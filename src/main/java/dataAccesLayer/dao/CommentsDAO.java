package dataAccesLayer.dao;

import dataAccesLayer.entity.Comments;
import dataAccesLayer.util.DBService;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class CommentsDAO extends AbstractDao<Comments,String> {

    CommentsDAO(){super(Comments.class);}

    @Override
    public List<Comments> getAll() {
        EntityManager em= DBService.getEntytiManager();
        List<Comments> list=em.createNamedQuery("Comments.getAll").getResultList();
        return list;
    }

    @Override
    public void create(Comments entity) {
        entity.setId(UUID.nameUUIDFromBytes((entity.getComment()+entity.getLogin()).getBytes()).toString());
        super.create(entity);
    }

    public void delete(Comments comment) {
        String uuid=UUID.nameUUIDFromBytes((comment.getComment()+comment.getLogin()).getBytes()).toString();
        super.delete(uuid);
    }
    @Override
    public void delete(String id){
        super.delete(id);
    }
}
