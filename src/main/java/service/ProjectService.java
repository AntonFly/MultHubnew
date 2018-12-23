package service;

import dao.*;
import entity.*;
import exception.DBException;
import org.hibernate.Transaction;
import util.DBService;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.UUID;


public class ProjectService extends AbstractService<Projects,String> {


    @Override
    public List<Projects> getAll() throws DBException {
        try {
            ProjectsDAO dao = DaoFactory.getProjectsDAO();
            List<Projects> list =  dao.getAll();
            return list;
        } catch (PersistenceException e) {
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
    public boolean create(Projects project) throws DBException {
        try {
            ProjectsDAO dao = DaoFactory.getProjectsDAO();
            dao.create(project);
        } catch (PersistenceException e) {
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
    public boolean update(Projects item) throws DBException {
        try {
            ProjectsDAO dao = DaoFactory.getProjectsDAO();
            dao.update(item);
        } catch (PersistenceException e) {
            throw new DBException(e);
        }
        return true;
    }


    @Override
    public Projects get(String id) throws DBException {
        try {
            ProjectsDAO dao = DaoFactory.getProjectsDAO();
            Projects pe =dao.getEntityById(id);
            return pe;
        }catch (PersistenceException e) {
            throw new DBException(e);
        }
    }

    @Override
    public boolean delete(String id) throws DBException {
        try{
            ProjectsDAO dao= DaoFactory.getProjectsDAO();
            dao.delete(id);
        } catch (PersistenceException e) {
            throw new DBException(e);
        }
        return true;
    }
/////////////////////////////////////////////////////////////////////////////////



    /**
     *  adds new row in developers
     * @param developersEntity Developer Entity object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean addDeveloper(Developers developersEntity) throws DBException{
        try{
            DevelopersDAO developersDAO = DaoFactory.getDevelopersDAO();
            developersDAO.create(developersEntity);
        } catch (PersistenceException e) {
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
    public boolean deleteDeveloper(Developers developersEntity) throws DBException{
        DevelopersEntityPK developersEntityPK = new DevelopersEntityPK();
        developersEntityPK.setLogin(developersEntity.getLogin());
        developersEntityPK.setProjectid(developersEntity.getProjectid());

        try{
            DevelopersDAO developersDAO = DaoFactory.getDevelopersDAO();
            developersDAO.delete(developersEntityPK);
        } catch (PersistenceException e) {
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
    public boolean sendInviteToProject(Requests requestsEntity)throws DBException{
        requestsEntity.setIsrequest(false);
        try{
            RequestsDAO requestsDAO = DaoFactory.getRequestsDAO();
            requestsDAO.create(requestsEntity);
        } catch (PersistenceException e) {
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
    public boolean addPostToBlog(Projectposts projectpostsEntity)throws DBException{
        try{
            ProjectspostsDAO projectspostsDAO = DaoFactory.getProjectspostsDAO();
            projectspostsDAO.create(projectpostsEntity);
        } catch (PersistenceException e) {
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
    public boolean deletePostInBlog(Projectposts projectpostsEntity) throws DBException{
        try{
            ProjectspostsDAO projectspostsDAO = DaoFactory.getProjectspostsDAO();
            projectspostsDAO.delete(UUID.nameUUIDFromBytes(projectpostsEntity.getText().getBytes()).toString());
        } catch (PersistenceException e) {
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
    public boolean approveRequest(Requests requestsEntity)throws DBException{
        RequestsEntityPK requestsEntityPK = new RequestsEntityPK();
        requestsEntityPK.setLogin(requestsEntity.getLogin());
        requestsEntityPK.setProjectid(requestsEntity.getProjectid());
        try{
            Developers developersEntity = new Developers();
            developersEntity.setProjectid(requestsEntity.getProjectid());
            developersEntity.setLogin(requestsEntity.getLogin());
            developersEntity.setDescription("empty");
            developersEntity.setProjpos(requestsEntity.getProjpos());
            addDeveloper(developersEntity);
            RequestsDAO requestsDAO = DaoFactory.getRequestsDAO();
            requestsDAO.delete(requestsEntityPK);
        } catch (PersistenceException e) {
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
    public boolean rejectRequest(Requests requestsEntity)throws DBException{
        RequestsEntityPK requestsEntityPK = new RequestsEntityPK();
        requestsEntityPK.setLogin(requestsEntity.getLogin());
        requestsEntityPK.setProjectid(requestsEntity.getProjectid());
        try{
            RequestsDAO requestsDAO = DaoFactory.getRequestsDAO();
            requestsDAO.delete(requestsEntityPK);
        } catch (PersistenceException e) {
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
    public boolean updateDeveloper(Developers developersEntity)throws DBException{
        try{
            DevelopersDAO developersDAO = DaoFactory.getDevelopersDAO();
            developersDAO.update(developersEntity);
        } catch (PersistenceException e) {
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
    public boolean addCreditInfo(Creditinfo creditinfoEntity)throws DBException{
        try{
             CreditInfoDAO creditInfoDAO = DaoFactory.getCreditInfoDAO();
             creditInfoDAO.create(creditinfoEntity);
        } catch (PersistenceException e) {
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
    public boolean deleteCreditInfo(Creditinfo creditinfoEntity)throws DBException {
        try {
            CreditInfoDAO creditInfoDAO = DaoFactory.getCreditInfoDAO();
            creditInfoDAO.delete(creditinfoEntity.getProjectid());
        } catch (PersistenceException e) {
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
    public boolean approveCommit( Commits commitsEntity)throws DBException{
        commitsEntity.setId(UUID.nameUUIDFromBytes(   (commitsEntity.getDeveloper()+commitsEntity.getProjectid()+commitsEntity.getTime())   .getBytes()  ).toString());
        commitsEntity.setApproved(Approved.APPROVED);
        try{
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            commitsDao.update(commitsEntity);
        } catch (PersistenceException e) {
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
    public boolean rejectCommit(Commits commitsEntity)throws DBException{
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
    public boolean commitFiles(Commits commitsEntity,List<Commitsfile> commitsfileEntities )throws DBException{
        try{
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            commitsDao.create(commitsEntity);
            CommitsfileDAO commitsfileDAO = DaoFactory.getCommitsfileDAO();
            for (Commitsfile file :commitsfileEntities) {
                commitsfileDAO.create(file);
            }
        } catch (PersistenceException e) {
            throw new DBException(e);
        }

        return true;
    }


    /**
     * get new commits from commits entity
     * @return list of not approved commits
     * @throws DBException Hiber exceptions replaced with
     */
    public List<Commits> getUncheckedCommits()throws DBException{ //непонятки с commitsEntity
        List<Commits> commits;
        try{
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            commits = commitsDao.getUnchecked();
        } catch (PersistenceException e) {
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
    public List<Commitsfile> getCommitFiles(Commits commitsEntity)throws DBException{

        List<Commitsfile> commits;
        try{
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            commits = commitsDao.getEntityById(  UUID.nameUUIDFromBytes((commitsEntity.getDeveloper()+commitsEntity.getProjectid()+commitsEntity.getTime()).getBytes()).toString() ).getCommitsfile();
        } catch (PersistenceException e) {
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
    public boolean deleteCommit(Commits commitsEntity)throws DBException{
        try{
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            commitsDao.delete(commitsEntity);
        } catch (PersistenceException e) {

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
    public boolean deleteCommitFile(Commitsfile commitsfileEntity)throws DBException{

        CommitsfileEntityPK commitsfileEntityPK = new CommitsfileEntityPK();
        commitsfileEntityPK.setCommitid(commitsfileEntity.getCommitid());
        commitsfileEntityPK.setFilename(commitsfileEntity.getFilename());
        try{
            CommitsfileDAO commitsfileDAO = DaoFactory.getCommitsfileDAO();
            commitsfileDAO.delete(commitsfileEntityPK);
        } catch (PersistenceException e) {
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
    public boolean deleteComment(Comments commentsEntity)throws DBException{
        try {
            ServiceFactory.getUserService().deleteComment(commentsEntity);
        }
        catch (PersistenceException ex){
            throw new DBException(ex);
        }
        return true;
    }

}