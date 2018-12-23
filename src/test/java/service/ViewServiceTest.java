package service;

import entity.*;
import exception.DBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ServiceFactory;
import service.ViewService;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;
//import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ViewServiceTest {
//    private ViewService vs;
//    @BeforeEach
//    void setUp() {
//        vs= ServiceFactory.getViewService();
//    }
//
//    @Test
//    void userPageInformation() {
//        Map<String,Object> user_data=null;
//        try {
//            user_data=vs.UserPageInformation("5d");
//        } catch (DBException e) {
//            e.printStackTrace();
//            Assertions.fail("Ошибка получения информации профиля");
//        }
//        System.out.println("\nLogin: "+(String) user_data.get("login"));
//        System.out.println("Name: "+(String)user_data.get("name"));
//        System.out.println("Img: "+(String) user_data.get("imjPath"));
//        for (Projects proj: (List<Projects>)user_data.get("projects")
//             ) {
//            System.out.println("\nProject: "+proj.getName());
//        }
//        System.out.println("\nFollowers:");
//        for (Followers follower:(List<Followers>)user_data.get("followers")
//             ) {
//            System.out.println(follower.getFollower());
//        }
//        System.out.println("\nPosts:");
//        for (Userpost post:(List<Userpost>)user_data.get("posts")
//        ) {
//            System.out.println(post.getText());
//        }
////        System.out.println("Followers: "+user_data.get("projects"));
//
//    }
//
//
//    @Test
//    void userDialog(){
//        List<Map<String,Object>> result=null;
//        try{
//            result=vs.getDialogs("5d");
//        } catch (DBException e) {
//            e.printStackTrace();
//            Assertions.fail("Ошибка получения диалогов");
//        }
//        for (Map<String,Object> map:
//                result) {
//            System.out.println();
//            System.out.println("Собеседник: "+ map.get("other"));
//            System.out.println("Аватар собеседникa: "+ map.get("otherImage"));
//            System.out.println("Последнее сообщение: "+  map.get("text"));
//            System.out.println("Время сообщения: "+ map.get("time"));
//        }
//    }
//
//    @Test
//    void dialogMessages() throws DBException {
//        List<Message> messages= vs.getDialogMessages("1");
//        for (Message mes:
//                messages) {
//            System.out.println();
//            System.out.println("Cообщение: "+  mes.getText());
//            System.out.println("Время сообщения: "+ mes.getTime());
//        }
//    }
//
//    @Test
//    void mainPage() throws DBException {
//        List<Map<String,Object>> result=vs.mainPage();
//        for (Map<String,Object> map:
//                result) {
//            System.out.println("\nПодписчиков: "+(BigInteger)map.get("followers"));
//            System.out.println("Название проекта: " +(String) map.get("prjName"));
//            System.out.println("Описание проекта: "+(String) map.get("description"));
//            System.out.println("Последний пост: "+(String) map.get("lastPost"));
//            List<Commitsfile> latestMedia=(List<Commitsfile>)map.get("lastMedia");
//            System.out.print("Последние медиа файлы: ");
//            for (Commitsfile media: latestMedia
//                 ) {
//                System.out.println(( media).getFilename());
//
//            }
//        }
//    }
//
//    @Test
//    void getFiles(){
//        try{
//            Projects Projects = new Projects();
//            Projects.setName("LOL CHANGED");
//            Projects.setDescription("V 1999 GODU rodilsa divan i vosstal");
//            Projects.setCurbudget(12.);
//            Projects.setGoalbudget(13.);
//            Projects.setProjectid(UUID.nameUUIDFromBytes((Projects.getName()+Projects.getDescription()).getBytes()).toString());
//            List<Object[]> list = ServiceFactory.getViewService().filesPageProjectInfo(Projects);
//            System.out.println("Commits and files connected with project:");
//            for(Object[] row:list){
//                System.out.println("CommitId: "+((Commits)row[1]).getId()+" \nDEVELOPER: "+ ((Commits)row[1]).getDeveloper()+" TIME:"+((Commits)row[1]).getTime());
//                System.out.println("FILENAME: "+((Commitsfile)row[2]).getFilename() +" FILEPATH:"+((Commitsfile)row[2]).getFilepath());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assertions.fail("Ошибка получения диалогов");
//        }
//    }
//
//    @Test
//    void getFileCommits(){
//        try {
//            Map<Commits,Commitsfile> map = ServiceFactory.getViewService().getFilecommits("kek");
//            for (Map.Entry<Commits,Commitsfile> entry: map.entrySet()) {
//                System.out.println("Commit: id = "+entry.getKey().getId()+" time = "+entry.getKey().getTime()+" File: filename = "+entry.getValue().getFilename());
//            }
//
//        } catch (Exception e) {
//        e.printStackTrace();
//        Assertions.fail("Ошибка получения комитов");
//        }
//    }
//
//    @Test
//    void mainPageInfo(){
//        try {
//            Projects Projects = new Projects();
//            Projects.setName("LOL CHANGED");
//            Projects.setDescription("V 1999 GODU rodilsa divan i vosstal");
//            Projects.setCurbudget(12.);
//            Projects.setGoalbudget(13.);
//            Projects.setProjectid(UUID.nameUUIDFromBytes((Projects.getName()+Projects.getDescription()).getBytes()).toString());
//
//            Map<String,Object> map = ServiceFactory.getViewService().mainPageProjectInfo(Projects);
//            System.out.println("posts text: "+((List<Projectposts>)map.get("Posts")).get(0).getText());
//            System.out.println("subs: "+((List<Users>)map.get("Subs")).get(0).getLogin());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assertions.fail("Ошибка получения инфы");
//        }
//    }
//
//    @Test
//    void developersPageProjectInfo(){
//        try {
//            Projects Projects = new Projects();
//            Projects.setName("LOL CHANGED");
//            Projects.setDescription("V 1999 GODU rodilsa divan i vosstal");
//            Projects.setCurbudget(12.);
//            Projects.setGoalbudget(13.);
//            Projects.setProjectid(UUID.nameUUIDFromBytes((Projects.getName()+Projects.getDescription()).getBytes()).toString());
//
//            Map<String,Object> map = ServiceFactory.getViewService().developersPageProjectInfo(Projects);
//            System.out.println("developers: login = "+((List<DevelopersEntity>)map.get("Devs")).get(0).getLogin() +" description = "+((List<DevelopersEntity>)map.get("Devs")).get(0).getDescription());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assertions.fail("Ошибка получения инфы");
//        }
//    }
//
//    @Test
//    void uncheckedfilesPageProjectInfo(){
//        try {
//
//            Projects Projects = new Projects();
//            Projects.setName("LOL CHANGED");
//            Projects.setDescription("V 1999 GODU rodilsa divan i vosstal");
//            Projects.setCurbudget(12.);
//            Projects.setGoalbudget(13.);
//            Projects.setProjectid(UUID.nameUUIDFromBytes((Projects.getName()+Projects.getDescription()).getBytes()).toString());
//
//
//            List<Object[]> list = ServiceFactory.getViewService().uncheckedfilesPageProjectInfo(Projects);
//            System.out.println("Waiting commits:");
//            for(Object[] row:list){
//                System.out.println("CommitId: "+((Commits)row[1]).getId()+" \nDEVELOPER: "+ ((Commits)row[1]).getDeveloper()+" TIME:"+((Commits)row[1]).getTime());
//                System.out.println("FILENAME: "+((Commitsfile)row[2]).getFilename() +" FILEPATH:"+((Commitsfile)row[2]).getFilepath());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assertions.fail("Ошибка получения инфы");
//        }
//    }
}
