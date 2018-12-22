package entity;

import javax.persistence.*;

@Entity
@Table(name = "subs")
@NamedQuery(name = "Subs.getAll", query = "SELECT u from Subs u")
@IdClass(SubsEntityPK.class)
public class Subs {
    public Subs(){}
    @Id
    private String login;
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
