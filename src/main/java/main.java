import Entity.Projects;
import Entity.Users;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class main {
    public static void main(String[] args)
    {
        EntityManager em = Persistence.createEntityManagerFactory("MULTHUB").createEntityManager();
        em.getTransaction().begin();
        List<Users> users = em.createNamedQuery("Users.getAll").getResultList();
        em.getTransaction().commit();
        for (Users user: users)
        {
            if(user.getLogin().equals("aad"))
            {
                System.out.println( user.getCondata().geteMail());
            }
        }


        //EntityManager em2 = Persistence.createEntityManagerFactory("MULTHUB").createEntityManager();
        em.getTransaction().begin();
        List<Projects> projects = em.createNamedQuery("Projects.getAll").getResultList();
        //em.getTransaction().commit();
        for (Projects project: projects)
        {
            if(project.getProjectid().equals("123456789"))
            {
                System.out.println( project.getProjectid()+" WORKS!!");
            }
        }



    }
}