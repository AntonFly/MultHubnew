package entity;

import javax.persistence.*;

@Entity
@Table(name = "subs")
@NamedQuery(name = "Subs.getAll", query = "SELECT u from Subs u")
@IdClass(SubsEntityPK.class)
public class Subs {
    public Subs(){}
    @Id
    private String login;
    @Id
    private String projectid;
}
