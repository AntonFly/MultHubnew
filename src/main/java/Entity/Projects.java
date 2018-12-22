package entity;

import javax.persistence.*;


@Entity
@Table(name="projects")
@NamedQuery(name = "Projects.getAll", query = "SELECT u from Projects u")
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
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name ="projectid")
    private Creditinfo credit;

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCurbudget() {
        return curbudget;
    }

    public void setCurbudget(Double curbudget) {
        this.curbudget = curbudget;
    }

    public Double getGoalbudget() {
        return goalbudget;
    }

    public void setGoalbudget(Double goalbudget) {
        this.goalbudget = goalbudget;
    }

    public Creditinfo getCredit() {
        return credit;
    }

    public void setCredit(Creditinfo credit) {
        this.credit = credit;
    }
}
