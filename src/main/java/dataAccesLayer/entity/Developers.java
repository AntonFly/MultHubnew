package dataAccesLayer.entity;

import javax.persistence.*;

@Entity
@Table(name = "developers")
@NamedQuery(name = "Developers.getAll", query = "SELECT u from Developers u")
@AssociationOverrides({
        @AssociationOverride(name = "id.login",
                joinColumns = @JoinColumn(name = "login")),
        @AssociationOverride(name = "id.projectid",
                joinColumns = @JoinColumn(name = "projectid")) })
public class Developers {
    public Developers(){
        this.id=new DevelopersEntityPK();
    }

    @EmbeddedId
    private DevelopersEntityPK id;

    @Column(name="projpos")
    @Enumerated(EnumType.STRING)
    private Projpos projpos;

    @Column(name="description")
    private String description;

    @Transient
    public Projects getProjectid(){return getId().getProjectid();}
    public void setProjectid(Projects pr){getId().setProjectid(pr);}

    @Transient
    public Users getLogin(){return getId().getLogin();}
    public void setLogin(Users us){getId().setLogin(us); }

    public DevelopersEntityPK getId(){return this.id;}
    public void setId(DevelopersEntityPK id) {
        this.id = id;
    }

    public Projpos getProjpos() {
        return projpos;
    }

    public void setProjpos(Projpos projpos) {
        this.projpos = projpos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
