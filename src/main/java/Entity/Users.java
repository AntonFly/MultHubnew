package Entity;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
@NamedQuery(name = "Users.getAll", query = "SELECT u from Users u")
public class Users {
    public Users() {
    }

    @Id
    private String login;
    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String password;
    @Column
    private String imgpath;
    @Column
    private String status;
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name ="login")
    private ConnectionData condata;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Userpost> posts;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "follow")
    private List<Users> followers;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ConnectionData getCondata() {
        return condata;
    }

    public void setCondata(ConnectionData condata) {
        this.condata = condata;
    }


    public List<Userpost> getPosts() {
        return posts;
    }

    public void setPosts(List<Userpost> posts) {
        this.posts = posts;
    }

    public List<Users> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Users> followers) {
        this.followers = followers;
    }
}
