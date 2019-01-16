package buisnessLayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataAccesLayer.entity.*;
import dataAccesLayer.exception.DBException;
import dataAccesLayer.service.ProjectService;
import dataAccesLayer.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.xml.registry.infomodel.User;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    @POST
    @Path("/update")
    public Response updateProject(@FormParam("projectId") String projectId,
                                  @FormParam("goalBudget") Double goalBudget,
                                  @FormParam("curBudget") Double curBudget){
        try {
            Projects project = this.projectService.get(projectId);
            if(goalBudget != null)
                project.setGoalbudget(goalBudget);
            if(curBudget != null)
                project.setCurbudget(curBudget);
//? enough?
        }catch (DBException e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(400);
            return response.build();
        }
        return Response.ok().status(200).build();
    }

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
    @POST
    @Path("/addDev")
    public Response addDev(@FormParam("projectId") String projectId,
                           @FormParam("login")String login,
                           @FormParam("projpos")Projpos projpos
                           ){
        try {
            Developers developers = new Developers();
            developers.setLogin(this.userService.get(login));
            developers.setProjectid(this.projectService.get(projectId));
            developers.setProjpos(projpos);
            this.projectService.addDeveloper(developers);
        }catch (DBException e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(400);
            return response.build();
        }
        return Response.ok().status(200).build();
    }

//    deleteDev
    @DELETE
    @Path("/developer{login}&{projectId}")
    public Response deleteDeveloper(@PathParam(value = "login") String login,
                                    @PathParam(value = "projectId") String projectId){
        try{
            this.projectService.deleteDeveloper(this.userService.get(login), this.projectService.get(projectId));
        }catch (DBException e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(400);
            return response.build();
        }
        return Response.ok().status(200).build();
    }
    //InviteToProj
    @POST
    @Path("/invite")
    public Response inviteToProject(@FormParam("login") String login,
                                    @FormParam("projectId") String projectId,
                                    @FormParam("projPos") Projpos projPos){
        try {
            Requests request = new Requests();
            request.setLogin(this.userService.get(login));
            request.setProjectid(this.projectService.get(projectId));
            request.setProjpos(projPos);
        this.projectService.sendInviteToProject(request);
        }catch (DBException e){
        e.printStackTrace();
        Response.ResponseBuilder response = Response.ok();
        response.status(400);
        return response.build();
    }
        return Response.ok().status(200).build();
    }

    //add post to projBlog
    @POST
    @Path("/toPost")
    public Response addPostToBlog(@FormParam("projectId") String projectId,
                                  @FormParam("text") String text,
                                  @FormParam("proctId") String proetId              //FILE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                  ){
        try {
            Projectposts projectposts = new Projectposts();
            projectposts.setProject(this.projectService.get(projectId));
            projectposts.setText(text);
            projectposts.setFilepath("/deafult");     // !!!!!!!!!!!!!!!!!!!!!Что делать
            this.projectService.addPostToBlog(projectposts);

        }catch (DBException e){
        e.printStackTrace();
        Response.ResponseBuilder response = Response.ok();
        response.status(400);
        return response.build();
    }
        return Response.ok().status(200).build();
    }
    //delete Post
    @DELETE
    @Path("/toPost{postId}")
    public Response deletePost(@PathParam(value = "postId") String posId){
        try{
                Projectposts projectposts = new Projectposts();
                projectposts.setId(posId);
                this.projectService.deletePostInBlog(projectposts);
        }catch (DBException e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(400);
            return response.build();
        }
        return Response.ok().status(200).build();
    }
    //approve request
    @POST
    @Path("/approveRequest")
    public Response approveRequest(@FormParam("projectID") String projectId,
                                   @FormParam("login") String login){
        try{
            Projects project = this.projectService.get(projectId);
            for(Requests req: project.getRequests()) {
                if (req.getLogin().equals(login))
                    this.projectService.approveRequest(req);
            }
        }catch (DBException e){
        e.printStackTrace();
        Response.ResponseBuilder response = Response.ok();
        response.status(400);
        return response.build();
    }
        return Response.ok().status(200).build();
    }
    //rejectRequest
    @POST
    @Path("/rejectRequest")
    public Response rejectRequest(@FormParam("projectID") String projectId,
                                   @FormParam("login") String login){
        try{
            Projects project = this.projectService.get(projectId);
            for(Requests req: project.getRequests()) {
                if (req.getLogin().equals(login))
                    this.projectService.rejectRequest(req);
            }
        }catch (DBException e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(400);
            return response.build();
        }
        return Response.ok().status(200).build();
    }

    //update developer
    @POST
    @Path("/updateDev")
    public Response updateDeveloper(@FormParam("projectID") String projectId,
                                    @FormParam("login") String login,
                                    @FormParam("projPos") Projpos projPos,
                                    @FormParam("description") String desc){
        try{
            Projects project = this.projectService.get(projectId);

            for(Developers dev: project.getDevelopers()) {
                if(dev.getLogin().getLogin().equals(login)){
                    if(projPos != null)
                        dev.setProjpos(projPos);
                    if(desc != null)
                        dev.setDescription(desc);
                }
            }
        }catch (DBException e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(400);
            return response.build();
        }
        return Response.ok().status(200).build();
    }

    //addCreditIngo
    @POST
    @Path("/addCredit")
    public Response addCredit(@FormParam("projectId") String projectId,
                              @FormParam("cardnumber") Integer cardnumber,
                              @FormParam("qiwi") Integer qiwi,
                              @FormParam("yaMoney") Long yaMoney){
        try {
            Creditinfo creditinfo = new Creditinfo();
            creditinfo.setProjectid(projectId);
            creditinfo.setCardnumber(cardnumber);
            creditinfo.setQiwimobilephone(qiwi);
            creditinfo.setYamoney(yaMoney);
            this.projectService.addCreditInfo(creditinfo);
        }catch (DBException e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(400);
            return response.build();
        }
        return Response.ok().status(200).build();
    }

    //updateCredit
    @POST
    @Path("/updateCredit")
    public Response updateCredit(@FormParam("projectId") String projectId,
                                 @FormParam("cardnumber") Integer cardnumber,
                                 @FormParam("qiwi") Integer qiwi,
                                 @FormParam("yaMoney") Long yaMoney){
        try {
            Projects project = this.projectService.get(projectId);
            if(cardnumber != null)
                project.getCredit().setCardnumber(cardnumber);
            if(qiwi != null)
                project.getCredit().setQiwimobilephone(qiwi);
            if(yaMoney != null)
                project.getCredit().setYamoney(yaMoney);

        }catch (DBException e){
            e.printStackTrace();
            Response.ResponseBuilder response = Response.ok();
            response.status(400);
            return response.build();
        }
        return Response.ok().status(200).build();
    }

    //approve commit
    @POST
    @Path("/approveCommit")
    public Response approveCommit(
                        @FormParam("commitId") String commitId,
                        @FormParam("projectId") String projectId
                    ){
        try {
            Projects project = this.projectService.get(projectId);
            for(Commits commit : project.getCommits()) {
                if(commit.getId().equals(commitId))
                    this.projectService.approveCommit(commit);
            }
        }catch (DBException e){
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok().status(200).build();
    }

    //Reject commit
    @POST
    @Path("/rejectCommit")
    public Response rejectCommit(
                                    @FormParam("commitId") String commitId,
                                    @FormParam("projectId") String projectId
                                ){
        try {
            Projects project = this.projectService.get(projectId);
            for(Commits commit : project.getCommits()) {
                if(commit.getId().equals(commitId))
                    this.projectService.rejectCommit(commit);
            }
        }catch (DBException e){
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok().status(200).build();
    }

    //commit new files !!!!HARD!!!!
    @POST                       ///////////////////////////////////////////////////////////////////////////////////////////////
    @Path("/addFiles")
    public Response commitFiles(
                                    @FormParam("projectId") String projectId,
                                    @FormParam("login") String login
                                    //чтото с листом файлов
                                ){
        try{
            Commits commit = new Commits();
            commit.setProjectid(projectId);
            commit.setApproved(Approved.AWAITS);
            commit.setDeveloper(login);
            commit.setTime(new Timestamp(System.currentTimeMillis()));

            //Write Some FILEs
            List<Commitsfile> files = new LinkedList<>();

            this.projectService.commitFiles(commit,files);
        }catch (DBException e){
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok().status(200).build();
    }



    //    @GET
//    @Path("/uncheckedCommits{projectId}")
//    public Response uncheckedfilesPageProjectInfo(@PathParam(value = "projectId") String projectId){
//        String json;                                                                                    //работает не совсем верно...возвращает вложенный ключ
//        try{
//            ObjectMapper mapper = new ObjectMapper();
//            List<Commitsfile> result = this.projectService.getUncheckedCommits();
//            //хер пойми че надо вместе с ними пикчи наверн
//            json = mapper.writeValueAsString(result);
//        }catch (JsonProcessingException | DBException e){
////            e.printStackTrace();
//            Response.ResponseBuilder response = Response.ok();
//            response.status(401);
//            return response.build();
//        }
//        return Response.ok(json).build();
//    }



    //getCommitFiles
    @GET
    @Path("/commitFiles{projectId}&{commitId}")
    public Response getCommitFiles(@PathParam(value = "commitId") String commitId,@PathParam(value = "projectId") String projectId){
        String json ="";
        ObjectMapper mapper = new ObjectMapper();
        try{
            Projects project = this.projectService.get(projectId);
            for(Commits com: project.getCommits()) {
                if(com.getId().equals(commitId))
                json = mapper.writeValueAsString(this.projectService.getCommitFiles(com));
            }
        }catch (JsonProcessingException | DBException e){
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok(json).status(200).build();
    }
    //deleteCommit
    @DELETE
    @Path("/commit")
    public Response deleteCommit(
                                    @FormParam("commitId") String commitID,
                                    @FormParam("projectId") String projectId
                                ){
        try{
            Projects projects = this.projectService.get(projectId);
            for(Commits com: projects.getCommits()) {
                if(com.getId().equals(commitID))
                    this.projectService.deleteCommit(com);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok().status(200).build();
    }
    //deleteCommitFile
    @DELETE
    @Path("/commitFile")
    public Response deleteCommitFile(
            @FormParam("commitId") String commitID,
            @FormParam("projectId") String projectId,
            @FormParam("fileName") String fileName
    ){
        try{
            Projects projects = this.projectService.get(projectId);
            for(Commits com: projects.getCommits()) {

                if(com.getId().equals(commitID)) {
                    for(Commitsfile file:com.getCommitsfile()){
                        if(file.getFilename().equals(fileName))
                            this.projectService.deleteCommitFile(file);
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok().status(200).build();
    }

    //deleteComment
    @DELETE
    @Path("/comment{id}")
    public Response deleteComment(@PathParam(value = "id") String id){
        try{
            this.userService.deleteComment(id);
        }catch (Exception e){
            e.printStackTrace();
            return Response.ok().status(400).build();
        }
        return Response.ok().status(200).build();
    }
}
