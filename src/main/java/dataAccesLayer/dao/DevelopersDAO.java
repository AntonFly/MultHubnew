package dataAccesLayer.dao;

import dataAccesLayer.entity.*;
import dataAccesLayer.util.DBService;

import javax.persistence.EntityManager;
import java.util.List;

public class DevelopersDAO extends AbstractDao<Developers,String> {
    DevelopersDAO(){super(Developers.class);}

    @Override
    public List<Developers> getAll() {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        List<Developers> list=em.createNamedQuery("Developers.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }

    public Developers getEntityById(Users user, Projects project) {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        Developers entity = em.find(param,new DevelopersEntityPK(user,project));
        em.getTransaction().commit();
        return entity;
    }


    public void delete(DevelopersEntityPK developersEntityPK) {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        Developers entity = em.find(param,developersEntityPK);
        em.getTransaction().commit();
        em.remove(entity);

    }
}
