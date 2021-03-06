package dataAccesLayer.dao;

import dataAccesLayer.entity.Projects;
import dataAccesLayer.entity.Subs;
import dataAccesLayer.entity.SubsEntityPK;
import dataAccesLayer.entity.Users;
import dataAccesLayer.util.DBService;

import javax.persistence.EntityManager;
import java.util.List;

public class SubsDAO extends AbstractDao<Subs,String> {
    SubsDAO(){super(Subs.class);}

    @Override
    public List<Subs> getAll() {
        EntityManager em= DBService.getEntytiManager();
//        em.getTransaction().begin();
        List<Subs> list=em.createNamedQuery("Subs.getAll").getResultList();
//        em.getTransaction().commit();
        return list;
    }



    public Subs getEntityById(Users user, Projects project) {
        EntityManager em= DBService.getEntytiManager();
//        em.getTransaction().begin();
        Subs entity = em.find(param,new SubsEntityPK(user,project));
//        em.getTransaction().commit();
        return entity;
    }

    public void delete(Users user,Projects project) {
        EntityManager em= DBService.getEntytiManager();
//        em.getTransaction().begin();
        Subs entity = em.find(param,new SubsEntityPK(user,project));
        em.remove(entity);
//        em.getTransaction().commit();
    }
}


