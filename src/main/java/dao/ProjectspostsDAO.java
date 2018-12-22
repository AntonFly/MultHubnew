package dao;

import entity.ProjectpostsEntity;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.query.Query;
import util.DBService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class ProjectspostsDAO extends AbstractDao<ProjectpostsEntity,String> {
    @Override
    public List<ProjectpostsEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsEntity ", ProjectpostsEntity.class).list();
    }

    /**
     *
     * do not use
     */
    @Override
    public ProjectpostsEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(ProjectpostsEntity.class,id, LockMode.PESSIMISTIC_READ);
    }

    public Serializable create(ProjectpostsEntity entity){
        entity.setId( UUID.nameUUIDFromBytes(entity.getText().getBytes()).toString());
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .save(entity);
    }

    public ProjectpostsEntity getProjLastPost(String projId){
        SQLQuery query= DBService.getSessionFactory()
                .getCurrentSession()
                .createSQLQuery("Select * from projectposts where projectid='"+projId+"' and time in(SELECT max(time ) from projectposts where projectid='"+projId+"');");
        query.addEntity(ProjectpostsEntity.class);

        return (ProjectpostsEntity) query.list().get(0);
        }


    public List<ProjectpostsEntity> getProjectPosts(String id){
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from ProjectpostsEntity where projectid =:paramId ");
        query.setParameter("paramId",id);

        return query.getResultList();
    }
}
