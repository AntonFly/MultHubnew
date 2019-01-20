package dataAccesLayer.dao;


import dataAccesLayer.entity.*;
import dataAccesLayer.util.DBService;

import javax.persistence.EntityManager;
import java.util.List;

public class FollowersDAO extends AbstractDao<Followers,String> {
    FollowersDAO(){super(Followers.class);}

    @Override
    public List<Followers> getAll() {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        List<Followers> list=em.createNamedQuery("Followers.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }

    public void delete(FollowersPK pk) {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        Followers entity = em.find(param,pk);
        em.remove(entity);
        em.getTransaction().commit();
    }
}
