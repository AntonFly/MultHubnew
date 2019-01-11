package dataAccesLayer.entity;

import javax.persistence.*;


@Entity
@Table(name = "requests")
@NamedQuery(name = "Requests.getAll", query = "SELECT u from Requests u")
@AssociationOverrides({
        @AssociationOverride(name = "id.login",
                joinColumns = @JoinColumn(name = "login")),
        @AssociationOverride(name = "id.projectid",
                joinColumns = @JoinColumn(name = "projectid"))
})
public class Requests {
    public Requests(){
        this.id=new RequestsEntityPK();
    }
    @EmbeddedId
    private RequestsEntityPK id;
    @Column
    @Enumerated(EnumType.STRING)
    private Projpos projpos;
    @Column
    private Boolean isrequest;
    @Transient
    public Projects getProjectid(){return getId().getProjectid();}
    public void setProjectid(Projects pr){getId().setProjectid(pr);}

    @Transient
    public Users getLogin(){return getId().getLogin();}
    public void setLogin(Users us){getId().setLogin(us); }





    public  void setLogin(Projects projects){
        getId().setProjectid(projects);
    }

    public RequestsEntityPK getId() {
        return id;
    }

    public void setId(RequestsEntityPK id) {
        this.id = id;
    }

    public Projpos getProjpos() {
        return projpos;
    }

    public void setProjpos(Projpos projpos) {
        this.projpos = projpos;
    }

    public Boolean getIsrequest() {
        return isrequest;
    }

    public void setIsrequest(Boolean isrequest) {
        this.isrequest = isrequest;
    }
}
