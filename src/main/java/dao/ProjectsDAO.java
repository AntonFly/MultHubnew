package dao;

import entity.ProjectsEntity;
import org.hibernate.LockMode;
import util.DBService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class ProjectsDAO extends AbstractDao<ProjectsEntity,String> {
    public ProjectsDAO(){}

    @Override
    public List<ProjectsEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from ProjectsEntity ", ProjectsEntity.class).list();
    }

    @Override
    public ProjectsEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(ProjectsEntity.class, id, LockMode.PESSIMISTIC_READ);
    }

    public Serializable create(ProjectsEntity entity){
        entity.setProjectid( UUID.nameUUIDFromBytes((entity.getName()+entity.getDescription()).getBytes()).toString());
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .save(entity);
    }

}

