package dataAccesLayer.service;

import dataAccesLayer.entity.*;
import dataAccesLayer.exception.DBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;
//import java.dataAccesLayer.util.stream.Stream;


class ViewServiceTest {
    private ViewService vs;
    @BeforeEach
    void setUp() {
        vs= ServiceFactory.getViewService();
    }

    @Test
    void userPageInformation() {
        Map<String,Object> user_data=null;
        try {
            user_data=vs.UserPageInformation("5d");
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения информации профиля");
        }
        System.out.println("\nLogin: "+(String) user_data.get("login"));
        System.out.println("Name: "+(String)user_data.get("name"));
        System.out.println("Img: "+(String) user_data.get("imjPath"));
        for (Projects proj: (List<Projects>)user_data.get("projects")
             ) {
            System.out.println("\nProject: "+proj.getName());
        }
        System.out.println("\nFollowers:");
        for (Users follower:(List<Users>)user_data.get("followers")
             ) {
            System.out.println(follower.getLogin());
        }
        System.out.println("\nPosts:");
        for (Userpost post:(List<Userpost>)user_data.get("posts")
        ) {
            System.out.println(post.getText());
        }
//        System.out.println("Followers: "+user_data.get("projects"));

    }


    @Test
    void userDialog(){
        List<Dialog> result=null;
        try{
            result=vs.getDialogs("5d");
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения диалогов");
        }
        for (Dialog map:
                result) {
            System.out.println();
            System.out.println("Собеседник: "+ map.getInterlocutors().get(0).getLogin());
            System.out.println("Аватар собеседникa: "+ map.getInterlocutors().get(0).getImgpath());
            System.out.println("Последнее сообщение: "+  map.getMessages().get(map.getMessages().size()-1));
            System.out.println("Время сообщения: "+ map.getMessages().get(map.getMessages().size()-1).getTime());
        }
    }

    @Test
    void dialogMessages() throws DBException {
        List<Message> messages= vs.getDialogMessages("2");
        for (Message mes:
                messages) {
            System.out.println();
            System.out.println("Cообщение: "+  mes.getText());
            System.out.println("Время сообщения: "+ mes.getTime());
        }
    }

    @Test
    void mainPage() throws DBException {
        List<Projects> result=vs.mainPage("5d");
        for (Projects map:
                result) {
            System.out.println("\nПодписчиков: "+map.getSubscribers().size());
            System.out.println("Название проекта: " +(String) map.getName());
            System.out.println("Описание проекта: "+(String) map.getDescription());
            System.out.println("Последний пост: "+ map.getPosts().get(map.getPosts().size()-1).getText());
            List<Commitsfile> latestMedia=(List<Commitsfile>)map.getCommits().get(map.getCommits().size()-1).getCommitsfile();
            System.out.print("Последние медиа файлы: ");
            for (Commitsfile media: latestMedia
                 ) {
                System.out.println(( media).getFilename());

            }
        }
    }

    @Test
    void getFiles(){
        try{
            Projects Projects = new Projects();
            Projects.setName("LOL CHANGED");
            Projects.setDescription("V 1999 GODU rodilsa divan i vosstal");
            Projects.setCurbudget(12.);
            Projects.setGoalbudget(13.);
            Projects.setProjectid(UUID.nameUUIDFromBytes((Projects.getName()+Projects.getDescription()).getBytes()).toString());
            List<Commitsfile> list = ServiceFactory.getViewService().filesPageProjectInfo(Projects.getProjectid());
            System.out.println("Commits and files connected with project:");
            for(Commitsfile row:list){
                System.out.println("CommitId: "+row.getCommitid().getId()+" \nDEVELOPER: "+ row.getCommitid().getDeveloper()+" TIME:"+row.getCommitid().getTime());
                System.out.println("FILENAME: "+row.getFilename() +" FILEPATH:"+row.getFilepath());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения диалогов");
        }
    }



    @Test
    void mainPageInfo(){
        try {
            Projects Projects = new Projects();
            Projects.setName("LOL CHANGED");
            Projects.setDescription("V 1999 GODU rodilsa divan i vosstal");
            Projects.setCurbudget(12.);
            Projects.setGoalbudget(13.);
            Projects.setProjectid(UUID.nameUUIDFromBytes((Projects.getName()+Projects.getDescription()).getBytes()).toString());

            Map<String,Object> map = ServiceFactory.getViewService().mainPageProjectInfo(Projects.getProjectid());
            System.out.println("posts text: "+((List<Projectposts>)map.get("Posts")).get(0).getText());
            System.out.println("subs: "+((List<Users>)map.get("Subs")).get(0).getLogin());

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения инфы");
        }
    }

    @Test
    void developersPageProjectInfo(){
        try {
            Projects Projects = new Projects();
            Projects.setName("LOL CHANGED");
            Projects.setDescription("V 1999 GODU rodilsa divan i vosstal");
            Projects.setCurbudget(12.);
            Projects.setGoalbudget(13.);
            Projects.setProjectid(UUID.nameUUIDFromBytes((Projects.getName()+Projects.getDescription()).getBytes()).toString());

            Map<String,Object> map = ServiceFactory.getViewService().developersPageProjectInfo(Projects.getProjectid());
            System.out.println("developers: login = "+((List<Developers>)map.get("Devs")).get(0).getLogin().getLogin() +" description = "+((List<Developers>)map.get("Devs")).get(0).getDescription());

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения инфы");
        }
    }

    @Test
    void uncheckedfilesPageProjectInfo(){
        try {

            Projects Projects = new Projects();
            Projects.setName("LOL CHANGED");
            Projects.setDescription("V 1999 GODU rodilsa divan i vosstal");
            Projects.setCurbudget(12.);
            Projects.setGoalbudget(13.);
            Projects.setProjectid(UUID.nameUUIDFromBytes((Projects.getName()+Projects.getDescription()).getBytes()).toString());


            List<Commitsfile> list = ServiceFactory.getViewService().uncheckedfilesPageProjectInfo(Projects.getProjectid());
            for(Commitsfile row:list){
                System.out.println("CommitId: "+row.getId().getCommitid()+" \nDEVELOPER: "+ row.getCommitid().getDeveloper()+" TIME:"+row.getCommitid().getTime());
                System.out.println("FILENAME: "+row.getFilename() +" FILEPATH:"+row.getFilepath()+"\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения инфы");
        }
    }
}
