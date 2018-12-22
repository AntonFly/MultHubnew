package dao;


import entity.*;
import util.DBService;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class CommitsfileDAO extends AbstractDao<Commitsfile,String> {
    CommitsfileDAO(){super(Commitsfile.class);}

    @Override
    public List<Commitsfile> getAll() {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        List<Commitsfile> list=em.createNamedQuery("Commitsfile.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }
    public void delete(CommitsfileEntityPK commitsfileEntityPK) {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        Commitsfile entity = em.find(param,commitsfileEntityPK);
        em.remove(entity);
        em.getTransaction().commit();

    }
}

