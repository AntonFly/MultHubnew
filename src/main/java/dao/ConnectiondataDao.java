package dao;

import entity.ConnectiondataEntity;
import org.hibernate.LockMode;
import util.DBService;

import java.util.List;

public class ConnectiondataDao extends AbstractDao<ConnectiondataEntity,String> {
    @Override
    public List<ConnectiondataEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from ConnectiondataEntity ", ConnectiondataEntity.class).list();

    }

    @Override
    public ConnectiondataEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(ConnectiondataEntity.class, id, LockMode.PESSIMISTIC_READ);
    }




}
