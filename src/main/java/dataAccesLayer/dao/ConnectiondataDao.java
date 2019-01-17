package dataAccesLayer.dao;

import dataAccesLayer.entity.ConnectionData;
import dataAccesLayer.util.DBService;
import javax.persistence.EntityManager;
import java.util.List;

public class ConnectiondataDao extends AbstractDao<ConnectionData,String> {
    ConnectiondataDao(){super(ConnectionData.class);}

    @Override
    public List<ConnectionData> getAll() {
        EntityManager em= DBService.getEntytiManager();
        List<ConnectionData> list=em.createNamedQuery("ConnectionData.getAll").getResultList();
        return list;
    }



}
