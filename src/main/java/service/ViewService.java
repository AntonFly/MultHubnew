package service;

import dao.*;
import entity.*;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import util.DBService;

import javax.persistence.NoResultException;
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
//    public Map<String,Object> mainPageProjectInfo(ProjectsEntity projectsEntity) throws DBException{
//        Transaction transaction= DBService.getTransaction();
//        Map<String,Object> mapa = new HashMap<>();
//        try {
//            //posts
//            ProjectspostsDAO projectspostsDAO = DaoFactory.getProjectspostsDAO();
//            List<ProjectpostsEntity> posts = projectspostsDAO.getProjectPosts(projectsEntity.getProjectid());
//            //donaters
//            DonatersDAO donatersDAO = DaoFactory.getDonatersDAO();
//            List<DonatersEntity> donaters = donatersDAO.getProjectDonaters(projectsEntity.getProjectid());
//            //comments
//            CommentsDAO commentsDAO = DaoFactory.getCommentsDAO();
//            List<CommentsEntity> comments = commentsDAO.getProjectComments(projectsEntity.getProjectid());
//            //followers
//            SubsDAO subsDAO = DaoFactory.getSubsDAO();
//            UsersDAO usersDAO = DaoFactory.getUsersDAO();
//            List<SubsEntity> subsEntities = subsDAO.getProjectSubs(projectsEntity.getProjectid());
//            List<UsersEntity> users = new LinkedList<>();
//            for (SubsEntity sub: subsEntities) {
//                users.add(usersDAO.getEntityById(sub.getLogin()));
//            }
//            mapa.put("ProjectEntity",projectsEntity);
//            mapa.put("Posts",posts);
//            mapa.put("Subs",users);
//            mapa.put("Comments",comments);
//            mapa.put("Donaters",donaters);
//
//            transaction.commit();
//        } catch (HibernateException | NoResultException e) {
//            DBService.transactionRollback(transaction);
//            throw new DBException(e);
//        }
//        return mapa;
//    }
//
//    /**
//     * Return full information to display file tab of project
//     * @param projectsEntity object of project
//     * @return list of project's files
//     * @throws DBException Hiber exceptions replaced withon
//     */
//    public List<Object[]> filesPageProjectInfo(ProjectsEntity projectsEntity) throws DBException{
//        Transaction transaction= DBService.getTransaction();
//        List<Object[]> list;
//        try {
//            CommitsDao commitsDao = DaoFactory.getCommitsDao();
//            list = commitsDao.getCommitsFiles(projectsEntity);
//            transaction.commit();
//
//        } catch (HibernateException | NoResultException e) {
//            DBService.transactionRollback(transaction);
//            throw new DBException(e);
//        }
//        return list;
//    }
//
//    /**
//     * Return full information to display developer tab of project
//     * @param projectsEntity object of project
//     * @return map of projects's developers
//     * @throws DBException Hiber exceptions replaced withon
//     */
//
//    public Map<String, Object> developersPageProjectInfo(ProjectsEntity projectsEntity) throws DBException{
//        Transaction transaction= DBService.getTransaction();
//        Map<String,Object> mapa = new HashMap<>();
//        try {
//            DevelopersDAO developersDAO = DaoFactory.getDevelopersDAO();
//            mapa.put("ProjectEntity",projectsEntity);
//            mapa.put("Devs",developersDAO.getProjectDevs(projectsEntity.getProjectid()));
//            transaction.commit();
//
//        } catch (HibernateException | NoResultException e) {
//            DBService.transactionRollback(transaction);
//            throw new DBException(e);
//        }
//        return mapa;
//    }
//
//    /**
//     * Return full information to display tab with commits which is'n approved by maneger
//     * @param projectsEntity object of project
//     * @return List of priject's unapproved commits
//     * @throws DBException Hiber exceptions replaced withon
//     */
//    public List<Object[]> uncheckedfilesPageProjectInfo(ProjectsEntity projectsEntity) throws DBException{ //for manager approvence
//        Transaction transaction= DBService.getTransaction();
//        List<Object[]> list;
//        try {
//            CommitsDao commitsDao = DaoFactory.getCommitsDao();
//            list = commitsDao.getUncheckCommitsFiles(projectsEntity);
//            transaction.commit();
//
//        } catch (HibernateException | NoResultException e) {
//            DBService.transactionRollback(transaction);
//            throw new DBException(e);
//        }
//        return list;
//    }
//    /**
//     * Return information about history of commits of file
//     * @param fileDirectory directory with file
//     * @return map of file's commits
//     * @throws DBException Hiber exceptions replaced withon
//     */
//    public Map<CommitsEntity, CommitsfileEntity> getFilecommits(String fileDirectory) throws DBException {
//        Transaction transaction= DBService.getTransaction();
//        Map<CommitsEntity,CommitsfileEntity> map = new HashMap<>();
//        try {
//            CommitsfileDAO commitsfileDAO = DaoFactory.getCommitsfileDAO();
//            List<CommitsfileEntity> files = commitsfileDAO.getFilesByPath(fileDirectory);
//            transaction.commit();
//            //List<CommitsEntity> commits = new LinkedList<>();
//            CommitsDao commitsDao = DaoFactory.getCommitsDao();
//            for (CommitsfileEntity file: files) {
//                transaction = DBService.getTransaction();
//                map.put(commitsDao.getEntityById(file.getCommitid()),file);
//              //      commits.add(commitsDao.getEntityById(file.getCommitid()));
//                transaction.commit();
//            }
//
//        } catch (HibernateException | NoResultException e) {
//            DBService.transactionRollback(transaction);
//            throw new DBException(e);
//        }
//        return map;
//    }
//
//
//    /**
//     * Return information to display user's dialog
//     * @param login user login whose dialogs needed
//     * @return  List of each dialog information
//     * @throws DBException Hiber exceptions replaced withon
//     */
//    public  List<Map<String,Object>> getDialogs(String login) throws DBException {
//        List<Map<String,Object>> result= new ArrayList<>();
//        Transaction transaction= DBService.getTransaction();
//        try{
//        UsersDAO userDao= DaoFactory.getUsersDAO();
//        DialogDAO dialogDAO = DaoFactory.getDialogDao();
//        MessageDAO messageDao= DaoFactory.getMessageDao();
//
//        List<DialogEntity> dialogs=dialogDAO.getUserDialogs(login);
//        transaction.commit();
//        for (DialogEntity dialog:dialogs
//             ) {
//            UsersEntity other;
//            transaction= DBService.getTransaction();
//            Map<String,Object> map=new HashMap<>();
//            if (dialog.getOneUserId().equals(login)){
//                map.put("other",dialog.getTwoUserId());
//             other=userDao.getEntityById(dialog.getTwoUserId());
//            }else{
//                map.put("other",dialog.getOneUserId());
//                other=userDao.getEntityById(dialog.getOneUserId());
//            }
//            MessageEntity message =messageDao.getLastMessage(login);
//            map.put("otherImage",other.getImgpath());
//            map.put("text",message.getText());
//            map.put("time",message.getTime());
//            result.add(map);
//        }
//        } catch (HibernateException | NoResultException e) {
//            DBService.transactionRollback(transaction);
//            throw new DBException(e);
//        }
//        return result;
//    }
//
//    /**
//     * Using for get all messages of dialog
//     * @param id dialog id
//     * @return List of messages
//     * @throws DBException Hiber exceptions replaced withon
//     */
//    public List<MessageEntity> getDialogMessages(String id) throws DBException{
//        Transaction transaction= DBService.getTransaction();
//        List<MessageEntity> result;
//        try{
//        MessageDAO messageDAO= DaoFactory.getMessageDao();
//        result =messageDAO.getDialogMessages(id);
//        transaction.commit();
//        } catch (HibernateException | NoResultException e) {
//            DBService.transactionRollback(transaction);
//            throw new DBException(e);
//        }
//        return  result;
//    }
//
//    /**
//     * Using to get full information which is displayed on main page of site
//     * @return List of main page objects
//     * @throws DBException Hiber exceptions replaced witho
//     */
//    public  List<Map<String,Object>> mainPage() throws DBException{
//        List<Map<String,Object>> result= new ArrayList<>();
//        Transaction transaction= DBService.getTransaction();
//
//        SubsDAO subsDao= DaoFactory.getSubsDAO();
//        ProjectsDAO projectsDao= DaoFactory.getProjectsDAO();
//        ProjectspostsDAO postDao= DaoFactory.getProjectspostsDAO();
//        CommitsDao commitsDao= DaoFactory.getCommitsDao();
//        CommitsfileDAO commitsfileDao= DaoFactory.getCommitsfileDAO();
//        List<Object[]> subs=subsDao.getMostPopular();
//        transaction.commit();
//        for (Object[] sub: subs
//             ) {
//            transaction= DBService.getTransaction();
//            ProjectsEntity proj=projectsDao.getEntityById((String) sub[0]);
//            ProjectpostsEntity post=postDao.getProjLastPost(proj.getProjectid());
//            List<CommitsfileEntity> latestMedia=commitsfileDao.getLatestMedea(proj.getProjectid());
//            transaction.commit();
//            Map<String,Object> map=new HashMap<>();
//            map.put("followers",sub[1]);
//            map.put("prjName", proj.getName());
//            map.put("description",proj.getDescription());
//            map.put("lastPost",post.getText());
//            map.put("lastMedia",latestMedia);
//            result.add(map);
//
//        }
//        return result;
//    }
}
