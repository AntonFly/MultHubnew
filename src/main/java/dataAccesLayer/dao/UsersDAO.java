package dataAccesLayer.dao;

import dataAccesLayer.entity.Users;
import dataAccesLayer.util.DBService;

import javax.persistence.EntityManager;
import java.util.List;

public class UsersDAO extends AbstractDao<Users,String> {
    public UsersDAO() {
        super(Users.class);
    }

    @Override
    public List<Users> getAll() {
            EntityManager em= DBService.getEntytiManager();
            em.getTransaction().begin();
            List<Users> list=em.createNamedQuery("Users.getAll").getResultList();
            em.getTransaction().commit();
            return list;
    }

    public  void  detach(Users user){
        EntityManager em= DBService.getEntytiManager();
        em.detach(user);
    }

    public List<Users> searchUsers(String namePart){
        EntityManager em = DBService.getEntytiManager();
//        em.getTransaction().begin();
        List<Users> projects=em.createQuery("from Users where login like '%"+namePart+"%'").getResultList();
//        em.getTransaction().commit();
        return  projects;
    }
}
