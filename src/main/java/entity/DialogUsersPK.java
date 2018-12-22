package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class DialogUsersPK implements Serializable {
    @Column(name = "dialog_id", nullable = false, length = -1)
    @Id
    private String dialogId;
    @Column(name = "user_id", nullable = false, length = -1)
    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DialogUsersPK that = (DialogUsersPK) o;

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
