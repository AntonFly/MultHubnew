package entity;

import javax.persistence.*;

@Entity
@Table(name = "dialog_users", schema = "public", catalog = "multhub")
@IdClass(DialogUsersPK.class)
public class DialogUsers {
    @Id
    @Column(name = "dialog_id", nullable = false, length = -1)
    private String dialogId;

    @Id
    @Column(name = "user_id", nullable = false, length = -1)
    private String userId;



    public String getDialogId() {
        return dialogId;
    }

    public void setDialogId(String dialogId) {
        this.dialogId = dialogId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
