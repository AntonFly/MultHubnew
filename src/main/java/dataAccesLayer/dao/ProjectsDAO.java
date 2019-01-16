package dataAccesLayer.dao;

import dataAccesLayer.entity.Projects;
import dataAccesLayer.util.DBService;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class ProjectsDAO extends AbstractDao<Projects,String> {
    ProjectsDAO() {
        super(Projects.class);
    }

    @Override
    public List<Projects> getAll() {
        EntityManager em = DBService.getEntytiManager();
        em.getTransaction().begin();
        List<Projects> list = em.createNamedQuery("Projects.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }

    @Override
    public void create(Projects entity) {
        entity.setProjectid(UUID.nameUUIDFromBytes((entity.getName() + entity.getDescription()).getBytes()).toString());

        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public List<Projects> searchProjects(String namePart){
        EntityManager em = DBService.getEntytiManager();
//        em.getTransaction().begin();
        List<Projects> projects=em.createQuery("from Projects where name like '%"+namePart+"%'").getResultList();
//        em.getTransaction().commit();
        return  projects;
    }
}

