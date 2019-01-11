import dataAccesLayer.dao.*;
import dataAccesLayer.entity.Dialog;
import dataAccesLayer.entity.Users;
import dataAccesLayer.exception.DBException;
import dataAccesLayer.service.ServiceFactory;
import dataAccesLayer.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

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