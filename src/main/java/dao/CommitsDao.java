package dao;

import entity.Approved;
import entity.CommitsEntity;
import entity.CommitsfileEntity;
import entity.ProjectsEntity;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.query.Query;
import util.DBService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class CommitsDao extends AbstractDao<CommitsEntity,String> {
    @Override
    public List<CommitsEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsEntity ", CommitsEntity.class).list();
    }

    /**
     *
     * do not use
     */
    @Override
    public CommitsEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(CommitsEntity.class,id, LockMode.PESSIMISTIC_READ);
    }

    public Serializable create(CommitsEntity entity){
        entity.setId( UUID.nameUUIDFromBytes(   (entity.getDeveloper()+entity.getProjectid()+entity.getTime())   .getBytes()  ).toString() );
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .save(entity);
    }

    public void delete(CommitsEntity commitsEntity){
        commitsEntity.setId(UUID.nameUUIDFromBytes((commitsEntity.getDeveloper()+commitsEntity.getProjectid()+commitsEntity.getTime()).getBytes()).toString());
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("delete from CommitsEntity where id = :paramId");
        query.setParameter("paramId",commitsEntity.getId());
        query.executeUpdate();
    }

    public List<CommitsEntity> getUnchecked(){
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsEntity where approved =:paramApr ");
        query.setParameter("paramApr", Approved.AWAITS);

        return query.getResultList();
    }

    public List<CommitsEntity> getCommits(String projectid){
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsEntity where projectid =:paramId AND approved =:paramAp");
        query.setParameter("paramId",projectid);
        query.setParameter("paramAp", Approved.APPROVED);

        return query.getResultList();
    }

    public List<Object[]> getCommitsFiles(ProjectsEntity projectsEntity){
            SQLQuery query= DBService.getSessionFactory()
                    .getCurrentSession()
                    .createSQLQuery("select * from projects inner join commits on commits.projectid =projects.projectid AND projects.projectid='"+projectsEntity.getProjectid()+"' and commits.approved LIKE 'APPROVED' inner join commitsfile on commits.id = commitsfile.commitid inner join (SELECT max(time),filepath from commits inner join commitsfile on commits.id = commitsfile.commitid group by filepath) as A on time = A.max AND A.filepath = commitsfile.filepath");
            //query.setParametr("paramID",projectsEntity.getProjectid());
        //developer,time,filename,commitsfile.filepath
            query.addEntity(ProjectsEntity.class);
            query.addEntity(CommitsEntity.class);
            query.addEntity(CommitsfileEntity.class);

            return query.list();
        }

    public List<Object[]> getUncheckCommitsFiles(ProjectsEntity projectsEntity){
        SQLQuery query= DBService.getSessionFactory()
                .getCurrentSession()
                .createSQLQuery("select * from projects inner join commits on commits.projectid =projects.projectid AND projects.projectid='"+projectsEntity.getProjectid()+"' and commits.approved LIKE 'AWAITS'inner join commitsfile on commits.id = commitsfile.commitid inner join (SELECT max(time),filepath from commits inner join commitsfile on commits.id = commitsfile.commitid group by filepath) as A on time = A.max AND A.filepath = commitsfile.filepath");
        //query.setParametr("paramID",projectsEntity.getProjectid());
        //developer,time,filename,commitsfile.filepath
        query.addEntity(ProjectsEntity.class);
        query.addEntity(CommitsEntity.class);
        query.addEntity(CommitsfileEntity.class);

        return query.list();
    }

//    public void getCommitsOnly(String filedirectory){
//        SQLQuery query=DBService.getSessionFactory()
//                .getCurrentSession()
//                .createSQLQuery("");
//
//        query.addEntity(ProjectsEntity.class);
//        query.addEntity(CommitsEntity.class);
//        query.addEntity(CommitsfileEntity.class);
//
//        return query.list();
//    }

}
