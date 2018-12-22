package dao;

import entity.DonatersEntity;
import entity.DonatersEntityPK;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;

import java.util.List;

public class DonatersDAO extends AbstractDao<DonatersEntity,String> {
    @Override
    public List<DonatersEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsEntity ", DonatersEntity.class).list();
    }

    /**
     *
     * do not use
     */
    @Override
    public DonatersEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(DonatersEntity.class,id, LockMode.PESSIMISTIC_READ);
    }

    public DonatersEntity getEntityById(DonatersEntityPK item){
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from DonatersEntity where  login = :paramLogin AND projectid = :paramProjId ");
        query.setParameter("paramLogin", item.getLogin());
        query.setParameter("paramProjId",item.getProjectid());

        return (DonatersEntity) query.uniqueResult();
    }

    public List<DonatersEntity> getProjectDonaters(String projectid)
    {
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from DonatersEntity where projectid =:paramId ");
        query.setParameter("paramId",projectid);

        return query.getResultList();
    }

}

