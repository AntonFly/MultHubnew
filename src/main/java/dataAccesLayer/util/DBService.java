package dataAccesLayer.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DBService {
        private static EntityManagerFactory managerFactory;

        static {
                managerFactory = Persistence.createEntityManagerFactory("MULTHUB");
        }

        private DBService() {}

    private static EntityManagerFactory getEntytiManagerFactory(){
        return managerFactory;
    }

    public static EntityManager getEntytiManager(){
            return getEntytiManagerFactory().createEntityManager();
    }
    public static EntityTransaction getTransaction() {
        EntityTransaction transaction = getEntytiManager().getTransaction();

        if (!transaction.isActive()) {
            transaction.begin();
        }
        return transaction;
    }


}

