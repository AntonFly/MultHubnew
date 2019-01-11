package dataAccesLayer.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dialog")
@NamedQuery(name = "Dialog.getAll", query = "SELECT u from Dialog u")
public class Dialog {
    public Dialog() {
        this.creationtime=new Timestamp(System.currentTimeMillis());
        this.id= UUID.nameUUIDFromBytes((this.creationtime.toString().getBytes())).toString();
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "creationtime")
    private Timestamp creationtime;


    @ManyToMany(mappedBy = "dialogs")
    private List<Users> interlocutors;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dialog_id")
    private List<Message> messages;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Timestamp creationtime) {
        this.creationtime = creationtime;
    }

    public List<Users> getInterlocutors() {
        return interlocutors;
    }

    public void setInterlocutors(List<Users> interlocutors) {
        this.interlocutors = interlocutors;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
