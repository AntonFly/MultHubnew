import dao.*;
import entity.Dialog;
import entity.Projects;
import entity.Users;
import exception.DBException;
import service.ServiceFactory;
import service.UserService;
import sun.plugin.javascript.navig.Link;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class main {
    public static void main(String[] args) throws DBException {
        UsersDAO usersDAO= new UsersDAO();
//        List<Users> users= usersDAO.getAll();
//        System.out.println();
         EntityManager em = Persistence.createEntityManagerFactory("MULTHUB").createEntityManager();
        em.getTransaction().begin();
                Dialog dialog=new Dialog();
                Users user1=em.find(Users.class,"4d");
                Users user2= em.find(Users.class,"7d");
        em.getTransaction().commit();
        UserService userService= ServiceFactory.getUserService();
        userService.createDialog(user1,user2);


//        Projects proj= em.find(Projects.class,"1");
//        System.out.println(proj.getCommits().get(0).getCommitsfile().size());
//        em.getTransaction().commit();
    }

}