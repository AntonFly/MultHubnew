package dataAccesLayer.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "commits")
@NamedQuery(name = "Commits.getAll", query = "SELECT u from Commits u")
public class Commits implements Serializable {
    public Commits(){}

    @Id
    private String id;

    @Column
    private String projectid;

    @Column
    private String developer;

    @Column
    private Timestamp time;

    @JsonIgnore
    @Column
    @Enumerated(EnumType.STRING)
    private Approved approved;


    //на файлы
    //@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name="commitid")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.commitid")
    private List<Commitsfile> commitsfile;

    //на проект
    @JsonIgnore
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(
            name = "projectid",
            insertable = false,
            updatable = false
    )
    private Projects project;

    public List<Commitsfile> getCommitsfile() {
        return commitsfile;
    }

    public void setCommitsfile(List<Commitsfile> commitsfile) {
        this.commitsfile = commitsfile;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Approved getApproved() {
        return approved;
    }

    public void setApproved(Approved approved) {
        this.approved = approved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }
}