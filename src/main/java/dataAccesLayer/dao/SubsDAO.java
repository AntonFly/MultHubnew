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
        List<Subs> list=em.createNamedQuery("Subs.getAll").getResultList();
        return list;
    }



    public Subs getEntityById(Users user, Projects project) {
        EntityManager em= DBService.getEntytiManager();
        Subs entity = em.find(param,new SubsEntityPK(user,project));
        return entity;
    }

    public void delete(Users user,Projects project) {
        EntityManager em= DBService.getEntytiManager();
        Subs entity = em.find(param,new SubsEntityPK(user,project));
        em.remove(entity);
    }
}


