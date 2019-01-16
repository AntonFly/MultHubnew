package buisnessLayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.sun.jersey.multipart.FormDataParam;
import dataAccesLayer.entity.Users;
import dataAccesLayer.exception.DBException;
import dataAccesLayer.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.enterprise.inject.spi.Bean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.*;
import java.util.List;

import dataAccesLayer.service.UserService;

@Stateful
@Path("/user")
public class UserResources {

//    @PostConstruct
//    void initQuery(){
//        try {
//            List<Users> users  = this.userService.getAll();
//            System.out.println(users.size()+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        }catch(Exception ex){}
//    }


    private String avatarPath=null;
    private  String generalAvatarPath="E:/Печатные работы/ПИП/Курсач/resources/avatars/";

    @Context
    UriInfo uriInfo;

    @Inject
    UserService userService;

    @GET
    public  String hello(){
        return "<H2 style=\"color : red\">Hello EJB</H2>";

    }

    @POST
    @Path("/signIn")
    public Response fingByLogin(@FormParam("login") final String login, @FormParam("password") String password ) throws JsonProcessingException {
        Users user = null;
        try {
            user = userService.get(login);
        } catch (DBException e) {
            return  Response.ok().status(500).build();
        }
        if (user == null)
            return Response.ok("{\"msg\":\"Неверный пароль или логин\"}").build();
        HashFunction hf = Hashing.md5();
        HashCode hc = hf.newHasher()
                .putString(password, Charsets.UTF_8)
                .hash();
        if( user.getPassword().equals(hc.toString())){
            ObjectMapper mapper = new ObjectMapper();
            userService.detach(user);
            user.setPassword(null);
            String jsonString = mapper.writeValueAsString(user);
            return Response.ok(jsonString).build();
        }
        else
        {
            return Response.ok("{\"msg\":\"Неверный пароль или логин\"}").build();
        }

    }

    @POST
    @Path("/signUp")
    public Response signUp(@FormParam("login") final String login,
                           @FormParam("password") String password,
                           @FormParam("name") String name,
                           @FormParam("surname") String surname,
                           @FormParam("email") String email,
                           @FormParam("mobile") String mobile
    ){
        try {
            System.out.println("sfdsfAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAdsfsdfsdfsdffdsfsdv!!!!!!!"+login+" "+password+" "+name+" ");
            Users user =new Users();
            user.setLogin(login);
            HashFunction hf = Hashing.md5();
            HashCode hc = hf.newHasher()
                    .putString(password, Charsets.UTF_8)
                    .hash();
            user.setPassword(hc.toString());
            user.setName(name);
            user.setSurname(surname);
            if(avatarPath==null)
                avatarPath=generalAvatarPath+"default.png";
            user.setImgpath(avatarPath);
            userService.create(user);
            if(email !=null){
                user = userService.get(login);
                user.getCondata().seteMail(email);
            }
            if(mobile !=null){
                user.getCondata().setMobilenumb(Long.valueOf(mobile));
            }
            return   Response.ok().status(200).build();
        }catch (Exception e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(401);
            return response.build();
        }
    }

    @GET
    @Path("/try{login}")
    public  Response tryLogin(@PathParam(value = "login") String login) throws DBException {
            Users user = userService.get(login);
            System.out.println(login);
            if (user == null)
                return Response.ok("{\"msg\":\"true\"}").build();
            return Response.ok("{\"msg\":\"false\"}").build();
    }

    @POST
    @Path("/NewStatus")
    public  Response newPost(@FormParam("login") String login, @FormParam("status") String status) throws DBException {
        Users users = userService.get(login);
        users.setStatus(status);
        return Response.ok().build();

    }



    @Path("/uploadAvatar")
    @POST
//    @Produces(MediaType.MULTIPART_FORM_DATA)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public javax.ws.rs.core.Response uploadNewAdvJson(@FormDataParam("file") InputStream is) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        //Your local disk path where you want to store the file
        String uploadedFileLocation = generalAvatarPath + "login.png";
        avatarPath =uploadedFileLocation;
        System.out.println(uploadedFileLocation);
        // save it
        File objFile=new File(uploadedFileLocation);
        if(objFile.exists())
        {
            objFile.delete();

        }

        saveToFile(is, uploadedFileLocation);

        String output = "File uploaded via Jersey based RESTFul Webservice to: " + uploadedFileLocation;

        return Response.status(200).entity("{\"msg\":\"uploaded\"}").build();

    }

    private void saveToFile(InputStream uploadedInputStream,
                            String uploadedFileLocation) {

        try {
            OutputStream out = null;
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    @GET
    @Path("/getavatar{login}")
    @Produces("image/png")
    public Response getFile(@PathParam("login") String login) {

        File file = new File(generalAvatarPath+login+".png");

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=image_from_server.png");
        return response.build();

    }
    @POST
    @Path("/changeProfile")
    public Response changeProfile(@FormParam("login") final String login,
                              @FormParam("password") String password,
                              @FormParam("firstname") String name,
                              @FormParam("lastname") String surname,
                              @FormParam("email") String email,
                              @FormParam("modile") String mobile){
        try{
            Users user = userService.get(login);
            if(password!=null)
                user.setPassword(password);
            if(email!=null)
                user.getCondata().seteMail(email);
            if(mobile!=null)
                user.getCondata().setMobilenumb(Long.valueOf(mobile));
            if(avatarPath!=null && !user.getImgpath().equals(generalAvatarPath+login) && !user.getImgpath().equals(avatarPath))
                user.setImgpath(avatarPath);
            return Response.ok("{\"msg\":\"updated\"}").build();

        }catch (Exception e){
            e.printStackTrace();
            return Response.ok().status(500).build();
        }



    };

    @GET
    @Path("/search{userName}")
    public  Response searchProject(@PathParam("userName") String userName){
        String json;
        try{
            List<Users> users =  userService.search(userName);
            ObjectMapper mapper = new ObjectMapper();

            json = mapper.writeValueAsString(users);

        }catch (com.fasterxml.jackson.core.JsonProcessingException e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(401);
            return response.build();
        }
        return Response.ok(json).build();
    }


    @POST
    @Path("/sendMessage")
    public  Response sendMessage(@FormParam("login") String login,
                                 @FormParam("toLogin") String toLogin,
                                 @FormParam("message") String body,
                                 @FormParam("dialog") String dialogId){
        try{
        Message message =new Message();
        if(dialogId!=null)
        message.setDialogId(dialogId);
        else {
            Users user1 =userService.get(login);
            Users user2 = userService.get(toLogin);
            userService.createDialog(user1,user2);
            for (Dialog dial: user1.getDialogs()
                 ) {
                for (Dialog dialof2: user2.getDialogs()
                     ) {
                    if (dial.getId().equals(dialof2.getId())){
                        message.setDialogId(dial.getId());
                        break;
                    }
                }
            }
        }
        message.setIsread(false);
        message.setTime(new Timestamp(System.currentTimeMillis()));
        message.setSender(login);
        message.setText("horosho");
        userService.addMessgae(message);

        }catch (DBException e){
            e.printStackTrace();
            return  Response.ok().status(500).build();
        }
        return  Response.ok("{\"msg\":\"sended\"}").build();

    }


    @POST
    @Path("/doanate")
    public  Response donate(@FormParam("login") String login,
                            @FormParam("projectId") String projectId,
                            @FormParam("comment") String comment,
                            @FormParam("value") Double value
                             ){
        try{
        Donaters donate = new Donaters();
        donate.setLogin(userService.get(login));
        donate.setProjectid(projectService.get(projectId));
        donate.setText(comment);
        donate.setValue(value);
        userService.donate(donate);
        }catch (DBException e ) {
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok("{\"msg\":\"donated\"}").build();
    }


    @POST
    @Path("/subscribe")
    public  Response subscribe(@FormParam("login") String login,
                               @FormParam("projectId") String projectId){

        try {
            userService.sub(userService.get(login),projectService.get(projectId));
        } catch (DBException e) {
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok("{\"msg\":\"subscribed\"}").build();
    }

    @DELETE
    @Path("/unsub")
    public Response unsubscribe(@FormParam("login") String login,
                              @FormParam("projectId") String projectId){

        try {
            userService.unsub(userService.get(login),projectService.get(projectId));
        } catch (DBException e) {
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok("{\"msg\":\"subscribed\"}").build();
    }


    @POST
    @Path("/follow")
    public  Response follow(@FormParam("login") String login,
                            @FormParam("follower") String follower){
        try{
        Followers followers = new Followers();
        followers.setLogin(userService.get(login));
        followers.setFollower(userService.get(follower));
        userService.followUser(followers);
        }catch (DBException e){
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok("{\"msg\":\"followed\"}").build();
    }


    @DELETE
    @Path("/unfollow")
    public  Response unfollow(@FormParam("login") String login,
                            @FormParam("follower") String follower){
        try{
           Users user = userService.get(follower);

            for (Users follow:
                    user.getFollowers()
                 ) {
                if(follow.getLogin().equals(login)) {
                    user.getFollowers().remove(follow);
                    break;
                }
            }
        }catch (DBException e){
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok("{\"msg\":\"unfollowed\"}").build();
    }

    @POST
    @Path("/request")
    public  Response request(@FormParam("login") String login,
                             @FormParam("projectId") String projectId
                             ){
        try{
        userService.requestProj(projectService.get(projectId),userService.get(login));
        }catch (DBException e){
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok("{\"msg\":\"requested\"}").build();
    }

    @POST
    @Path("/rejectInvite")
    public  Response rejectInvite(@FormParam("login") String login,
                                  @FormParam("projectId") String projectId
                                 ){
        try {

            Requests req = userService.getRequest(login, projectId);
            userService.rejectInvite(req);
        }catch (DBException e){
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok("{\"msg\":\"rejected\"}").build();
    }

    @POST
    @Path("/approveInvite")
    public Response approveInvite(@FormParam("login") String login,
                                  @FormParam("projectId") String projectId
                                 ){
        try {
            Requests req = userService.getRequest(login,projectId);
            userService.approveInvite(req);
        }catch (DBException e){
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok("{\"msg\":\"approwed\"}").build();
    }

    @GET
    @Path("ivites{login}")
    public Response getInvites(@PathParam("login") String login){
        String jsonString;
        try {
            List<Requests> reqs = userService.getInvites(login);
            ObjectMapper mapper = new ObjectMapper();
            jsonString = mapper.writeValueAsString(reqs);
        }catch (JsonProcessingException |DBException e) {
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok(jsonString).build();

    }
}





