package dao;

import entity.Requests;

import util.DBService;

import javax.persistence.EntityManager;
import java.util.List;

public class RequestsDAO extends AbstractDao<Requests,String> {
    RequestsDAO(){super(Requests.class);}

    @Override
    public List<Requests> getAll() {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        List<Requests> list=em.createNamedQuery("Requests.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }

}
