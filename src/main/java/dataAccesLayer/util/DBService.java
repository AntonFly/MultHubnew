package dataAccesLayer.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.sql.DataSource;


public class DBService {
        private static EntityManagerFactory managerFactory;

        static {
//            DataSource source = null;
//            try {
//
//                source = (DataSource)new InitialContext().lookup("java:global/MULTHUB");
//                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+source.toString());
//
//            } catch (NamingException e) {
//                e.printStackTrace();
//            }

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

