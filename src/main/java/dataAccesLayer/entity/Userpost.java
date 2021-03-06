package dataAccesLayer.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "userpost")
@NamedQuery(name = "Userpost.getAll", query = "SELECT u from Userpost u")
public class Userpost {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column
    private String text;

    @Column
    private String filepath;

    @Column
    private Timestamp time;

    @JsonIgnore
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "login",
            insertable = false,
            updatable = false
    )
    private Users owner;

    @JsonGetter("time")
    public String getTime1() {
        return time.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }
}
