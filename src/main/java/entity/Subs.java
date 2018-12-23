package entity;

import javax.persistence.*;

@Entity
@Table(name = "subs")
@NamedQuery(name = "Subs.getAll", query = "SELECT u from Subs u")
public class Subs {
    public Subs(){}
    @EmbeddedId
    private SubsEntityPK id;

    public SubsEntityPK getId() {
        return id;
    }

    public void setId(SubsEntityPK id) {
        this.id = id;
    }
}
