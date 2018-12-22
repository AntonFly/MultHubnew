package dao;


import entity.Commitsfile;
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

}

