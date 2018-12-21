package Entity;

import javax.persistence.*;


@Entity
@Table(name="projects")
@NamedQuery(name = "Creditinfo.getAll", query = "SELECT u from Users u")
public class Creditinfo {
    @Id
    private String projectid;
    @Column
    private Integer cardnumber;
    @Column
    private Integer qiwimobilephone;
    @Column
    private Long yamoney;
}
