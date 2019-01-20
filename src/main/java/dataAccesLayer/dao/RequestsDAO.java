package dataAccesLayer.dao;

import dataAccesLayer.entity.Projects;
import dataAccesLayer.entity.Requests;

import dataAccesLayer.entity.RequestsEntityPK;
import dataAccesLayer.entity.Users;
import dataAccesLayer.util.DBService;

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


    public Requests getEntityById(Users user, Projects project) {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        Requests entity = em.find(param,new RequestsEntityPK(user,project));
        em.getTransaction().commit();
        return entity;
    }


    public void delete(RequestsEntityPK developersEntityPK) {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        Requests entity = em.find(Requests.class,developersEntityPK);
        em.remove(entity);
        em.getTransaction().commit();

    }


}
