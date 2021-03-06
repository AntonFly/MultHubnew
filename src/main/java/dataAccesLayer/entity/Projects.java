package dataAccesLayer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


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
    /////////////////////////////////////////////////////////////////////////////////////////
    @JsonIgnore
    @OneToOne()
    @JoinColumn(name ="projectid")
    private Creditinfo credit;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="projectid")
    private List<Projectposts> posts;

    @JsonIgnore
    @OneToMany( fetch = FetchType.LAZY)
    @JoinColumn(name="projectid")
    private List<Commits> commits;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "subs",
            joinColumns = @JoinColumn(name = "projectid"),
            inverseJoinColumns = @JoinColumn(name = "login")
    )
    private List<Users> subscribers;

    @JsonIgnore
    @OneToMany(
            mappedBy = "id.projectid",
           fetch = FetchType.LAZY
    )
    private List<Donaters> donations;

    @JsonIgnore
    @OneToMany(
            mappedBy = "id.projectid",
            fetch = FetchType.LAZY
    )
    private List<Requests> requests;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.projectid")
    private List<Developers> developers;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="projectid")
    private List<Comments> comments;
    /////////////////////////////////////////////////////////////////////////////////////////

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<Developers> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developers> developers) {
        this.developers = developers;
    }

    public List<Commits> getCommits() {
        return commits;
    }

    public void setCommits(List<Commits> commits) {
        this.commits = commits;
    }

    public List<Projectposts> getPosts() {
        return posts;
    }

    public void setPosts(List<Projectposts> posts) {
        this.posts = posts;
    }

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

    public List<Users> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Users> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Donaters> getDonations() {
        return donations;
    }

    public void setDonations(List<Donaters> donations) {
        this.donations = donations;
    }

    public List<Requests> getRequests() {
        return requests;
    }

    public void setRequests(List<Requests> requests) {
        this.requests = requests;
    }
}
