package entity;

import javax.persistence.*;

@Entity
@Table(name = "developers")
@NamedQuery(name = "Developers.getAll", query = "SELECT u from Developers u")
@IdClass(DevelopersEntityPK.class)
public class Developers {
    public Developers(){}

    @Id
    private String login;
    @Id
    private String projectid;
    @Column
    private Projpos projpos;
    @Column
    private String description;

}
