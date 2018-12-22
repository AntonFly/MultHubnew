package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@NamedQuery(name = "Comments.getAll", query = "SELECT u from Comments u")
public class Comments {
    public Comments(){}

    @Id
    @GeneratedValue
    private String id;
    @Column
    private String projectid;
    @Column
    private String login;
    @Column
    private String comment;
    @Column
    private Timestamp time;
}
