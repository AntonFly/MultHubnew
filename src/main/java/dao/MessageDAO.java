package dao;

import entity.MessageEntity;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.query.Query;
import util.DBService;

import java.util.List;

public class MessageDAO extends AbstractDao<MessageEntity,String> {

    @Override
    public List<MessageEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from MessageEntity ", MessageEntity.class).list();
    }

    @Override
    public MessageEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(MessageEntity.class, id, LockMode.PESSIMISTIC_READ);
    }

    public MessageEntity getLastMessage(String id){
         SQLQuery query= DBService.getSessionFactory()
                .getCurrentSession()
                .createSQLQuery("Select * from message where to_user_id='"+id+"'or user_id='"+id+"' and time in(SELECT max(time ) from message );");
         query.addEntity(MessageEntity.class);

         return (MessageEntity)query.list().get(0);
    }

    public List<MessageEntity> getDialogMessages(String id){
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from MessageEntity  where dialogId= :Loginparam ");
        query.setParameter("Loginparam",id);
        return  query.list();
    }
}
