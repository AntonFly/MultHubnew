package dao;

import entity.RequestsEntity;
import entity.RequestsEntityPK;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;

import java.util.List;

public class RequestsDAO extends AbstractDao<RequestsEntity,String> {
    public RequestsDAO() {
    }

    @Override
    public List<RequestsEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from RequestsEntity ", RequestsEntity.class).list();

    }

    @Override
    public RequestsEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(RequestsEntity.class, id, LockMode.PESSIMISTIC_READ);
    }

    public RequestsEntity getEntityById(RequestsEntityPK key)
    {
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from DevelopersEntity where login = :paramLogin AND projectid = :paramProjId");
        query.setParameter("paramLogin",key.getLogin());
        query.setParameter("paramProjId",key.getProjectid());
        query.executeUpdate();
        return (RequestsEntity) query.uniqueResult();
    }

    public void delete(RequestsEntityPK key)
    {

        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("delete from RequestsEntity where login = :paramLogin AND projectid = :paramProjId");
        query.setParameter("paramLogin",key.getLogin());
        query.setParameter("paramProjId",key.getProjectid());
        query.executeUpdate();
    }

}
