package dao;

import entity.Subs;
import util.DBService;

import javax.persistence.EntityManager;
import java.util.List;

public class SubsDAO extends AbstractDao<Subs,String> {
    SubsDAO(){super(Subs.class);}

    @Override
    public List<Subs> getAll() {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        List<Subs> list=em.createNamedQuery("Subs.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }
}


