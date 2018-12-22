package dao;

import entity.Message;
import util.DBService;
import javax.persistence.EntityManager;
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
}
