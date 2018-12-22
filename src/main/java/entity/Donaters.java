package entity;
import javax.persistence.*;

@Entity
@Table(name = "donaters")
@NamedQuery(name = "Donaters.getAll", query = "SELECT u from Donaters u")
@IdClass(DonatersEntityPK.class)
@AssociationOverrides({
        @AssociationOverride(name = "projectid",
                joinColumns = @JoinColumn(name = "projectid")),
        @AssociationOverride(name = "login",
                joinColumns = @JoinColumn(name = "login")) })
public class Donaters {
    public Donaters(){}

    @Id
    private String login;
    @Id
    private String projectid;
    @Column
    private Double value;
    @Column
    private String text;

}
