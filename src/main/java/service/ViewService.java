package service;

import dao.*;
import entity.*;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import util.DBService;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.*;

public class ViewService  {
    /**
     * Return user information for user profile
     * @param login request user login
     * @return map of user's information
     * @throws DBException Hiber exceptions replaced with
     */
    public Map<String,Object> UserPageInformation(String login) throws DBException {
        Map<String,Object> map =new HashMap();

        UsersDAO usersDAO= DaoFactory.getUsersDAO();
        ConnectiondataDao conDao= DaoFactory.getConnectiondataDao();
        UserpostDAO postsDao= DaoFactory.getUserPostDao();
        DevelopersDAO devDao= DaoFactory.getDevelopersDAO();
        ProjectsDAO projDao= DaoFactory.getProjectsDAO();
        FollowersDAO followDao= DaoFactory.getFollowersDao();

        Users user= usersDAO.getEntityById(login);
        ConnectionData connectiondata=conDao.getEntityById(login);
        List<Userpost> posts= user.getPosts();
        List<Developers> devEnt=user.getDevelopers();
        List<Projects> proj = new ArrayList();
        List<Users> followers= user.getFollowers();


        for (Developers dev:
                devEnt) {
            proj.add(dev.getProjectid());

        }
        map.put("login",user.getLogin());
        map.put("name",user.getName());
        map.put("surname",user.getSurname());
        map.put("imjPath",user.getImgpath());
        map.put("email",connectiondata.geteMail());
        map.put("mobilenumb",connectiondata.getMobilenumb());
        map.put("satus",user.getStatus());
        map.put("posts",posts);
        map.put("projects",proj);
        map.put("followers",followers);

        return map;
    }


    /**
     *  Return full information tu display projectpage
     * @param projectsEntity object of project
     * @return map of project's information
     * @throws DBException Hiber exceptions replaced with
     */
    public Map<String,Object> mainPageProjectInfo(Projects projectsEntity) throws DBException{
        Map<String,Object> mapa = new HashMap();
        try {

            ProjectsDAO projectspostsDAO = DaoFactory.getProjectsDAO();
            Projects project=projectspostsDAO.getEntityById(projectsEntity.getProjectid());
            List<Projectposts> posts =project.getPosts();
            //donaters
            List<Donaters> donaters =project.getDonations();
            //comments
            List<Comments> comments = project.getComments();
            //followers
            List<Users> users = project.getSubscribers();
            mapa.put("ProjectEntity",projectsEntity);
            mapa.put("Posts",posts);
            mapa.put("Subs",users);
            mapa.put("Comments",comments);
            mapa.put("Donaters",donaters);

        } catch (PersistenceException e) {
            throw new DBException(e);
        }
        return mapa;
    }

    /**
     * Return full information to display file tab of project
     * @param projectsEntity object of project
     * @return list of project's files
     * @throws DBException Hiber exceptions replaced withon
     */
    public List<Commitsfile> filesPageProjectInfo(Projects projectsEntity) throws DBException{
        List<Commitsfile> files=new ArrayList();
        try {
            ProjectsDAO projectspostsDAO = DaoFactory.getProjectsDAO();
            Projects project=projectspostsDAO.getEntityById(projectsEntity.getProjectid());
            List<Commits> commits =project.getCommits();
            for (Commits commit:
                commits ) {
                files.addAll(commit.getCommitsfile());

            }



        } catch (PersistenceException e) {
            throw new DBException(e);
        }
        return files;
    }

    /**
     * Return full information to display developer tab of project
     * @param projectsEntity object of project
     * @return map of projects's developers
     * @throws DBException Hiber exceptions replaced withon
     */

    public Map<String, Object> developersPageProjectInfo(Projects projectsEntity) throws DBException{
        Map<String,Object> mapa = new HashMap();
        try {
            ProjectsDAO projectspostsDAO = DaoFactory.getProjectsDAO();
            Projects project=projectspostsDAO.getEntityById(projectsEntity.getProjectid());
            mapa.put("ProjectEntity",projectsEntity);
            mapa.put("Devs",project.getDevelopers());

        } catch (PersistenceException e) {
            throw new DBException(e);
        }
        return mapa;
    }

    /**
     * Return full information to display tab with commits which is'n approved by maneger
     * @param projectsEntity object of project
     * @return List of priject's unapproved commits
     * @throws DBException Hiber exceptions replaced withon
     */
    public List<Commitsfile> uncheckedfilesPageProjectInfo(Projects projectsEntity) throws DBException{ //for manager approvence
        List<Commitsfile> list= new ArrayList();
        try {
            ProjectsDAO projectspostsDAO = DaoFactory.getProjectsDAO();
            Projects project=projectspostsDAO.getEntityById(projectsEntity.getProjectid());
            List<Commits> commits =project.getCommits();
            for (Commits commit:
                 commits) {
                if(commit.getApproved()== Approved.AWAITS){
                    list.addAll(commit.getCommitsfile());
                }

            }


        } catch (PersistenceException e) {
            throw new DBException(e);
        }
        return list;
    }



    /**
     * Return information to display user's dialog
     * @param login user login whose dialogs needed
     * @return  List of each dialog information
     * @throws DBException Hiber exceptions replaced withon
     */
    public  List<Dialog> getDialogs(String login) throws DBException {
        try{
        UsersDAO userDao= DaoFactory.getUsersDAO();
        Users user = userDao.getEntityById(login);
         return user.getDialogs();
        } catch (PersistenceException e) {
            throw new DBException(e);
        }
    }

    /**
     * Using for get all messages of dialog
     * @param id dialog id
     * @return List of messages
     * @throws DBException Hiber exceptions replaced withon
     */
    public List<Message> getDialogMessages(String id) throws DBException{
        try{
        DialogDAO dialogDAO= DaoFactory.getDialogDao();
        Dialog dialog =dialogDAO.getEntityById(id);
        return  dialog.getMessages();
        } catch (PersistenceException e) {
            throw new DBException(e);
        }
    }

    /**
     * Using to get full information which is displayed on main page of site
     * @return List of main page objects
     * @throws DBException Hiber exceptions replaced witho
     */
    public  List<Projects> mainPage(String login) throws DBException{
        try{
            UsersDAO usersDAO= DaoFactory.getUsersDAO();
            Users user= usersDAO.getEntityById(login);
            return  user.getSubscriprions();
        } catch (PersistenceException e) {
            throw new DBException(e);
        }


    }
}
