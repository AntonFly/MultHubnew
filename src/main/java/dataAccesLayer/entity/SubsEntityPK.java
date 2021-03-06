package dataAccesLayer.entity;

import javax.persistence.*;
import java.io.Serializable;
@Embeddable
public class SubsEntityPK implements Serializable {
    public SubsEntityPK() {
    }

    public SubsEntityPK(Users login, Projects projectid) {
        this.login = login;
        this.projectid = projectid;
    }

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
