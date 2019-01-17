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
                E entity = em.find(param,id);
                return entity;
        }

        public  void update(E entity){
                EntityManager em= DBService.getEntytiManager();
                        em.merge(entity);

        }

        public  void delete(K id){
                EntityManager em= DBService.getEntytiManager();
                E entity = em.find(param,id);
                em.remove(entity);
        };

        public  void create(E entity){
                EntityManager em= DBService.getEntytiManager();
                em.persist(entity);
        }


}
