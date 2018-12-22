package dao;

import entity.FollowersEntity;
import entity.FollowersEntityPK;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;

import java.util.List;

public class FollowersDAO extends AbstractDao<FollowersEntity,String> {

    @Override
    public List<FollowersEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from FollowersEntity ", FollowersEntity.class).list();
    }

    @Override
    public FollowersEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(FollowersEntity.class, id, LockMode.PESSIMISTIC_READ);
    }

    public void delete(FollowersEntityPK key)
    {
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("delete from FollowersEntity where login = :paramLogin AND follower = :paramProjId");
        query.setParameter("paramLogin",key.getLogin());
        query.setParameter("paramProjId",key.getFollower());
    }
    public List<FollowersEntity> getUserFollowers(String login) {

        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from FollowersEntity  where login= :Loginparam ");
        query.setParameter("Loginparam",login);
        return query.list();
    }
}
