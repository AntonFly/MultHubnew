package buisnessLayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import dataAccesLayer.entity.Users;
import dataAccesLayer.exception.DBException;
import dataAccesLayer.service.UserService;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Stateful
@Path("/user")
public class UserResources {

    private String avatarPath=null;
    private  String generalAvatarPath="E:/Печатные работы/ПИП/Курсач/resources/avatars/";

    @Context
    UriInfo uriInfo;

    @Inject
    UserService userService;

//    @Inject
//    DBService dbService;


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
        HashFunction hf = Hashing.adler32();
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
                           @FormParam("firstname") String name,
                           @FormParam("lastname") String surname,
                           @FormParam("email") String email,
                           @FormParam("modile") String mobile
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
            this.userService.create(user);
            if(email !=null){
                user = userService.get(login);
                user.getCondata().seteMail(email);
            }
            if(mobile !=null){
                user.getCondata().setMobilenumb(Long.valueOf(mobile));
            }
            this.userService.create(user);
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
        Users user =this.userService.get(login);
        System.out.println(login);
        if (user == null)
            return Response.ok("{\"msg\":\"true\"}").build();
        return Response.ok("{\"msg\":\"false\"}").build();
    }

    @POST
    @Path("/NewStatus")
    public  Response newPost(@FormParam("login") String login, @FormParam("status") String status) throws DBException {
        Users users = this.userService.get(login);
        users.setStatus(status);
        return Response.ok().build();

    }



//    @POST
//    @Path("/uploadAvatar")  //Your Path or URL to call this service
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public void uploadFile(
//            @DefaultValue("true") @FormDataParam("enabled") boolean enabled,
//            @FormDataParam("file") InputStream uploadedInputStream,
////            @FormDataParam("file") FormDataContentDisposition fileDetail,
//            @FormParam("login") String login) {
//        //Your local disk path where you want to store the file
//        String uploadedFileLocation = generalAvatarPath + login;
//        avatarPath =uploadedFileLocation;
//        System.out.println(uploadedFileLocation);
//        // save it
//        File objFile=new File(uploadedFileLocation);
//        if(objFile.exists())
//        {
//            objFile.delete();
//
//        }
//
////        saveToFile(uploadedInputStream, uploadedFileLocation);
//
//        String output = "File uploaded via Jersey based RESTFul Webservice to: " + uploadedFileLocation;
//
////        return Response.status(200).entity("{\"msg\":\"uploaded\"}").build();
//
//    }
//    private void saveToFile(InputStream uploadedInputStream,
//                            String uploadedFileLocation) {
//
//        try {
//            OutputStream out = null;
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            out = new FileOutputStream(new File(uploadedFileLocation));
//            while ((read = uploadedInputStream.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//
//    }
}


