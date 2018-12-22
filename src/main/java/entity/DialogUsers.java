package Entity;

import javax.persistence.*;

@Entity
@Table(name = "dialog_users", schema = "public", catalog = "multhub")
@IdClass(Entity.DialogUsersPK.class)
public class DialogUsers {
    private String dialogId;
    private String userId;

    @Id
    @Column(name = "dialog_id", nullable = false, length = -1)
    public String getDialogId() {
        return dialogId;
    }

    public void setDialogId(String dialogId) {
        this.dialogId = dialogId;
    }

    @Id
    @Column(name = "user_id", nullable = false, length = -1)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DialogUsers that = (DialogUsers) o;

        if (dialogId != null ? !dialogId.equals(that.dialogId) : that.dialogId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dialogId != null ? dialogId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
