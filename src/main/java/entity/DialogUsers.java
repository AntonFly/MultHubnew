package entity;

import javax.persistence.*;

@Entity
@Table(name = "dialog_users", schema = "public", catalog = "multhub")
public class DialogUsers {
    @EmbeddedId
    private DialogUsersPK id;


    public DialogUsersPK getId() {
        return id;
    }

    public void setId(DialogUsersPK id) {
        this.id = id;
    }
}
