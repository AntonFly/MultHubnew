package buisnessLayer;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import dataAccesLayer.entity.*;
import dataAccesLayer.exception.DBException;
import dataAccesLayer.service.ServiceFactory;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public  class Bot extends TelegramLongPollingBot {
    public Bot() {
    }
    HashMap<Long,TelegramUser> users= new HashMap<>();

    public Bot(DefaultBotOptions options) {
        super(options);
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
       TelegramUser tUser = users.get(message.getChatId());
        if (message != null && message.hasText()){
        }
        if(tUser==null){
            switch (message.getText()){
                case "/start":
                    sendMsg(message,"Hello, user, first of all insert you login and password, then you can get all function of our bot. Insert \"/signIn\" to begin" +
                            "\n /info to get information about application");

                    break;
                case "/setting":
                    sendMsg(message,"What do you want to change");
                    break;
                case "/info":
                    sendMsg(message,"Smthing about bot");
                    break;
                case "/signIn":
                    sendMsg(message, "Enter your login:");
                    users.put(message.getChatId(),new TelegramUser());
                    break;
                default: sendMsg(message,"Sorry, I do not understand you.");
            }
        }else
            switch (message.getText()){
                case "/main": tUser.loginflag=false;
                    sendMsg(message,"Hello, user, first of all insert you login and password, then you can get all function of our bot. Insert \"/signIn\" to begin" +
                            "\n /info to get information about application");
                    tUser.loginflag=false;
                    tUser.logined=false;
                    break;
                case "/info":
                    sendMsg(message,"Smthing about bot");
                    break;
                case "/signIn":
                    sendMsg(message, "Enter your login:");
                    users.put(message.getChatId(),new TelegramUser());
                    break;
                default:
                    if(!tUser.loginflag && !tUser.logined){
                    try {
                        Users user = ServiceFactory.getUserService().get(message.getText());
                        if (user != null) {
                            sendMsg(message, "Ok, enter your password:");
                            tUser.loginflag = true;
                            tUser.login = message.getText();
                        } else
                            sendMsg(message, "Sorry, but there is not such user :-(\n" +
                                    "Enter your login again! or type /main to return at the beginning.");
                    } catch (DBException e) {
                        e.printStackTrace();
                    }
            }
            else if(!tUser.logined){
            try {
//                        ServiceFactory.getUserService().signIn(tUser.login,message.toString());
                HashFunction hf = Hashing.md5();
                HashCode hc = hf.newHasher()
                        .putString(message.getText(), Charsets.UTF_8)
                        .hash();
                if (ServiceFactory.getUserService().signIn(tUser.login,hc.toString())){
                    sendMsg(message,"All right, you successfully enter in our service :-).\n" +
                            "Your can see your news (/news);\n" +
                            "Your invites (/invites)\n");
                    tUser.logined=true;
                }else
                    sendMsg(message,"Sorry, this password is wrong :-(\n" +
                            "Enter your login and password again! or type /main to return at the beginning.");
            } catch (DBException e) {
                e.printStackTrace();
            }
            }else if(!tUser.isinvites){
                switch (message.getText()){
                    case "/news":
                        try{
                        List<Projectposts> posts=new ArrayList<>();
                        for (Projects proj: ServiceFactory.getUserService().get(tUser.login).getSubscriprions()
                             ) {
                            for (Projectposts post: proj.getPosts()
                                 ) {
                                Calendar cal= Calendar.getInstance();
                                cal.add(Calendar.DATE, -1);
                                long lMillis = cal.getTime().getTime();
                                if(post.getTime().getTime()-lMillis>0)
                                    posts.add(post);

                            }

                        }
//                        ObjectMapper mapper = new ObjectMapper();
//                        String jsonString = mapper.writeValueAsString(posts);
                          String resultString="";
                            for (Projectposts post: posts
                                 ) {
                                System.out.println(posts.size());
                                resultString+="\nProject: "+ServiceFactory.getProjectService().get(post.getProjectid()).getName()+"\n" +
                                        ""+post.getText();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                                Date parsedDate = dateFormat.parse(post.getTime().toString());
                                SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm dd-MM-yyyy");
                                System.out.println(post.getFilepath());
                                if(post.checkFilePath() && !post.getFilepath().equals("null") && !post.getFilepath().equals("") && post.getFilepath()!=null  ){
                                    System.out.println(post.getId());
//                                    sendMsg(message,resultString);
                                    sendImage(message,post.getFilepath(),resultString+"\n"+dateFormat1.format(parsedDate));
                                }else {
                                    System.out.println(resultString + "\n" + dateFormat1.format(parsedDate));
                                    sendMsg(message, resultString + "\n" + dateFormat1.format(parsedDate));
                                    System.out.println(post.getId());
                                }
                                resultString="";
                            }
                        sendMsg(message,resultString);
                        }catch (DBException | ParseException |NullPointerException e){
                            e.printStackTrace();
                            sendMsg(message,"Sorry, something went wrong :-(");
                        }
                        break;
                    case "/invites":
                        try {
                            sendMsg(message,"You can type \'/approve №\' or \'/reject №\' to approve/reject invite for project or type '/main' to choose news or invites again.");
                            int i=1;
//                            List<Requests> reqs = new ArrayList<>();
                            for (Requests req: ServiceFactory.getUserService().get(tUser.login).getRequests()
                                 ) {
                                if(!req.getIsrequest())
                                    tUser.invites.add(req);
                                sendMsg(message,"№ "+ i +"\n"+"Project: "+req.getProjectid().getName()+"\n" +
                                        "Position: "+ req.getProjpos()+"\n" +
                                        "Project description:" +req.getProjectid().getDescription());
                            }
                            if(tUser.invites.size()!=0)
                            tUser.isinvites= true;
                        } catch (DBException e) {
                            e.printStackTrace();
                            sendMsg(message,"Sorry, something went wrong :-(");
                        }
                        break;
                    default: sendMsg(message,"Sorry, I do not understand you.");

                }
        }else {
                try {
                    String[] instring = new String[2];
                    instring = message.getText().split(" ");
                    switch (instring[0]){
                        case "/approve":
                            Requests inv= tUser.invites.get(Integer.valueOf(instring[1])-1);

//                            Developers dev= new Developers();
//                            dev.setDescription("null");
//                            dev.setProjpos(Projpos.DEVELOPER);
//                            dev.setLogin(ServiceFactory.getUserService().get(inv.getLogin().getLogin()));
//                            dev.setProjectid(ServiceFactory.getProjectService().get(inv.getProjectid().getProjectid()));
//                            ServiceFactory.getProjectService().addDeveloper(dev);
                            System.out.println(ServiceFactory.getUserService().approveInvite(tUser.invites.get(Integer.valueOf(instring[1])-1)));
//                            ServiceFactory.getUserService().approveInvite(tUser.invites.get(Integer.valueOf(instring[1])-1));
                            tUser.isinvites=false;
                            sendMsg(message,"Now you are the "+tUser.invites.get(Integer.valueOf(instring[1])-1).getProjpos()+" of the project "+tUser.invites.get(Integer.valueOf(instring[1])-1).getProjectid().getName()+".");
                            break;
                        case "/reject":
                            ServiceFactory.getUserService().rejectInvite(tUser.invites.get(Integer.valueOf(instring[1])-1));
                            tUser.isinvites=false;
                            sendMsg(message,"You rejected invites to the project "+tUser.invites.get(Integer.valueOf(instring[1])-1).getProjectid().getName()+".");
                            break;
                        case "/main":
                            tUser.isinvites=false;
                            sendMsg(message,"Your can see your news (/news);\n" +
                                    "Your invites (/invites)\n");

                            break;
                            default: sendMsg(message,"Sorry, I do not understand you.");

                    }
                }catch (Exception e){
                    e.printStackTrace();
                    sendMsg(message,"Sorry, I do not understand you.");
                }
            }

    }
    }


    public synchronized void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
//            setButton(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
            e.printStackTrace();
        }
    }

    public void  setButton(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRowList = new ArrayList();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/info"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }
    public void sendImage(Message message, String url,String discription ) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(message.getChatId());
        sendPhotoRequest.setPhoto(new File(url));
        sendPhotoRequest.setCaption(discription);
        try {
            execute(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     * @return имя бота
     */
    public String getBotUsername() {
        return "MultHubBot";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        return "753724473:AAEYQx22N3KBxDkIEWv6jp5DnWloRKKiQts";

}
}

