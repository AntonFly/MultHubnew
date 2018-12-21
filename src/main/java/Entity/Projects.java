package Entity;

import javax.persistence.*;


@Entity
@Table(name="projects")
@NamedQuery(name = "Users.getAll", query = "SELECT u from Users u")
public class Projects {
    public Projects(){}

    @Id
    private String projectid;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Double curbudget;
    @Column
    private Double goalbudget;
    @OneToOne
    private Creditinfo credit;

}
