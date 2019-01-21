package buisnessLayer;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.*;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@MessageDriven(
        //имя topic, на который подписан бин
        mappedName="jms/messageTopic",
        name = "MessagesBean")
@ServerEndpoint("/echo/{login}")
public class MessagesBean implements MessageListener {

        static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

        @OnMessage
        public String handleMessage(String message){
            return "{\"msg\":\"Thanks for the message: " + message+"\"},{\"from\":\"server\"}";
        }

    @OnOpen
    public void openConnection(Session session,
                               @PathParam("login") String Login) {
        session.getUserProperties().put("toLogin", Login);
        peers.add(session);
        send("OpenSomeConnection","server",Login);
    }

    @OnClose
    public void closedConnection(Session session) {
        peers.remove(session);
    }

    public static void send(String msg,String from,String toLogin) {
        try {
            for (Session session : peers) {

                    if (session.isOpen() && session.getUserProperties().get("toLogin").equals(toLogin)){
//                        ObjectMapper mapper = new ObjectMapper();
//
//                        json = mapper.writeValueAsString();
                        session.getBasicRemote().sendObject("{\"msg\":\""+msg+"\",\"from\":\""+from+"\"}");
                    }
                }
            }
         catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }



    //метод, вызываемый при получении нового сообщения
    @Override
    public void onMessage(Message msg) {
        try {
            TextMessage message = (TextMessage)msg;
            //считываем свойство из соответствующего поля, заданное вручную в consumer
            System.out.println("FROM MDB - client type IS " + message.getStringProperty("clientType"));
            //считываем  само сообщение
            System.out.println("FROM MDB - payload  IS " + message.getText());
            send(message.getText(),msg.getStringProperty("from"),msg.getStringProperty("toLogin"));

        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

}
