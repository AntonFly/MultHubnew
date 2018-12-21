package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "followers", schema = "public", catalog = "multhub")
@IdClass(FollowersPK.class)
public class Followers {
    @Id
    private String login;
    @Id
    private String follower;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(
            name = "login",
            referencedColumnName = "login",
            insertable = false,
            updatable = false
    )
    private Users owner;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(
            name = "follower",
            referencedColumnName = "login",
            insertable = false,
            updatable = false
    )
    private Users follow;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public Users getFollow() {
        return follow;
    }

    public void setFollow(Users follow) {
        this.follow = follow;
    }
}
