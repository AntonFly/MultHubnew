package dataAccesLayer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class DevelopersEntityPK implements Serializable {

    public DevelopersEntityPK(){}
    public DevelopersEntityPK(Users users,Projects projects){this.login=users;this.projectid=projects;}

    @JsonIgnore
    @ManyToOne
    private Users login;

    @JsonIgnore
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
