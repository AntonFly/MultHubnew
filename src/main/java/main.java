import Entity.Users;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class main {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("MULTHUB").createEntityManager();
        em.getTransaction().begin();
        List<Users> users = em.createNamedQuery("Users.getAll").getResultList();
        for (Users user:
             users) {
            if(user.getLogin().equals("aad")){
                System.out.println( user.getCondata().geteMail());
            }
        }


    }

}