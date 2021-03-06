package dataAccesLayer.service;

import dataAccesLayer.entity.*;
import dataAccesLayer.exception.DBException;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runners.MethodSorters;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectsServiceTests {
    private ProjectService ps;
    private static Projects pe;
    private static Commits Commits;

    static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(ProjectsServiceTests.class);
    }

    @BeforeAll
    void init() {
        ps = new ProjectService();
        pe = new Projects();
        pe.setCurbudget(12.);
        pe.setDescription("TEST");
        pe.setGoalbudget(13.);
        pe.setName("TEST");
        pe.setProjectid(UUID.nameUUIDFromBytes((pe.getName()+pe.getDescription()).getBytes()).toString());

    }

    @Test
    void GetProject() {
        try {
//            AddProject();
            //System.out.println("UPDATED:" +ps.get(UUID.nameUUIDFromBytes((pe.getName()+pe.getDescription()).getBytes()).toString()).getName());
            System.out.println("UPDATED:" +ps.get(pe.getProjectid()));
            //ps.delete(UUID.nameUUIDFromBytes((pe.getName()+pe.getDescription()).getBytes()).toString());

        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка добавления объекта");
        }
    }
    @Test
    void AddProject() {  //AddProject
        try {
            ps.create(pe);
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка добавления объекта");
        }
    }

    @Test
    void deleteProject(){

        try {
            //AddProject();
            ps.delete(UUID.nameUUIDFromBytes((pe.getName()+pe.getDescription()).getBytes()).toString());

        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка добавления объекта");
        }
    }

    @Test
    void commitFiles(){
        try {
//            ps.create(pe);

            Commits = new Commits();
            Commits.setApproved(Approved.AWAITS);
            Commits.setDeveloper("Usherb ebanii");
            Commits.setProjectid(pe.getProjectid());
            Commits.setTime(new Timestamp(System.currentTimeMillis()));
            Commits.setId(UUID.nameUUIDFromBytes(   (Commits.getDeveloper()+Commits.getProjectid()+Commits.getTime())   .getBytes()  ).toString());

            List<Commitsfile> commitsfileEntities = new LinkedList();
            Commitsfile Commitsfile1 = new Commitsfile();
            Commitsfile1.setCommitid(Commits);
            Commitsfile1.setFilename("mangusta");
            Commitsfile1.setFilepath("et");
            Commitsfile Commitsfile2 = new Commitsfile();
            Commitsfile2.setCommitid(Commits);
            Commitsfile2.setFilename("mangusta2");
            Commitsfile2.setFilepath("et");
            Commitsfile Commitsfile3 = new Commitsfile();
            Commitsfile3.setCommitid(Commits);
            Commitsfile3.setFilename("1mangusta");
            Commitsfile3.setFilepath("et");
            commitsfileEntities.add(Commitsfile1);
            commitsfileEntities.add(Commitsfile2);
            commitsfileEntities.add(Commitsfile3);

            ps.commitFiles(Commits,commitsfileEntities);
            //ps.deleteCommitFile(Commitsfile3);         IMPORTANT THING
            //ps.delete(UUID.nameUUIDFromBytes((pe.getName()+pe.getDescription()).getBytes()).toString()); //удаляет проект


        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка добавления коммита");
        }
    }

    @Test
    void getCommits(){
        try {
            List<Commits> commits = ps.getUncheckedCommits();
            for (Commits d:commits) {
                System.out.println(d.getDeveloper());
            }

            //deleteCommit();
            //ps.delete(UUID.nameUUIDFromBytes((pe.getName()+pe.getDescription()).getBytes()).toString()); //project

        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }
    @Test
    void getFiles(){
        try {
//            commitFiles();
            List<Commitsfile> files = ps.getCommitFiles(Commits);
            for (Commitsfile d:files) {
                System.out.println(d.getFilename());
            }
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }


    void deleteCommit(){
        try {
            ps.deleteCommit(Commits);

        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }



    private static Projectposts projectpostsEntity;
    @Test
    void AddPostToBlog(){  //AddPostToBlog
        try {
            ps.create(pe);
            projectpostsEntity = new Projectposts();
            projectpostsEntity.setId(null);
            projectpostsEntity.setFilepath("aaaaa");
            projectpostsEntity.setProjectid(pe.getProjectid());
            projectpostsEntity.setText("LOL ORU");
            projectpostsEntity.setTime(new Timestamp(System.currentTimeMillis()));
            ps.addPostToBlog(projectpostsEntity);
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }
    @Test
    void deletePost(){
        try {
            ps.deletePostInBlog(projectpostsEntity);
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }

    @Test
    void Commit(){
        try {
            //ps.create(pe);
            ps.approveCommit(Commits);
            ps.rejectCommit(Commits);
            ps.delete(UUID.nameUUIDFromBytes((pe.getName()+pe.getDescription()).getBytes()).toString()); //project

        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }

    @Test
    void AddDeveloper(){
        try {
            ps.create(pe);
            Projects project = ps.get(pe.getProjectid());
            UserService service = ServiceFactory.getUserService();
            Users Users = new Users();
            Users.setLogin("TEST");
            Users.setPassword("TEST");
            Users.setSurname("TEST");
            Users.setName("TEST");
            Users.setImgpath("TEST");
            service.create(Users);
            Users user = service.get(Users.getLogin());
            Developers Developers = new Developers();
            Developers.setLogin(Users);
            Developers.setProjectid(pe);
            ps.addDeveloper(Developers);
            service.delete(user.getLogin());
            ps.delete(project.getProjectid());

        }
        catch (DBException e){
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }


    void deleteDeveloper(Developers Developers){
        try {
            ps.deleteDeveloper(Developers.getLogin(),Developers.getProjectid());
        }
        catch (Exception e){
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }

    @Test
    void sendInvite(){
        try {
            ps.create(pe);
            UserService service = ServiceFactory.getUserService();

            Requests Requests = new Requests();
            Users Users = new Users();
            Users.setLogin("TEST");
            Users.setPassword("TEST");
            Users.setSurname("TEST");
            Users.setName("TEST");
            Users.setImgpath("TEST");
            ///////
            service.create(Users);
            ////////
            Requests.setIsrequest(false);
            Requests.setProjpos(Projpos.DEVELOPER);
            Requests.setLogin(Users);
            Requests.setProjectid(pe);
            ps.sendInviteToProject(Requests);
            service.delete("TEST");
         //   deleteProject();
        }
        catch (DBException e){
        e.printStackTrace();
        Assertions.fail("Ошибка получения коммитов");
        }
    }

    @Test
    void approveRequest_addDev(){
        try {
            UserService service = ServiceFactory.getUserService();
            Users Users = new Users();
            Users.setLogin("TEST");
            Users.setPassword("TEST");
            Users.setSurname("TEST");
            Users.setName("TEST");
            Users.setImgpath("TEST");
            service.create(Users);

            service.requestProj(pe,Users);

            Requests Requests = new Requests();
            Requests.setProjectid(pe);
            Requests.setLogin(Users);
            ps.approveRequest(Requests);
            Developers Developers = new Developers();
            Developers.setLogin(Users);
            Developers.setProjectid(pe);
            ps.deleteDeveloper(Developers.getLogin(),Developers.getProjectid());
            service.delete(Users.getLogin());
        }
        catch (DBException e){
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }

    @Test
    void removeCredit(){
        try {
            Creditinfo Creditinfo = new Creditinfo();
            Creditinfo.setCardnumber(124312421);
            Creditinfo.setProjectid(pe.getProjectid());
            ps.deleteCreditInfo(Creditinfo);
            ps.delete(UUID.nameUUIDFromBytes((pe.getName()+pe.getDescription()).getBytes()).toString());
        }
        catch (DBException e){
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }
    @Test
    void AddSOMECredit(){
        try {
            Creditinfo Creditinfo = new Creditinfo();
            Creditinfo.setCardnumber(124312421);
            Creditinfo.setProjectid(pe.getProjectid());
            Creditinfo.setProject(pe);
            ps.addCreditInfo(Creditinfo);
        }
        catch (DBException e){
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }

}
/*
AddProject
getProject
commitFiles
getCommits
getFiles
Commit   // approve and reject commit


*/