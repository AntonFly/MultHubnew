package dao;

import entity.Users;
import org.hibernate.LockMode;
import util.DBService;

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
}
