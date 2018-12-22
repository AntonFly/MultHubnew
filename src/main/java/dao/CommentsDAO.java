package dao;

import entity.Comments;
import util.DBService;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class CommentsDAO extends AbstractDao<Comments,String> {

    CommentsDAO(){super(Comments.class);}

    @Override
    public List<Comments> getAll() {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        List<Comments> list=em.createNamedQuery("Comments.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }

    @Override
    public void create(Comments entity) {
        entity.setId(UUID.nameUUIDFromBytes((entity.getComment()+entity.getLogin()).getBytes()).toString());
        super.create(entity);
    }
}
