package dataAccesLayer.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
@Embeddable
public class DonatersEntityPK implements Serializable {
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
