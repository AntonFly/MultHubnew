package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DonatersEntityPK implements Serializable {
    private String login;
    private String projectid;

    @Column(name = "login", nullable = false, length = -1)
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

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonatersEntityPK that = (DonatersEntityPK) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(projectid, that.projectid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, projectid);
    }
}
