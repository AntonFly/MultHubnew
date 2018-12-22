package dao;

import entity.DevelopersEntity;
import entity.DevelopersEntityPK;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;

import java.util.List;

public class DevelopersDAO extends AbstractDao<DevelopersEntity,String> {
    public DevelopersDAO() {
    }

    @Override
    public List<DevelopersEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from DevelopersEntity ", DevelopersEntity.class).list();

    }

    @Override
    public DevelopersEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(DevelopersEntity.class, id, LockMode.PESSIMISTIC_READ);
    }

    public DevelopersEntity getEntityById(DevelopersEntityPK key)
    {
         Query query = DBService.getSessionFactory()
            .getCurrentSession()
            .createQuery("from SubsEntity where login = :paramLogin AND projectid = :paramProjId");
        query.setParameter("paramLogin",key.getLogin());
        query.setParameter("paramProjId",key.getProjectid());
        return (DevelopersEntity) query.uniqueResult();
    }

    public void delete(DevelopersEntityPK key)
    {
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("delete from DevelopersEntity where login = :paramLogin AND projectid = :paramProjId");
        query.setParameter("paramLogin",key.getLogin());
        query.setParameter("paramProjId",key.getProjectid());
        query.executeUpdate();
    }

    public List<DevelopersEntity> getProjectDevs(String projectid){
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from DevelopersEntity where projectid =:paramId ");
        query.setParameter("paramId",projectid);

        return query.getResultList();
    }
    public List<DevelopersEntity> getUserProject(String login) {

        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from DevelopersEntity  where login= :Loginparam ");
        query.setParameter("Loginparam",login);
        return query.list();
    }

}
