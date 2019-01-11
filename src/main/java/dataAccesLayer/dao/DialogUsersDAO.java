package dataAccesLayer.dao;

import dataAccesLayer.entity.*;

import java.util.List;

public class DialogUsersDAO extends AbstractDao<DialogUsers,String> {
    DialogUsersDAO(){super(DialogUsers.class);}

    @Override
    public List<DialogUsers> getAll() {

        List<DialogUsers> list=null;
        return list;
    }

//    public Subs getEntityById(Users user, Projects project) {
//        EntityManager em= DBService.getEntytiManager();
//        em.getTransaction().begin();
//        Subs dataAccesLayer.entity = em.find(param,new SubsEntityPK(user,project));
//        em.getTransaction().commit();
//        return dataAccesLayer.entity;
//    }

//    public void delete(Users user,Projects project) {
//        EntityManager em= DBService.getEntytiManager();
//        em.getTransaction().begin();
//        Subs dataAccesLayer.entity = em.find(param,new SubsEntityPK(user,project));
//        em.remove(dataAccesLayer.entity);
//        em.getTransaction().commit();
//    }
}


