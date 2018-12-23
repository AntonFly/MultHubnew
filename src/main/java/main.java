import dao.*;
import entity.Projects;
import entity.Users;
import sun.plugin.javascript.navig.Link;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class main {
    public static void main(String[] args) {
        UsersDAO usersDAO= new UsersDAO();
        Users users1 = usersDAO.getEntityById("5d");
        System.out.println("PASS: "+users1.getPassword()+"   "+users1.getComments().get(0).getComment());
        Users users2 = usersDAO.getEntityById("6d");
        System.out.println("PASS: "+users2.getPassword()+"   "+users2.getDevelopers().get(0).getProjectid().getProjectid());

//         EntityManager em = Persistence.createEntityManagerFactory("MULTHUB").createEntityManager();
//        em.getTransaction().begin();
//                Users user=em.find(Users.class,"5d");
//                System.out.println( user.getFollowers());
//        em.getTransaction().commit();
//        em.getTransaction().begin();
//        Projects proj= em.find(Projects.class,"1");
//        System.out.println(proj.getCommits().get(0).getCommitsfile().size());
//        em.getTransaction().commit();
    }

}