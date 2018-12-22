package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dialog")
@NamedQuery(name = "Dialog.getAll", query = "SELECT u from Dialog u")
public class Dialog {
    @Id
    private String id;

    @Column(name = "creationtime")
    private String creationtime;

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

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    public List<Users> getInterlocutors() {
        return interlocutors;
    }

    public void setInterlocutors(List<Users> interlocutors) {
        this.interlocutors = interlocutors;
    }
}
