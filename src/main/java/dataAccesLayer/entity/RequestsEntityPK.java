package dataAccesLayer.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
@Embeddable
public class RequestsEntityPK implements Serializable {
    public RequestsEntityPK(){}
    public RequestsEntityPK(Users user,Projects project){this.login=user;this.projectid=project;}
    @ManyToOne
    private Users login;

    @ManyToOne
    private Projects projectid;

    public Users getLogin() {
        return login;
    }

    public void setLogin(Users login) {
        this.login = login;
    }

    public Projects getProjectid() {
        return projectid;
    }

    public void setProjectid(Projects projectid) {
        this.projectid = projectid;
    }

}