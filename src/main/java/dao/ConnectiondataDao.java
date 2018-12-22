package dao;

import entity.ConnectionData;
import util.DBService;
import javax.persistence.EntityManager;
import java.util.List;

public class ConnectiondataDao extends AbstractDao<ConnectionData,String> {
    ConnectiondataDao(){super(ConnectionData.class);}

    @Override
    public List<ConnectionData> getAll() {
        EntityManager em= DBService.getEntytiManager();
        em.getTransaction().begin();
        List<ConnectionData> list=em.createNamedQuery("ConnectionData.getAll").getResultList();
        em.getTransaction().commit();
        return list;
    }



}
