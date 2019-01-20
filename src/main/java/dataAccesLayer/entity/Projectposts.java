package dataAccesLayer.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "projectposts")
@NamedQuery(name = "Projectpost.getAll", query = "SELECT u from Projectposts u")
public class Projectposts {
    public Projectposts(){}

    @Id
    private String id;

    @Column
    private String projectid;

    @Column
    private String text;
    @Column
    private Timestamp time;
    @Column
    private String filepath;

    @JsonIgnore
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(
            name = "projectid",
            insertable = false,
            updatable = false
    )
    private Projects project;

    public boolean checkFilePath(){
        return this.filepath!=null;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @JsonGetter("time")
    public String getTimel() {
        return time.toString();
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }
}
