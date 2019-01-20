package dataAccesLayer.dao;

import dataAccesLayer.entity.Creditinfo;
import dataAccesLayer.util.DBService;

import javax.persistence.EntityManager;
import java.util.List;

public class CreditInfoDAO extends AbstractDao<Creditinfo,String> {

    CreditInfoDAO(){super(Creditinfo.class);}

    @Override
    public List<Creditinfo> getAll() {
        EntityManager em= DBService.getEntytiManager();
//        em.getTransaction().begin();
        List<Creditinfo> list=em.createNamedQuery("Creditinfo.getAll").getResultList();
//        em.getTransaction().commit();
        return list;
    }

}
