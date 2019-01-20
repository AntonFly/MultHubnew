import dataAccesLayer.exception.DBException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class main {
    public static void main(String[] args) throws DBException, ParseException {
//        UsersDAO usersDAO= new UsersDAO();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = dateFormat.parse("2019-01-18 23:00:13.431000");
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//        timestamp.
        Calendar cal= Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        int month = cal.get(Calendar.MONTH) + 1; // 0 for January, 1 for Feb and so on
        int day = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        long lMillis = cal.getTime().getTime();
        System.out.println(cal.getTime());
        System.out.println(timestamp);

        System.out.println(timestamp.getTime()-lMillis);
//        List<Users> users= usersDAO.getAll();
//        System.out.println();
//         EntityManager em = Persistence.createEntityManagerFactory("MULTHUB").createEntityManager();
//        em.getTransaction().begin();
//                Dialog dialog=new Dialog();
//                Users user1=em.find(Users.class,"4d");
//                Users user2= em.find(Users.class,"7d");
//        em.getTransaction().commit();
//        UserService userService= ServiceFactory.getUserService();
//        userService.createDialog(user1,user2);


//        Projects proj= em.find(Projects.class,"1");
//        System.out.println(proj.getCommits().get(0).getCommitsfile().size());
//        em.getTransaction().commit();
    }

}