package Entity;

import javax.persistence.*;

@Entity
@Table(name = "dialog")
public class Dialog {
    @Id
    private String id;

    @Column(name = "one_user_id")
    private String oneUserId;
    @Column(name = "two_user_id")
    private String twoUserId;


}
