package Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "projectposts")
@NamedQuery(name = "Creditinfo.getAll", query = "SELECT u from Creditinfo u")
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
    

}
