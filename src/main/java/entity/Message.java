package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "message", schema = "public", catalog = "multhub")
@NamedQuery(name = "Message.getAll", query = "SELECT u from Message u")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = -1)
    private Integer id;

    @Basic
    @Column(name = "text", nullable = true, length = -1)
    private String text;

    @Basic
    @Column(name = "dialog_id", nullable = true, length = -1)
    private String dialogId;

    @Basic
    @Column(name = "sender", nullable = true, length = -1)
    private String sender;

    @Basic
    @Column(name = "isread", nullable = true)
    private Boolean isread;

    @Basic
    @Column(name = "time", nullable = true)
    private Timestamp time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getDialogId() {
        return dialogId;
    }

    public void setDialogId(String dialogId) {
        this.dialogId = dialogId;
    }




    public Boolean getIsread() {
        return isread;
    }

    public void setIsread(Boolean isread) {
        this.isread = isread;
    }


    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }


}
