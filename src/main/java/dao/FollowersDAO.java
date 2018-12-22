package dao;


import util.DBService;
import javax.persistence.EntityManager;
import entity.Followers;
import java.util.List;

public class FollowersDAO extends AbstractDao<Followers,String> {
    FollowersDAO(){super(Followers.class);}

    @Override
    public List<Followers> getAll() {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        List<Followers> list=em.createNamedQuery("Followers.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }
}
