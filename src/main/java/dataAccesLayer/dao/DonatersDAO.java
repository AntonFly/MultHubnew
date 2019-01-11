package dataAccesLayer.dao;

import dataAccesLayer.entity.Donaters;
import dataAccesLayer.util.DBService;

import javax.persistence.EntityManager;
import java.util.List;

public class DonatersDAO extends AbstractDao<Donaters,String> {

    DonatersDAO(){super(Donaters.class);}

    @Override
    public List<Donaters> getAll() {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        List<Donaters> list=em.createNamedQuery("Donaters.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }

}

