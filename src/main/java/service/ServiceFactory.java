package service;

public class ServiceFactory {
    private static volatile ProjectService projectService;
    private static volatile ViewService viewService;
    private static volatile UserService userService;

    public static ProjectService getProjectService(){
        if(projectService == null) {
            synchronized(ServiceFactory.class) {
                if(projectService == null) {
                    projectService = new ProjectService();
                }
            }
        }
        return projectService;
    }

    public static ViewService getViewService(){
        if(viewService == null) {
            synchronized(ServiceFactory.class) {
                if(viewService == null) {
                    viewService = new ViewService();
                }
            }
        }
        return viewService;
    }

    public static UserService getUserService(){
        if(userService == null) {
            synchronized(ServiceFactory.class) {
                if(userService == null) {
                    userService = new UserService();
                }
            }
        }
        return userService;
    }
}
