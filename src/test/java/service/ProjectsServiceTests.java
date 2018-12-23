package service;

import entity.*;
import exception.DBException;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
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
    static Projects pe;
    static Commits Commits;

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
            Commitsfile1.setFilename("pizda ebanaya cherez rot mangusta blyat");
            Commitsfile1.setFilepath("epta");
            Commitsfile Commitsfile2 = new Commitsfile();
            Commitsfile2.setCommitid(Commits);
            Commitsfile2.setFilename("pizda ebanaya cherez rot mangusta blyat2");
            Commitsfile2.setFilepath("epta");
            Commitsfile Commitsfile3 = new Commitsfile();
            Commitsfile3.setCommitid(Commits);
            Commitsfile3.setFilename("pizda ebanaya cherez rot mangusta blyat3");
            Commitsfile3.setFilepath("epta");
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



    static Projectposts projectpostsEntity;
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
            UserService service = ServiceFactory.getUserService();
            Users Users = new Users();
            Users.setLogin("TEST");
            Users.setPassword("TEST");
            Users.setSurname("TEST");
            Users.setName("TEST");
            Users.setImgpath("TEST");
            service.create(Users);
            Developers Developers = new Developers();
            Developers.setLogin(Users);
            Developers.setProjectid(pe);
            ps.addDeveloper(Developers);
            //deleteDeveloper(Developers);
            ps.deleteDeveloper(Developers);
        }
        catch (DBException e){
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }


    void deleteDeveloper(Developers Developers){
        try {
            ps.deleteDeveloper(Developers);
        }
        catch (Exception e){
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }

    @Test
    void sendInvite(){
        try {
            //ps.create(pe);
            UserService service = ServiceFactory.getUserService();

            Requests Requests = new Requests();
            Users Users = new Users();
            Users.setLogin("TEST");
            Users.setPassword("TEST");
            Users.setSurname("TEST");
            Users.setName("TEST");
            Users.setImgpath("TEST");

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
            ps.deleteDeveloper(Developers);
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