package dao;

import entity.CommentsEntity;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class CommentsDAO extends AbstractDao<CommentsEntity,String> {


    @Override
    public List<CommentsEntity> getAll() {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommentsEntity ", CommentsEntity.class).list();
    }

    @Override
    public CommentsEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(CommentsEntity.class, id, LockMode.PESSIMISTIC_READ);
    }


    public void delete(CommentsEntity comment) {
        String uuid=UUID.nameUUIDFromBytes((comment.getComment()+comment.getLogin()).getBytes()).toString();
        delete(uuid);

    }


    public Serializable create(CommentsEntity entity) {
        entity.setId(UUID.nameUUIDFromBytes((entity.getComment()+entity.getLogin()).getBytes()).toString());
        return super.create(entity);
    }

    public List<CommentsEntity> getProjectComments(String projectid)
    {
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommentsEntity where projectid =:paramId ");
        query.setParameter("paramId",projectid);

        return query.getResultList();
    }

}
