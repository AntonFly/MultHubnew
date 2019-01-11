package dataAccesLayer.entity;

import javax.persistence.*;
import java.io.Serializable;
@Embeddable
public class DialogUsersPK implements Serializable {
    @ManyToOne
    private Dialog dialogId;
    @ManyToOne
    private Users userId;

    public DialogUsersPK() {
    }

    public DialogUsersPK(Dialog dialogId, Users userId) {
        this.dialogId = dialogId;
        this.userId = userId;
    }

    public Dialog getDialogId() {
        return dialogId;
    }

    public void setDialogId(Dialog dialogId) {
        this.dialogId = dialogId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
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
