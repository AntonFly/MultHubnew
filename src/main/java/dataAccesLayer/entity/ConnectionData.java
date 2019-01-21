package dataAccesLayer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "connectiondata", schema = "public", catalog = "multhub")
@NamedQuery(name = "ConnectionData.getAll", query = "SELECT u from ConnectionData u")
public class ConnectionData {
    @Id
    private String login;
    @Column(name = "email")
    private String eMail;
    @Column(name = "mobilenumb")
    private Long mobilenumb;

    @JsonIgnore
    @OneToOne(optional = false,mappedBy = "condata", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Users owner;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Long getMobilenumb() {
        return mobilenumb;
    }

    public void setMobilenumb(Long mobilenumb) {
        this.mobilenumb = mobilenumb;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public boolean checkMail(){return this.eMail != null;}
}
