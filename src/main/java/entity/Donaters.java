package entity;
import javax.persistence.*;

@Entity
@Table(name = "donaters")
@NamedQuery(name = "Donaters.getAll", query = "SELECT u from Donaters u")
@AssociationOverrides({
        @AssociationOverride(name = "id.login",
            joinColumns = @JoinColumn(name = "login")),
        @AssociationOverride(name = "id.projectid",
                joinColumns = @JoinColumn(name = "projectid"))
})
public class Donaters {
    @EmbeddedId
    private DonatersEntityPK id;
    public Donaters(){}

    @Column
    private Double value;
    @Column
    private String text;


    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public DonatersEntityPK getId() {
        return id;
    }

    public void setId(DonatersEntityPK id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Transient
    public  Users getLogin(){
        return  getId().getLogin();
    }

    public  void setLogin(Users user){
        getId().setLogin(user);
    }

    @Transient
    public  Projects getProjectid(){
        return  getId().getProjectid();
    }

    public  void setLogin(Projects projects){
        getId().setProjectid(projects);
    }
}
