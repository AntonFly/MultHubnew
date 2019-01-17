package dataAccesLayer.dao;


import dataAccesLayer.entity.*;
import dataAccesLayer.util.DBService;

import javax.persistence.EntityManager;
import java.util.List;

public class CommitsfileDAO extends AbstractDao<Commitsfile,String> {
    CommitsfileDAO(){super(Commitsfile.class);}

    @Override
    public List<Commitsfile> getAll() {
        EntityManager em= DBService.getEntytiManager();
        List<Commitsfile> list=em.createNamedQuery("Commitsfile.getAll").getResultList();
        return list;
    }
    public void delete(CommitsfileEntityPK commitsfileEntityPK) {
        EntityManager em= DBService.getEntytiManager();
        Commitsfile entity = em.find(param,commitsfileEntityPK);
        em.remove(entity);

    }
}

