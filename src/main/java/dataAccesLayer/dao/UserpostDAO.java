package dataAccesLayer.dao;

import dataAccesLayer.entity.Userpost;
import dataAccesLayer.util.DBService;
import javax.persistence.EntityManager;
import java.util.List;

public class UserpostDAO extends AbstractDao<Userpost,String> {

    UserpostDAO(){super(Userpost.class);}

    @Override
    public List<Userpost> getAll() {
        EntityManager em= DBService.getEntytiManager();
        List<Userpost> list=em.createNamedQuery("Userpost.getAll").getResultList();
        return list;
    }
}