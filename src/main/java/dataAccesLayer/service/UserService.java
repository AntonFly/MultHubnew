package dataAccesLayer.service;

import dataAccesLayer.dao.*;
import dataAccesLayer.entity.*;
import dataAccesLayer.exception.DBException;

import javax.ejb.Singleton;
import javax.persistence.PersistenceException;
import java.util.List;
@Singleton
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

    public void  detach(Users user){
            UsersDAO dao= DaoFactory.getUsersDAO();
            dao.detach(user);
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

        try{
            UsersDAO usersDAO= DaoFactory.getUsersDAO();
            Users users= usersDAO.getEntityById(user.getLogin());
            users.sub(projectsEntity);
            usersDAO.update(users);
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
             UsersDAO usersDAO= DaoFactory.getUsersDAO();
             Users users= usersDAO.getEntityById(user.getLogin());
             users.unSub(projectsEntity);
             usersDAO.update(users);
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
     public Dialog createDialog(Users user1,Users user2)throws DBException{
         Dialog dialogEntity = new Dialog();

         try{
             DialogDAO dialogDao = DaoFactory.getDialogDao();
             UsersDAO UsersDAO =DaoFactory.getUsersDAO();
             user1.addDialog(dialogEntity);
             user2.addDialog(dialogEntity);
             UsersDAO.update(user1);
             UsersDAO.update(user2);

         }catch (PersistenceException e){
             throw new DBException(e);
         }
         return dialogEntity;
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
             UsersDAO usersDAO= DaoFactory.getUsersDAO();
             Users owner =usersDAO.getEntityById(follow.getLogin().getLogin());
             Users follower =usersDAO.getEntityById(follow.getFollower().getLogin());
             owner.addFollower(follower);
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
             FollowersDAO dao = DaoFactory.getFollowersDao();
             UsersDAO usersDAO= DaoFactory.getUsersDAO();
             Users owner =usersDAO.getEntityById(follow.getLogin().getLogin());
             Users follower =usersDAO.getEntityById(follow.getFollower().getLogin());
             owner.dellFollower(follower);
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

        }catch (PersistenceException e){
            throw new DBException(e);
        }
        return true;
    }

    public boolean rejectInvite(Requests entity) throws DBException{
        try{
            RequestsDAO dao= DaoFactory.getRequestsDAO();
            RequestsEntityPK pk=new RequestsEntityPK();
            pk.setLogin(entity.getLogin());
            pk.setProjectid(entity.getProjectid());
            dao.delete(pk);
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
