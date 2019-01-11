package dataAccesLayer.entity;

import javax.persistence.*;

@Entity
@Table(name = "followers", schema = "public", catalog = "multhub")
@IdClass(FollowersPK.class)
@NamedQuery(name = "Followers.getAll", query = "SELECT u from Followers u")
public class Followers {
    @Id
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(
            name = "login",
            referencedColumnName = "login",
            insertable = false,
            updatable = false
    )
    private Users login;
    @Id()
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(
            name = "follower",
            referencedColumnName = "login",
            insertable = false,
            updatable = false
    )
    private Users follower;

    public Users getLogin() {
        return login;
    }

    public void setLogin(Users login) {
        this.login = login;
    }

    public Users getFollower() {
        return follower;
    }

    public void setFollower(Users follower) {
        this.follower = follower;
    }
}
