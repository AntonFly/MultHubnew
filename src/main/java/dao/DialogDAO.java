package dao;

import entity.DialogEntity;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class DialogDAO extends AbstractDao<DialogEntity,String> {
    public DialogDAO(){}

    @Override
    public List<DialogEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from DialogEntity ", DialogEntity.class).list();
    }

    @Override
    public DialogEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(DialogEntity.class, id, LockMode.PESSIMISTIC_READ);
    }

    public Serializable create(DialogEntity entity){
        entity.setId(UUID.nameUUIDFromBytes((entity.getOneUserId()+entity.getTwoUserId()).getBytes()).toString());
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .save(entity);
    }

    public List<DialogEntity> getUserDialogs(String login) {

        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from DialogEntity  where oneUserId= :Loginparam  or twoUserId =:Loginparam");
        query.setParameter("Loginparam",login);
        return query.list();
    }
}
