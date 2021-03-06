package dataAccesLayer.dao;

import dataAccesLayer.entity.Commits;
import dataAccesLayer.util.DBService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class CommitsDao extends AbstractDao<Commits,String> {

    CommitsDao(){super(Commits.class);}

    @Override
    public List<Commits> getAll() {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        List<Commits> list=em.createNamedQuery("Commits.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }

    @Override
    public void create(Commits entity) {
        entity.setId( UUID.nameUUIDFromBytes(   (entity.getDeveloper()+entity.getProjectid()+entity.getTime())   .getBytes()  ).toString() );

        EntityManager em= DBService.getEntytiManager();
//        em.getTransaction().begin();
        em.persist(entity);
//        em.getTransaction().commit();
    }
    public void delete(Commits commitsEntity){
        commitsEntity.setId(UUID.nameUUIDFromBytes((commitsEntity.getDeveloper()+commitsEntity.getProjectid()+commitsEntity.getTime()).getBytes()).toString());
        EntityManager em= DBService.getEntytiManager();
//        em.getTransaction().begin();
        Commits entity = em.find(param,commitsEntity.getId());
        em.remove(entity);
//        em.getTransaction().commit();
    }
    public List<Commits> getUnchecked(){

        EntityManager em= DBService.getEntytiManager();
//        em.getTransaction().begin();
        return em.createQuery("from Commits where approved ='AWAITS'").getResultList();
    }

}
