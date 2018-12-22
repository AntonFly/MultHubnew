package entity;

import javax.persistence.*;


@Entity
@Table(name = "requests")
@NamedQuery(name = "Requests.getAll", query = "SELECT u from Requests u")
@IdClass(RequestsEntityPK.class)
public class Requests {
    public Requests(){}
    @Id
    private String login;
    @Column
    private String projectid;
    @Column
    private Projpos projpos;
    @Column
    private Boolean isrequest;
}
