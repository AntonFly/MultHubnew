package service;

import dao.*;
import entity.Approved;
import entity.CommitsfileEntityPK;
import entity.DevelopersEntityPK;
import entity.RequestsEntityPK;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import util.DBService;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;


public class ProjectService extends AbstractService<ProjectsEntity,String> {
    @Override
    public List<ProjectsEntity> getAll() throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ProjectsDAO dao = DaoFactory.getProjectsDAO();
            List<ProjectsEntity> list =  dao.getAll();

            transaction.commit();
            return list;
//            logger.fine("Create item " + user);

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }

    }

    /**
     * Generate new project
     * @param project - project obj
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    @Override
    public boolean create(ProjectsEntity project) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ProjectsDAO dao = DaoFactory.getProjectsDAO();
            dao.create(project);

            transaction.commit();

//            logger.fine("Create item " + user);

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     * @deprecated because of uuid id any change can cause errors
     * @param item project obj
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    @Override
    @Deprecated
    public boolean update(ProjectsEntity item) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ProjectsDAO dao = DaoFactory.getProjectsDAO();
            dao.update(item);

            transaction.commit();

//            logger.fine("Create item " + user);

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }


    @Override
    public ProjectsEntity get(String id) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ProjectsDAO dao = DaoFactory.getProjectsDAO();
            ProjectsEntity pe =dao.getEntityById(id);
            transaction.commit();
            return pe;
        }catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }

    @Override
    public boolean delete(String id) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try{
            ProjectsDAO dao= DaoFactory.getProjectsDAO();
            dao.delete(id);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     *  adds new row in developers
     * @param developersEntity Developer Entity object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean addDeveloper(DevelopersEntity developersEntity) throws DBException{
        Transaction transaction = DBService.getTransaction();
        try{
            DevelopersDAO developersDAO = DaoFactory.getDevelopersDAO();
            developersDAO.create(developersEntity);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     * removes row from Developers entity
     * @param developersEntity developerEntity obkect
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean deleteDeveloper(DevelopersEntity developersEntity) throws DBException{
        Transaction transaction = DBService.getTransaction();
        DevelopersEntityPK developersEntityPK = new DevelopersEntityPK();
        developersEntityPK.setLogin(developersEntity.getLogin());
        developersEntityPK.setProjectid(developersEntity.getProjectid());

        try{
            DevelopersDAO developersDAO = DaoFactory.getDevelopersDAO();
            developersDAO.delete(developersEntityPK);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     * adds new request to Requests entity. isRequest = false
     * @param requestsEntity requestEntity object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean sendInviteToProject(RequestsEntity requestsEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        requestsEntity.setIsrequest(false);
        try{
            RequestsDAO requestsDAO = DaoFactory.getRequestsDAO();
            requestsDAO.create(requestsEntity);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     * adds new row in project posts entity
     * shifts the responsibility of proj obj init to the upper level
     * @param projectpostsEntity  projectpostsEntity object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean addPostToBlog(ProjectpostsEntity projectpostsEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();

        try{
            ProjectspostsDAO projectspostsDAO = DaoFactory.getProjectspostsDAO();
            projectspostsDAO.create(projectpostsEntity);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     * deletes row in project posts entity
     * @param projectpostsEntity post obj without a key
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean deletePostInBlog(ProjectpostsEntity projectpostsEntity) throws DBException{
        Transaction transaction = DBService.getTransaction();
        try{
            ProjectspostsDAO projectspostsDAO = DaoFactory.getProjectspostsDAO();
            projectspostsDAO.delete(UUID.nameUUIDFromBytes(projectpostsEntity.getText().getBytes()).toString());

            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     *  deletes request row and creates new developer
     * @param requestsEntity request object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean approveRequest(RequestsEntity requestsEntity)throws DBException{
        Transaction transaction = null;
        RequestsEntityPK requestsEntityPK = new RequestsEntityPK();
        requestsEntityPK.setLogin(requestsEntity.getLogin());
        requestsEntityPK.setProjectid(requestsEntity.getProjectid());
        try{
            DevelopersEntity developersEntity = new DevelopersEntity();
            developersEntity.setProjectid(requestsEntity.getProjectid());
            developersEntity.setLogin(requestsEntity.getLogin());
            developersEntity.setDescription("empty");
            developersEntity.setProjpos(requestsEntity.getProjpos());
            addDeveloper(developersEntity);
            transaction = DBService.getTransaction();
            RequestsDAO requestsDAO = DaoFactory.getRequestsDAO();
            requestsDAO.delete(requestsEntityPK);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     * deletes row in request entity
     * @param requestsEntity request object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean rejectRequest(RequestsEntity requestsEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        RequestsEntityPK requestsEntityPK = new RequestsEntityPK();
        requestsEntityPK.setLogin(requestsEntity.getLogin());
        requestsEntityPK.setProjectid(requestsEntity.getProjectid());
        try{
            RequestsDAO requestsDAO = DaoFactory.getRequestsDAO();
            requestsDAO.delete(requestsEntityPK);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     * updates developer row in developers entity
     * @param developersEntity developersEntitu object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean updateDeveloper(DevelopersEntity developersEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        try{
            DevelopersDAO developersDAO = DaoFactory.getDevelopersDAO();
            developersDAO.update(developersEntity);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     * adds new row in creditInfo entity
     * @param creditinfoEntity creditinfoEntity object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean addCreditInfo(CreditinfoEntity creditinfoEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        try{
             CreditInfoDAO creditInfoDAO = DaoFactory.getCreditInfoDAO();
             creditInfoDAO.create(creditinfoEntity);
             transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     * deletes row in creditInfo entiy
     * @param creditinfoEntity creditInfo object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean deleteCreditInfo(CreditinfoEntity creditinfoEntity)throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            CreditInfoDAO creditInfoDAO = DaoFactory.getCreditInfoDAO();
            creditInfoDAO.delete(creditinfoEntity.getProjectid());
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }
    //////////////////////////////////////////////////////      COMMIT       //////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * sets APPROVED commit row, so it won't be shown to manager in unchecked commits
     * @param commitsEntity commitsEntity object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean approveCommit( CommitsEntity commitsEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        commitsEntity.setId(UUID.nameUUIDFromBytes(   (commitsEntity.getDeveloper()+commitsEntity.getProjectid()+commitsEntity.getTime())   .getBytes()  ).toString());
        commitsEntity.setApproved(Approved.APPROVED);
        try{
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            commitsDao.update(commitsEntity);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }
    //the same as in approve

    /**
     * deletes row in commit entity and connected files
     * @param commitsEntity comitsEntity object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean rejectCommit(CommitsEntity commitsEntity)throws DBException{
        commitsEntity.setId(UUID.nameUUIDFromBytes(   (commitsEntity.getDeveloper()+commitsEntity.getProjectid()+commitsEntity.getTime())   .getBytes()  ).toString());
        deleteCommit(commitsEntity);
        return true;
    }

    /**
     * adds new commit and connected files
     * @param commitsEntity commitsEntity obj
     * @param commitsfileEntities list of Commitsfile objects
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean commitFiles(CommitsEntity commitsEntity,List<CommitsfileEntity> commitsfileEntities )throws DBException{
        Transaction transaction = DBService.getTransaction();
        try{
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            commitsDao.create(commitsEntity);
            CommitsfileDAO commitsfileDAO = DaoFactory.getCommitsfileDAO();
            for (CommitsfileEntity file :commitsfileEntities) {
                commitsfileDAO.create(file);
            }
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }

        return true;
    }


    /**
     * get new commits from commits entity
     * @return list of not approved commits
     * @throws DBException Hiber exceptions replaced with
     */
    public List<CommitsEntity> getUncheckedCommits()throws DBException{ //непонятки с commitsEntity
        Transaction transaction = DBService.getTransaction();
        List<CommitsEntity> commits;
        try{
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            commits = commitsDao.getUnchecked();
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return commits;
    }

    /**
     * get commit's files
     * @param commitsEntity commitEntity obect
     * @return list of files connected with commit
     * @throws DBException Hiber exceptions replaced with
     */
    public List<CommitsfileEntity> getCommitFiles(CommitsEntity commitsEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        List<CommitsfileEntity> commits;
        try{
            CommitsfileDAO commitsDao = DaoFactory.getCommitsfileDAO();
            commits = commitsDao.getAssociatedFiles(  commitsEntity );
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return commits;
    }

    /**
     * deletes commit from commits entity
     * @param commitsEntity commitsEntity object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean deleteCommit(CommitsEntity commitsEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        try{
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            commitsDao.delete(commitsEntity);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }

        return true;
    }

    /**
     * deletes certain file from db
     * @param commitsfileEntity comitsfileEntity object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean deleteCommitFile(CommitsfileEntity commitsfileEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        CommitsfileEntityPK commitsfileEntityPK = new CommitsfileEntityPK();
        commitsfileEntityPK.setCommitid(commitsfileEntity.getCommitid());
        commitsfileEntityPK.setFilename(commitsfileEntity.getFilename());
        try{
            CommitsfileDAO commitsfileDAO = DaoFactory.getCommitsfileDAO();
            commitsfileDAO.delete(commitsfileEntityPK);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }

        return true;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * deletes comment
     * @param commentsEntity commentEntity object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean deleteComment(CommentsEntity commentsEntity)throws DBException{
        try {
            ServiceFactory.getUserService().deleteComment(commentsEntity);
        }
        catch (Exception ex){
            throw new DBException(ex);
        }
        return true;
    }

}
