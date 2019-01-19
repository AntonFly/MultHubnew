package buisnessLayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataAccesLayer.dao.DaoFactory;
import dataAccesLayer.entity.*;
import dataAccesLayer.exception.DBException;
import dataAccesLayer.service.ProjectService;
import dataAccesLayer.service.UserService;
import dataAccesLayer.service.ViewService;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import javax.ws.rs.sse.OutboundSseEvent;
//import javax.ws.rs.sse.Sse;
//import javax.ws.rs.sse.SseEventSink;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateful
@Path("/view")
public class ViewResources {

//int i = 0;

//    @GET
//    @Path("/getEvent")
//    public void news(@Context SseEventSink eventSink, @Context Sse sse) {
//        try (SseEventSink sink = eventSink) {
//            i++;
//            sink.send(sse.newEvent("data"+i));
//            sink.send(sse.newEvent("MyEventName", "more data"));
//
//            OutboundSseEvent event = sse.newEventBuilder().
//                    id("EventId").
//                    name("EventName").
//                    data(i).
//                    reconnectDelay(10000).
//                    comment("Anything i wanna comment here!").
//                    build();
//
//            sink.send(event);
//
//        }
//    }


    @Inject
    ViewService viewService;

    @Inject
    ProjectService projectService;

    @Inject
    UserService userService;

    @GET
    public  String hello(){
        return "<H2 style=\"color : red\">View EJB</H2>";
    }



    @GET
    @Path("/dialogs{login}")
    public Response getDialogs(@PathParam(value = "login") String login) {  //temp imho можно сделать лучше
        String jsonString = "";                                             // + отправка картинки
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Dialog> dialogs = this.viewService.getDialogs(login);

            HashMap<String,HashMap<String,Object>> maps = new HashMap<>();
            System.out.println(dialogs.size()+" AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            for(int i = 0; i< dialogs.size(); i++) {
                HashMap<String, Object> toJson = new HashMap<>();

                toJson.put("dialogs", dialogs.get(i));
                Users user;
                if (dialogs.get(0).getInterlocutors().get(0).getLogin().equals(login))
                    user =dialogs.get(i).getInterlocutors().get(1);
                else user =dialogs.get(i).getInterlocutors().get(0);
                toJson.put("humans", user);

                toJson.put("messages", dialogs.get(i).getMessages().get(0)); // надо возвращать только последнее сообщение!!!!!!!!!!
                maps.put(user.getLogin(), toJson);
            }

            jsonString = mapper.writeValueAsString(maps);

            return Response.ok(jsonString).build();
        }catch (IOException| DBException ex){
//            ex.printStackTrace();
        }
        return Response.ok(jsonString).build();

    }

    @GET
    @Path("/messages{dialogId}&{login}")  //айди конкретного диалога и айди клиента
    public  Response getDialogMessages(@PathParam(value = "dialogId") String dialogId,
                                       @PathParam(value = "login") String login ){   //по хорошему должно подгружаться кусками
        String json;                                                                      //при получении сообщений - непрочтенные сообщение прочитать
        try{
            ObjectMapper mapper = new ObjectMapper();
            List<Message> messages = this.viewService.getDialogMessages(dialogId);

//            for(int i = 0; i < messages.size(); i++){     не меняет в бд, как присоединить к презистанцу хз
//                if(!messages.get(i).getIsread() && !messages.get(i).getSender().equals(login))
//                    messages.get(i).setIsread(true);
//            }

            readMessages(login,dialogId);  //посылает запрос
            json = mapper.writeValueAsString(messages);
        }catch (com.fasterxml.jackson.core.JsonProcessingException | DBException ex){
//            ex.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(401);
            return response.build();
        }
        return Response.ok(json).build();
    }
    @GET
    @Path("/readMessage{dialogId}&{login}")
    public Response readMessages(@PathParam("login") String login, @PathParam("dialogId") String dialogId){
        try{
        Dialog dialog= DaoFactory.getDialogDao().getEntityById(dialogId);
        for (Message message:
                dialog.getMessages()
             ) {
            if(!message.getSender().equals(login)){
                message.setIsread(true);
            }

        }
        }catch (Exception e){
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok("{\"msg\":\"read\"}").build();
    }
    @GET
    @Path("/getFollowers{login}")
    public  Response getFollowers(@PathParam(value = "login") String login){
        String json;
        try{
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(userService.get(login).getFollowers());
        }catch (DBException |JsonProcessingException e){
          e.printStackTrace();
          return Response.ok().status(400).build();
        }
        return Response.ok(json).build();
    }

    @GET
    @Path("/userPageInfo{login}")
    public Response UserPageInformation(@PathParam(value = "login") String login){ //список подписок подписчиков и тп
        String json;
        try{
//            new MailSender().sendMail("TEST","Here's some text");
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> result = this.viewService.UserPageInformation(login);
//            result.put("Image",new) как то отправить пикчу
            json = mapper.writeValueAsString(result);
        }catch (com.fasterxml.jackson.core.JsonProcessingException | DBException e){
//            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(401);
            return response.build();
        }
        return Response.ok(json).build();
    }

    @GET
    @Path("/projInfo{projectId}")
    public Response mainPageProjectInfo(@PathParam(value = "projectId") String projectId){
        String json;
        try{
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> result = this.viewService.mainPageProjectInfo(projectId);
//            result.put("Image",new) как то отправить пикчи гг
            json = mapper.writeValueAsString(result);
        }catch (com.fasterxml.jackson.core.JsonProcessingException | DBException e){
//            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(401);
            return response.build();
        }
        return Response.ok(json).build();
    }

    @GET
    @Path("/projDev{projectId}")
    public Response developersPageProjectInfo(@PathParam(value = "projectId") String projectId){
        String json;  //работает не совсем верно...возвращает вложенный ключ
        try{
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> result = this.viewService.developersPageProjectInfo(projectId);
//            result.put("Image",new) как то отправить пикчи гг
            json = mapper.writeValueAsString(result);
        }catch (com.fasterxml.jackson.core.JsonProcessingException | DBException e){
//            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(401);
            return response.build();
        }
        return Response.ok(json).build();
    }

    @GET
    @Path("/projFiles{projectId}")
    public Response filesPageProjectInfo(@PathParam(value = "projectId") String projectId){
        String json; //работает не совсем верно...возвращает вложенный ключ
        try{
            ObjectMapper mapper = new ObjectMapper();
            List<Commitsfile> result = this.viewService.filesPageProjectInfo(projectId);
//хер пойми че надо вместе с ними пикчи наверн
            json = mapper.writeValueAsString(result);
        }catch (com.fasterxml.jackson.core.JsonProcessingException | DBException e){
//            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(401);
            return response.build();
        }
        return Response.ok(json).build();
    }

    @GET
    @Path("/uncheckedCommits{projectId}")
    public Response uncheckedfilesPageProjectInfo(@PathParam(value = "projectId") String projectId){
        String json; //работает не совсем верно...возвращает вложенный ключ
        try{
            ObjectMapper mapper = new ObjectMapper();
            List<Commitsfile> result = this.viewService.uncheckedfilesPageProjectInfo(projectId);
//хер пойми че надо вместе с ними пикчи наверн
            json = mapper.writeValueAsString(result);
        }catch (com.fasterxml.jackson.core.JsonProcessingException | DBException e){
//            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(401);
            return response.build();
        }
        return Response.ok(json).build();
    }

    @GET
    @Path("/news{login}")
    public Response mainPage(@PathParam(value = "login") String login){
        String json;
        try{
            ObjectMapper mapper = new ObjectMapper();


            json = mapper.writeValueAsString(this.viewService.mainPage(login));
        }catch (com.fasterxml.jackson.core.JsonProcessingException | DBException e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(401);
            return response.build();
        }
        return Response.ok(json).build();
    }

    @GET
    @Path("/search{projectName}")
    public  Response searchProject(@PathParam("projectName") String projectname){
        String json;
        try{
            List<Projects> projects =  projectService.search(projectname);
            ObjectMapper mapper = new ObjectMapper();

            json = mapper.writeValueAsString(projects);

        }catch (com.fasterxml.jackson.core.JsonProcessingException e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(401);
            return response.build();
        }
        return Response.ok(json).build();
    }

}
