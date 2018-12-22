package dao;

import entity.Developers;
import util.DBService;

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

}
