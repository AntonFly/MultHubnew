package dataAccesLayer.dao;

import dataAccesLayer.entity.Projectposts;
import dataAccesLayer.util.DBService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class ProjectspostsDAO extends AbstractDao<Projectposts,String> {
    ProjectspostsDAO() {super(Projectposts.class);}

    @Override
    public List<Projectposts> getAll() {
        EntityManager em = DBService.getEntytiManager();
        em.getTransaction().begin();
        List<Projectposts> list = em.createNamedQuery("Projectpost.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }

    @Override
    public void create(Projectposts entity) {
        entity.setId( UUID.nameUUIDFromBytes(entity.getText().getBytes()).toString());

        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }
}
