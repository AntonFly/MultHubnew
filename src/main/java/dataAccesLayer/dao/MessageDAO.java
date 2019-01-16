package dataAccesLayer.dao;

import dataAccesLayer.entity.Message;
import dataAccesLayer.util.DBService;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MessageDAO extends AbstractDao<Message,String> {

    MessageDAO(){super(Message.class);}

    @Override
    public List<Message> getAll() {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        List<Message> list=em.createNamedQuery("Message.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }
    public void readMessage(String dialogId, String sender){
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        Query query = em.createQuery("update Message set isread = true where dialogId ='" + dialogId +"' and isread = false and sender not like \'"+ sender +"\'");
        query.executeUpdate();
    }
}
