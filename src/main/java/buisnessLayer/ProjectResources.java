package buisnessLayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataAccesLayer.exception.DBException;
import dataAccesLayer.service.ProjectService;
import dataAccesLayer.service.UserService;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Stateful
@Path("/project")
public class ProjectResources {

    @Inject
    ProjectService projectService;

    @Inject
    UserService userService;

    @GET
    public  String hello(){
        return "<H2 style=\"color : red\">Projects EJB</H2>";
    }

    //получение всех
    @GET
    @Path("/getAll")
    public Response getAll(){
        String jsonString = "";
        ObjectMapper mapper = new ObjectMapper();
        try {

            jsonString = mapper.writeValueAsString(projectService.getAll());
        }catch (IOException | DBException ex){
            ex.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(400);
            return response.build();
        }
        return Response.ok(jsonString).build();

    }
    //создать проект
    @POST
    @Path("/create")
    public Response createProject(@FormParam("login") String login,
                                  @FormParam("name")String name,
                                  @FormParam("description") String description,
                                  @FormParam("goalBudget") double goalBudget){
        try {
            this.userService.createProject(name,description,goalBudget,this.userService.get(login));
            return   Response.ok().status(200).build();
        }catch (DBException e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(400);
            return response.build();
        }
    }

    //апдейт?
    //getByID
    @GET
    @Path("/project{projectId}")
    public Response getProject(@PathParam(value = "projectId")String projectId){
        String jsonString = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = mapper.writeValueAsString(userService.get(projectId));
        }catch (DBException | JsonProcessingException e) {
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(400);
            return response.build();
        }
        return Response.ok(jsonString).build();
    }
    //delete
    @DELETE
    @Path("/project{projectId}")
    public Response deleteProject(@PathParam(value = "projectId")String projectId){
        try{
            this.userService.delete(projectId);
        }catch (Exception e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(400);
            return response.build();
        }
        return Response.ok().status(200).build();
    }
    //добавить девелопера
    //deleteDev
    //InviteToProj
    //add post to projBlog
    //delete Post
    //approve request
    //rejectRequest
    //update developer
    //add
    // delete creditInfo
    //approve reject commit commit ??getUncheckedCommits??
    //getCommitFiles
    //deleteCommit
    //deleteCommitFile

    //deleteComment
    @DELETE
    @Path("/comment{id}")
    public Response deleteComment(@PathParam(value = "id") String id){
        try{
            this.userService.deleteComment(id);

        }catch (Exception e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(400);
            return response.build();
        }
        return Response.ok().status(200).build();
    }
}
