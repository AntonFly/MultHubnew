import entity.Users;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class main {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("MULTHUB").createEntityManager();
        em.getTransaction().begin();
                Users user=em.find(Users.class,"5d");
                System.out.println( user.getFollowers());
                System.exit(0);
        em.getTransaction().commit();
    }

}