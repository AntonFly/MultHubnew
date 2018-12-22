package dao;

import entity.SubsEntity;
import entity.SubsEntityPK;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.query.Query;
import util.DBService;

import java.util.List;

public class SubsDAO extends AbstractDao<SubsEntity,String> {
    public SubsDAO() {
    }

    @Override
    public List<SubsEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from SubsEntity ", SubsEntity.class).list();

    }

    @Override
    public SubsEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(SubsEntity.class, id, LockMode.PESSIMISTIC_READ);
    }

    public SubsEntity getEntityById(SubsEntityPK key)
    {
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from SubsEntity where login = :paramLogin AND projectid = :paramProjId");
        query.setParameter("paramLogin",key.getLogin());
        query.setParameter("paramProjId",key.getProjectid());
        return (SubsEntity) query.uniqueResult();

    }

    public void delete(SubsEntityPK key)
    {
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("delete from SubsEntity where login = :paramLogin AND projectid = :paramProjId");
        query.setParameter("paramLogin",key.getLogin());
        query.setParameter("paramProjId",key.getProjectid());
        query.executeUpdate();
    }

    public List<SubsEntity> getProjectSubs(String projectid)
    {
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from SubsEntity where projectid =:paramId ");
        query.setParameter("paramId",projectid);

        return query.getResultList();
    }

    public  List<SubsEntity> getUserSubs(String login){
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery(" from SubsEntity where login = :paramLogin ");
        query.setParameter("paramLogin",login);
        return query.list();
    }

    public List<Object[]> getMostPopular(){
        SQLQuery query= DBService.getSessionFactory()
                .getCurrentSession()
                .createSQLQuery("select projectid,count(projectid) from subs group by projectid;");


        List<Object[]> rows = query.list();
//        for(Object[] row : rows){
//            String id =(String) row[0];
//            BigInteger count= (BigInteger) row[1];
//            System.out.println(id+" "+count);
        return rows;
    }
}


