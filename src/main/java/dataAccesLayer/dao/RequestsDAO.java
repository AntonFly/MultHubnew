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
        List<Requests> list=em.createNamedQuery("Requests.getAll").getResultList();
        return list;
    }


    public Requests getEntityById(Users user, Projects project) {
        EntityManager em= DBService.getEntytiManager();
        Requests entity = em.find(param,new RequestsEntityPK(user,project));
        return entity;
    }


    public void delete(RequestsEntityPK developersEntityPK) {
        EntityManager em= DBService.getEntytiManager();
        Requests entity = em.find(param,developersEntityPK);
        em.remove(entity);

    }


}
