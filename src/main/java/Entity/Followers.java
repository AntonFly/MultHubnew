package Entity;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "followers", schema = "public", catalog = "multhub")
@IdClass(FollowersPK.class)
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
