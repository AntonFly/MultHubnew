package dao;

import entity.CreditinfoEntity;
import org.hibernate.LockMode;
import util.DBService;

import java.util.List;

public class CreditInfoDAO extends AbstractDao<CreditinfoEntity,String> {


    @Override
    public List<CreditinfoEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CreditinfoEntity ", CreditinfoEntity.class).list();
    }

    @Override
    public CreditinfoEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(CreditinfoEntity.class, id, LockMode.PESSIMISTIC_READ);
    }

}
