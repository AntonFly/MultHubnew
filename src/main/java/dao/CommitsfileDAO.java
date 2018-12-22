package dao;

import entity.CommitsEntity;
import entity.CommitsfileEntity;
import entity.CommitsfileEntityPK;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.query.Query;
import util.DBService;

import java.util.List;
import java.util.UUID;

public class CommitsfileDAO extends AbstractDao<CommitsfileEntity,String> {
    @Override
    public List<CommitsfileEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsEntity ", CommitsfileEntity.class).list();
    }

    /**
     *
     * do not use
     */
    @Override
    public CommitsfileEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(CommitsfileEntity.class,id, LockMode.PESSIMISTIC_READ);
    }

    public CommitsfileEntity getEntityById(CommitsfileEntityPK item){
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsfileEntity where  filename = :filenameParam AND commitid = :commitidParam ");
        query.setParameter("filenameParam", item.getFilename());
        query.setParameter("commitidParam",item.getCommitid());

        return (CommitsfileEntity) query.uniqueResult();
    }

    public List<CommitsfileEntity> getAssociatedFiles(CommitsEntity commitsEntity){
        commitsEntity.setId(UUID.nameUUIDFromBytes((commitsEntity.getDeveloper()+commitsEntity.getProjectid()+commitsEntity.getTime()).getBytes()).toString());

        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsfileEntity where commitid =:paramApr ");
        query.setParameter("paramApr",commitsEntity.getId());

        return query.getResultList();
    }

    public void delete(CommitsfileEntityPK key)
    {
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("delete from CommitsfileEntity where commitid = :paramId AND filename = :paramFile");
        query.setParameter("paramId",key.getCommitid());
        query.setParameter("paramFile",key.getFilename());
        query.executeUpdate();
    }

    public List<CommitsfileEntity> getLatestMedea(String projId){
        SQLQuery query= DBService.getSessionFactory()
                .getCurrentSession()
                .createSQLQuery("SELECT * from (Select * from  commits where projectid='"+projId+"') as A    where A.time in ( Select max(time) from  commits where projectid='"+projId+"' ) ");
        query.addEntity(CommitsEntity.class);
        CommitsEntity commit= (CommitsEntity) query.list().get(0);
        Query query1= DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsfileEntity where commitid=:commitIdparemetr");
        query1.setParameter("commitIdparemetr",commit.getId());
        return query1.list();
    }

    public List<CommitsfileEntity> getFilesByPath(String filename){
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsfileEntity where filepath =:paramPath");
        query.setParameter("paramPath","%/"+filename);
        return query.getResultList();
    }

}

