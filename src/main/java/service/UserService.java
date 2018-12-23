package service;

import dao.*;
import entity.*;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import util.DBService;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public class UserService extends AbstractService<Users,String> {

    public UserService() {
    }

    /**
     * Returned all users
     * @return list of users
     * @throws DBException Hiber exceptions replaced with
     */

    @Override
    public List<Users> getAll() throws DBException {

        try {
            UsersDAO dao = DaoFactory.getUsersDAO();
            List<Users> list =  dao.getAll();
            return list;

        } catch (PersistenceException e) {
            throw new DBException(e);
        }

    }

    /**
     * Generate new user
     * @param user - user obj
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    @Override
    public boolean create(Users user) throws DBException {
        try {
            UsersDAO dao = DaoFactory.getUsersDAO();
            dao.create(user);
        } catch (PersistenceException e) {
            throw new DBException(e);
        }
        return true;
    }

    /**
     * Update short user information
     * @param item user who need to update
     *@return in case of success TRUE
     *@throws DBException Hiber exceptions replaced with
     */
    @Override
    public boolean update(Users item) throws DBException {
        try {
            UsersDAO dao = DaoFactory.getUsersDAO();
            dao.update(item);
        } catch (PersistenceException e) {
            throw new DBException(e);
        }
        return true;
    }

    /**
     *  Return short information about one user
     * @param id user, whose information needed
     *@return in case of success TRUE
     *@throws DBException Hiber exceptions replaced with
     */
    @Override
    public Users get(String id) throws DBException {
        try {
            UsersDAO dao = DaoFactory.getUsersDAO();
            Users ue =dao.getEntityById(id);
            return ue;
        }catch (PersistenceException e) {
            throw new DBException(e);
        }


    }

    /**
     *  Delete one user account
     * @param id user's login, who want
     *@return in case of success TRUE
     *@throws DBException Hiber exceptions replaced with
     */
    @Override
    public boolean delete(String id) throws DBException {
        try{
            UsersDAO dao= DaoFactory.getUsersDAO();
            dao.delete(id);
        } catch (PersistenceException e) {
            throw new DBException(e);
        }
        return true;
    }

    /**
     *
     * @param login user's login
     * @param password input user's password
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced with@throws DBException
     */
    public  boolean signIn(String login,String password ) throws DBException {
        Users user=null;
        try{
            UsersDAO dao = DaoFactory.getUsersDAO();
            user =dao.getEntityById(login);
        }catch (PersistenceException e){
            throw new DBException(e);
        }
        return user.getPassword().equals(password);
    }

    /**
     *
     * @param user obj, new user data
     * @param con obj, new user's connection dara
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced withhrows DBException
     */
    public boolean signUp(Users user, ConnectionData con)throws DBException {
        try {
            UsersDAO uDao = DaoFactory.getUsersDAO();
            ConnectiondataDao connectiondataDao = DaoFactory.getConnectiondataDao();
            uDao.create(user);
            connectiondataDao.create(con);
        }catch (PersistenceException e){
            throw new DBException(e);
        }
        return true;
    }



    /**
     *
     * @param user UserEntity obj who want to subscribe
     * @param projectsEntity ProjectEntity obj goal project
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced with
     * adds new sub into SubsEntity
     */
     public  boolean sub(Users user, Projects projectsEntity)throws DBException{
        Subs subsEntity = new Subs();
        subsEntity.setId(new SubsEntityPK(user,projectsEntity));
        try{
            SubsDAO subsDAO= DaoFactory.getSubsDAO();
            subsDAO.create(subsEntity);
        }catch (PersistenceException e){
            throw new DBException(e);
        }
         return true;
     }


    /**
     * Delete subscription from one project
     * @param user UserEntity obj who want to unsubscribe
     * @param projectsEntity ProjectEntity obj goal project
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced with
     *
     */
     public boolean unsub(Users user, Projects projectsEntity)throws DBException {
         try{
                SubsDAO subsDAO= DaoFactory.getSubsDAO();
                subsDAO.delete(user,projectsEntity);
         }catch (PersistenceException e){
             throw new DBException(e);
         }
         return true;
     }

    /**
     *  Make comment to one project
     * @param comment obj which want to add
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced with
     */
     public boolean doComment(Comments comment)throws DBException{
         try{
             CommentsDAO commentsDAO = DaoFactory.getCommentsDAO();
             commentsDAO.create(comment);
         }catch (PersistenceException e){
             throw new DBException(e);
         }
         return true;
     }

    /**
     * Delete comment from one project
     * @param comment comment which want to delete
     * @return  in case of success TRUE
     * @throws DBException Hiber exceptions replaced with
     */
     public boolean deleteComment(Comments comment) throws DBException{
         try{
             CommentsDAO commentsDAO = DaoFactory.getCommentsDAO();
             commentsDAO.delete(comment);
         }catch (PersistenceException e){
             throw new DBException(e);
         }
         return true;
     }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *  Create dialog between two users
     * @param user1  object of first user (may contain only login)
     * @param user2  object of second user (same)
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced with
     */
     public boolean createDialog(Users user1,Users user2)throws DBException{
         Dialog dialogEntity = new Dialog();
         DialogUsers dialogUsers1=new DialogUsers();
         DialogUsers dialogUsers2=new DialogUsers();
         dialogUsers1.setId(new DialogUsersPK(dialogEntity,user1));
         dialogUsers2.setId(new DialogUsersPK(dialogEntity,user2));
         try{
             DialogDAO dialogDao = DaoFactory.getDialogDao();
             DialogUsersDAO dialogUsersDAO =DaoFactory.getDialogUsersDAO();
             dialogDao.create(dialogEntity);
             dialogUsersDAO.create(dialogUsers1);
             dialogUsersDAO.create(dialogUsers1);
         }catch (PersistenceException e){
             throw new DBException(e);
         }
         return true;
     }

    /**
     * Create message in database
     * @param message obj where dialogId is UUID of bytes of "login1+login2"
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced with
     */
     public boolean addMessgae(Message message)throws DBException{
         try{
             MessageDAO dao= DaoFactory.getMessageDao();
             dao.create(message);
         }catch (PersistenceException e){
             throw new DBException(e);
         }
         return true;
     }

//

    /**
     *  Following the user's posts
     * @param follow obj which contain two login of user and of writer
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced with
     */
     public boolean followUser(Followers follow)throws DBException {
         try{
             FollowersDAO dao = DaoFactory.getFollowersDao();
             dao.create(follow);
         }catch (PersistenceException e){
             throw new DBException(e);
         }
         return true;
     }

    /**
     * Disable the following
     * @param follow  obj which contain two login of user and of writer
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced with
     */
     public boolean unfollowUser(Followers follow)throws DBException{
         try{
             FollowersDAO dao= DaoFactory.getFollowersDao();
             FollowersPK followersEntityPK= new FollowersPK();
             followersEntityPK.setLogin(follow.getLogin().getLogin());
             followersEntityPK.setFollower(follow.getFollower().getLogin());
             dao.delete(followersEntityPK);
         }catch (PersistenceException e){
             throw new DBException(e);
         }
         return true;
     }
    /**
     * Creating project by user
     * @param projectsEntity obj of the project
     *@return in case of success TRUE
     *@throws DBException Hiber exceptions replaced with
     */
    public boolean createProject(Projects projectsEntity,Users Users)throws  DBException{
        ProjectService service= ServiceFactory.getProjectService();
        service.create(projectsEntity);
        Developers dev= new Developers();
        dev.setLogin(Users);
        dev.setProjectid(projectsEntity);
        dev.setProjpos(Projpos.MANAGER);
        dev.setDescription(null);
        service.addDeveloper(dev);
        return true;
    }

    /**
     * Delete project, manager of which is user
     * @param entity object of project which need to delete
     *@return in case of success TRUE
     *@throws DBException Hiber exceptions replaced with
     */
    public boolean deleteProject(Projects entity)throws DBException{
        ProjectService service=ServiceFactory.getProjectService();
        service.delete(entity.getProjectid());
        return true;
    }

    /**
     * Send request to join project's developers
     * @param proj  goal project
     * @param user  user who want to join
     *@return in case of success TRUE
     *@throws DBException Hiber exceptions replaced with
     */
    public boolean requestProj(Projects proj,Users user) throws DBException{
        Requests req= new Requests();
        req.setLogin(user);
        req.setProjectid(proj);
        req.setProjpos(Projpos.DEVELOPER);
        req.setIsrequest(true);
        try{
            RequestsDAO dao = DaoFactory.getRequestsDAO();
            dao.create(req);
        }catch (PersistenceException e){
            throw new DBException(e);
        }
        return true;
    }


    public  boolean donate(Donaters donate) throws DBException{
        try{
            DonatersDAO dao= DaoFactory.getDonatersDAO();
            dao.create(donate);
        }catch (PersistenceException e){
            throw new DBException(e);
        }
    return true;
    }

    public boolean approveInvite(Requests entity) throws DBException{
        Transaction transaction=null;
        try{
            Developers dev= new Developers();
            dev.setDescription("null");
            dev.setProjpos(Projpos.DEVELOPER);
            dev.setLogin(entity.getLogin());
            dev.setProjectid(entity.getProjectid());
            ProjectService ps=ServiceFactory.getProjectService();
            ps.addDeveloper(dev);
            RequestsDAO dao= DaoFactory.getRequestsDAO();
            RequestsEntityPK pk=new RequestsEntityPK();
            pk.setLogin(entity.getLogin());
            pk.setProjectid(entity.getProjectid());
            dao.delete(pk);
            transaction.commit();

        }catch (PersistenceException e){
            throw new DBException(e);
        }
        return true;
    }

    public boolean rejectInvite(Requests entity) throws DBException{
        Transaction transaction= null;
        try{
            RequestsDAO dao= DaoFactory.getRequestsDAO();
            RequestsEntityPK pk=new RequestsEntityPK();
            pk.setLogin(entity.getLogin());
            pk.setProjectid(entity.getProjectid());
            dao.delete(pk);
            transaction.commit();
        }catch (PersistenceException e){
            throw new DBException(e);
        }
        return true;
    }

    public  boolean newStatus(Users user, String status) throws DBException{
        try{
            UsersDAO dao= DaoFactory.getUsersDAO();
            user.setStatus(status);
            dao.update(user);
        }catch (PersistenceException e){
            throw new DBException(e);

        }
        return true;
    }
}
