package dataAccesLayer.dao;

import dataAccesLayer.util.DBService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;

;
@Stateless
abstract class AbstractDao<E, K> {
        final Class<E> param;
        public AbstractDao(Class<E> param) {
                this.param=param;
        }

        public abstract List<E> getAll();

        public  E getEntityById(K id){
                EntityManager em= DBService.getEntytiManager();
                em.getTransaction().begin();
                E entity = em.find(param,id);
                em.getTransaction().commit();
                return entity;
        }

        public  void update(E entity){
                EntityManager em= DBService.getEntytiManager();
                em.getTransaction().begin();
                        em.merge(entity);
                em.getTransaction().commit();

        }

        public  void delete(K id){
                EntityManager em= DBService.getEntytiManager();
                em.getTransaction().begin();
                E entity = em.find(param,id);
                em.getTransaction().commit();
                em.remove(entity);
        };

        public  void create(E entity){
                EntityManager em= DBService.getEntytiManager();
                em.getTransaction().begin();
                em.persist(entity);
                em.getTransaction().commit();
        }


}
