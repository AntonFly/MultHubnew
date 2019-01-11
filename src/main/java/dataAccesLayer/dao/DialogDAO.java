package dataAccesLayer.dao;

import dataAccesLayer.entity.Dialog;
import dataAccesLayer.util.DBService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class DialogDAO extends AbstractDao<Dialog,String> {
    DialogDAO(){super(Dialog.class);}

    @Override
    public List<Dialog> getAll() {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        List<Dialog> list=em.createNamedQuery("Dialog.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }

    @Override
    public void create(Dialog entity) {
        entity.setId(UUID.nameUUIDFromBytes((entity.getCreationtime().toString().getBytes())).toString());
        super.create(entity);
    }
}
