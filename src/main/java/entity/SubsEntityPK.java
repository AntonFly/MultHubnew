package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SubsEntityPK implements Serializable {
    @Column(name = "login", nullable = false, length = 30)
    @Id
    private String login;
    @Column(name = "projectid", nullable = false)
    @Id
    private String projectid;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

}
