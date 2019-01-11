package dataAccesLayer.entity;

import javax.persistence.*;

@Entity
@Table(name="creditinfo")
@NamedQuery(name = "Creditinfo.getAll", query = "SELECT u from Creditinfo u")
public class Creditinfo {
    @Id
    @Column
    private String projectid;
    @Column(name = "cardnumber")
    private Integer cardnumber;
    @Column
    private Integer qiwimobilephone;
    @Column
    private Long yamoney;
    @OneToOne(optional = false,mappedBy = "credit")
    private Projects project;

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public Integer getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(Integer cardnumber) {
        this.cardnumber = cardnumber;
    }

    public Integer getQiwimobilephone() {
        return qiwimobilephone;
    }

    public void setQiwimobilephone(Integer qiwimobilephone) {
        this.qiwimobilephone = qiwimobilephone;
    }

    public Long getYamoney() {
        return yamoney;
    }

    public void setYamoney(Long yamoney) {
        this.yamoney = yamoney;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }
}
