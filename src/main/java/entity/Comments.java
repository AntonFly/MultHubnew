package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@NamedQuery(name = "Comments.getAll", query = "SELECT u from Comments u")
public class Comments {
    public Comments(){}
    @Id
    private String id;
    @Column
    private String projectid;
    @Column
    private String login;
    @Column
    private String comment;
    @Column
    private Timestamp time;

    @ManyToOne
    @JoinColumn(
            name = "login",
            insertable = false,
            updatable = false
    )
    private Users user;

    @ManyToOne
    @JoinColumn(
            name = "projectid",
            insertable = false,
            updatable = false
    )
    private Projects projects;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
