package service;

import dao.*;
import entity.Comments;
import entity.Projpos;
import entity.RequestsEntityPK;
import entity.SubsEntityPK;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import util.DBService;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public class UserService extends AbstractService<UsersEntity,String> {

    public UserService() {
    }

    /**
     * Returned all users
     * @return list of users
     * @throws DBException Hiber exceptions replaced with
     */

    @Override
    public List<UsersEntity> getAll() throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            UsersDAO dao = DaoFactory.getUsersDAO();
            List<UsersEntity> list =  dao.getAll();

            transaction.commit();
            return list;
//            logger.fine("Create item " + user);

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
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
    public boolean create(UsersEntity user) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            UsersDAO dao = DaoFactory.getUsersDAO();
            dao.create(user);

            transaction.commit();

//            logger.fine("Create item " + user);

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
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
    public boolean update(UsersEntity item) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            UsersDAO dao = DaoFactory.getUsersDAO();
            dao.update(item);

            transaction.commit();

//            logger.fine("Create item " + user);

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
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
    public UsersEntity get(String id) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            UsersDAO dao = DaoFactory.getUsersDAO();
            UsersEntity ue =dao.getEntityById(id);
            transaction.commit();
            return ue;
        }catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
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
        Transaction transaction = DBService.getTransaction();
        try{
            UsersDAO dao= DaoFactory.getUsersDAO();
            dao.delete(id);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
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
        Transaction transaction= DBService.getTransaction();
        UsersEntity user=null;
        try{
            UsersDAO dao = DaoFactory.getUsersDAO();
            user =dao.getEntityById(login);
            transaction.commit();
        }catch (HibernateException | NoResultException e){
            DBService.transactionRollback(transaction);
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
    public boolean signUp(UsersEntity user,ConnectiondataEntity con)throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            UsersDAO uDao = DaoFactory.getUsersDAO();
            ConnectiondataDao connectiondataDao = DaoFactory.getConnectiondataDao();
            uDao.create(user);
            connectiondataDao.create(con);
            transaction.commit();
        }catch (HibernateException | NoResultException e){
            DBService.transactionRollback(transaction);
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
     public  boolean sub(UsersEntity user, ProjectsEntity projectsEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        SubsEntity subsEntity = new SubsEntity();
        subsEntity.setLogin(user.getLogin());
        subsEntity.setProjectid(projectsEntity.getProjectid());
        try{
            SubsDAO subsDAO= DaoFactory.getSubsDAO();
            subsDAO.create(subsEntity);
            transaction.commit();
        }catch (HibernateException | NoResultException e){
            DBService.transactionRollback(transaction);
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
     public boolean unsub(UsersEntity user, ProjectsEntity projectsEntity)throws DBException {
         Transaction transaction = DBService.getTransaction();
         SubsEntityPK subsEntitypk = new SubsEntityPK();
         subsEntitypk.setLogin(user.getLogin());
         subsEntitypk.setProjectid(projectsEntity.getProjectid());
         try{
                SubsDAO subsDAO= DaoFactory.getSubsDAO();
                subsDAO.delete(subsEntitypk);
                transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
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
     public boolean doComment(CommentsEntity comment)throws DBException{
         Transaction transaction = DBService.getTransaction();
         try{
             CommentsDAO commentsDAO = DaoFactory.getCommentsDAO();
             commentsDAO.create(comment);
             transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
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
     public boolean createDialog(UsersEntity user1,UsersEntity user2)throws DBException{
         Transaction transaction = DBService.getTransaction();
         DialogEntity dialogEntity = new DialogEntity();
         dialogEntity.setId(null);
         dialogEntity.setOneUserId(user1.getLogin());
         dialogEntity.setTwoUserId(user2.getLogin());
         try{
             DialogDAO dialogDao = DaoFactory.getDialogDao();
             dialogDao.create(dialogEntity);
             transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
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
     public boolean addMessgae(MessageEntity message)throws DBException{
         Transaction transaction = DBService.getTransaction();
         try{
             MessageDAO dao= DaoFactory.getMessageDao();
             dao.create(message);
             transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
             throw new DBException(e);
         }
         return true;
     }

//     public boolean deleteMessage()throws DBException{
//         Transaction transaction =DBService.getTransaction();
//         try{
//
//             transaction.commit();
//         }catch (HibernateException | NoResultException e){
//             DBService.transactionRollback(transaction);
//             throw new DBException(e);
//         }
//         return true;
//     }

    /**
     *  Following the user's posts
     * @param follow obj which contain two login of user and of writer
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced with
     */
     public boolean followUser(FollowersEntity follow)throws DBException {
         Transaction transaction = DBService.getTransaction();
         try{
             FollowersDAO dao = DaoFactory.getFollowersDao();
             dao.create(follow);
             transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
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
     public boolean unfollowUser(FollowersEntity follow)throws DBException{
         Transaction transaction = DBService.getTransaction();
         try{
             FollowersDAO dao= DaoFactory.getFollowersDao();
             FollowersEntityPK followersEntityPK= new FollowersEntityPK();
             followersEntityPK.setLogin(follow.getLogin());
             followersEntityPK.setFollower(follow.getFollower());
             dao.delete(followersEntityPK);
             transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
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
    public boolean createProject(ProjectsEntity projectsEntity,UsersEntity usersEntity)throws  DBException{
        ProjectService service= ServiceFactory.getProjectService();
        service.create(projectsEntity);
        DevelopersEntity dev= new DevelopersEntity();
        dev.setLogin(usersEntity.getLogin());
        dev.setProjectid(projectsEntity.getProjectid());
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
    public boolean deleteProject(ProjectsEntity entity)throws DBException{
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
    public boolean requestProj(ProjectsEntity proj,UsersEntity user) throws DBException{
        Transaction transaction = DBService.getTransaction();
        RequestsEntity req= new RequestsEntity();
        req.setLogin(user.getLogin());
        req.setProjectid(proj.getProjectid());
        req.setProjpos(Projpos.DEVELOPER);
        req.setIsrequest(true);
        try{
            RequestsDAO dao = DaoFactory.getRequestsDAO();
            dao.create(req);
            transaction.commit();
        }catch (HibernateException | NoResultException e){
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }


    public  boolean donate(DonatersEntity donate) throws DBException{
        Transaction transaction= DBService.getTransaction();
        try{
            DonatersDAO dao= DaoFactory.getDonatersDAO();
            dao.create(donate);
            transaction.commit();
        }catch (HibernateException | NoResultException e){
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    return true;
    }

    public boolean approveInvite(RequestsEntity entity) throws DBException{
        Transaction transaction=null;
        try{
            DevelopersEntity dev= new DevelopersEntity();
            dev.setDescription("null");
            dev.setProjpos(Projpos.DEVELOPER);
            dev.setLogin(entity.getLogin());
            dev.setProjectid(entity.getProjectid());
            ProjectService ps=ServiceFactory.getProjectService();
            ps.addDeveloper(dev);
            transaction= DBService.getTransaction();
            RequestsDAO dao= DaoFactory.getRequestsDAO();
            RequestsEntityPK pk=new RequestsEntityPK();
            pk.setLogin(entity.getLogin());
            pk.setProjectid(entity.getProjectid());
            dao.delete(pk);
            transaction.commit();

        }catch (HibernateException | NoResultException e){
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    public boolean rejectInvite(RequestsEntity entity) throws DBException{
        Transaction transaction= null;
        try{
            RequestsDAO dao= DaoFactory.getRequestsDAO();
            RequestsEntityPK pk=new RequestsEntityPK();
            pk.setLogin(entity.getLogin());
            pk.setProjectid(entity.getProjectid());
            transaction= DBService.getTransaction();
            dao.delete(pk);
            transaction.commit();
        }catch (HibernateException | NoResultException e){
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    public  boolean newStatus(UsersEntity user, String status) throws DBException{
        Transaction transaction= DBService.getTransaction();
        try{
            UsersDAO dao= DaoFactory.getUsersDAO();
            user.setStatus(status);
            dao.update(user);
            transaction.commit();
        }catch (HibernateException | NoResultException e){
            DBService.transactionRollback(transaction);
            throw new DBException(e);

        }
        return true;
    }
}
