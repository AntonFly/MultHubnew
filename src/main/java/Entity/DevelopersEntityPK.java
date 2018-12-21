package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DevelopersEntityPK implements Serializable {
    private String login;
    private String projectid;

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Column(name = "login", nullable = false, length = 30)
    @Id
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "projectid", nullable = false, length = -1)
    @Id
    public String getProjectid() {
        return projectid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevelopersEntityPK that = (DevelopersEntityPK) o;
        return projectid == that.projectid &&
                Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, projectid);
    }
}
