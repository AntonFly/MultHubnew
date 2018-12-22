package dao;

public class DaoFactory {
    private static volatile UsersDAO usersDAO;
    private static volatile CreditInfoDAO creditInfoDAO;
    private static volatile ConnectiondataDao connectiondataDao;
    private static volatile CommitsDao commitsDao;
    private static volatile SubsDAO subsDAO;
    private static volatile RequestsDAO requestsDAO;
    private static volatile ProjectsDAO projectsDAO;
    private static volatile DevelopersDAO developersDAO;
    private static volatile CommentsDAO commentsDAO;
    private static volatile DialogDAO dialogDao;
    private static volatile MessageDAO messageDao;
    private static volatile UserpostDAO userPostDao;
    private static volatile FollowersDAO followersDao;
    private static volatile CommitsfileDAO commitsfileDAO;
    private static volatile DonatersDAO donatersDAO;
    private static volatile ProjectspostsDAO projectspostsDAO;



    public static UsersDAO getUsersDAO(){
        if(usersDAO == null) {
            synchronized(DaoFactory.class) {
                if(usersDAO == null) {
                    usersDAO = new UsersDAO();
                }
            }
        }
        return usersDAO;
    }

    public static CreditInfoDAO getCreditInfoDAO(){
        if(creditInfoDAO == null) {
            synchronized(DaoFactory.class) {
                if(creditInfoDAO == null) {
                    creditInfoDAO = new CreditInfoDAO();
                }
            }
        }
        return creditInfoDAO;
    }

    public static ConnectiondataDao getConnectiondataDao(){
        if(connectiondataDao == null) {
            synchronized(DaoFactory.class) {
                if(connectiondataDao == null) {
                    connectiondataDao = new ConnectiondataDao();
                }
            }
        }
        return connectiondataDao;
    }

    public static CommitsDao getCommitsDao(){
        if(commitsDao == null) {
            synchronized(DaoFactory.class) {
                if(commitsDao == null) {
                    commitsDao = new CommitsDao();
                }
            }
        }
        return commitsDao;
    }

    public static RequestsDAO getRequestsDAO(){
        if(requestsDAO == null) {
            synchronized(DaoFactory.class) {
                if(requestsDAO == null) {
                    requestsDAO = new RequestsDAO();
                }
            }
        }
        return requestsDAO;
    }
    public static ProjectsDAO getProjectsDAO(){
        if(projectsDAO == null) {
            synchronized(DaoFactory.class) {
                if(projectsDAO == null) {
                    projectsDAO = new ProjectsDAO();
                }
            }
        }
        return projectsDAO;
    }


    public static DevelopersDAO getDevelopersDAO(){
        if(developersDAO == null) {
            synchronized(DaoFactory.class) {
                if(developersDAO == null) {
                    developersDAO = new DevelopersDAO();
                }
            }
        }
        return developersDAO;
    }
    public static CommentsDAO getCommentsDAO(){
        if(commentsDAO == null) {
            synchronized(DaoFactory.class) {
                if(commentsDAO == null) {
                    commentsDAO = new CommentsDAO();
                }
            }
        }
        return commentsDAO;
    }
    public static SubsDAO getSubsDAO(){
        if(subsDAO == null) {
            synchronized(DaoFactory.class) {
                if(subsDAO == null) {
                    subsDAO = new SubsDAO();
                }
            }
        }
        return subsDAO;
    }

    public static DialogDAO getDialogDao(){
        if(dialogDao == null) {
            synchronized(DaoFactory.class) {
                if(dialogDao == null) {
                    dialogDao = new DialogDAO();
                }
            }
        }
        return dialogDao;
    }
    public static MessageDAO getMessageDao(){
        if(messageDao == null) {
            synchronized(DaoFactory.class) {
                if(messageDao == null) {
                    messageDao = new MessageDAO();
                }
            }
        }
        return messageDao;
    }
    public static UserpostDAO getUserPostDao(){
        if(userPostDao == null) {
            synchronized(DaoFactory.class) {
                if(userPostDao == null) {
                    userPostDao = new UserpostDAO();
                }
            }
        }
        return userPostDao;
    }
    public static FollowersDAO getFollowersDao(){
        if(followersDao == null) {
            synchronized(DaoFactory.class) {
                if(followersDao == null) {
                    followersDao = new FollowersDAO();
                }
            }
        }
        return followersDao;
    }

    public static CommitsfileDAO getCommitsfileDAO(){
        if(commitsfileDAO == null) {
            synchronized(DaoFactory.class) {
                if(commitsfileDAO == null) {
                    commitsfileDAO = new CommitsfileDAO();
                }
            }
        }
        return commitsfileDAO;
    }

    public static DonatersDAO getDonatersDAO(){
        if(donatersDAO == null) {
            synchronized(DaoFactory.class) {
                if(donatersDAO == null) {
                    donatersDAO = new DonatersDAO();
                }
            }
        }
        return donatersDAO;
    }

    public static ProjectspostsDAO getProjectspostsDAO(){
        if(projectspostsDAO == null) {
            synchronized(DaoFactory.class) {
                if(projectspostsDAO == null) {
                    projectspostsDAO = new ProjectspostsDAO();
                }
            }
        }
        return projectspostsDAO;
    }
}
