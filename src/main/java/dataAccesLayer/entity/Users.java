package dataAccesLayer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
@NamedQuery(name = "Users.getAll", query = "SELECT u from Users u")
public class Users {
    public Users() {

    }

    @Id
    private String login;

    @Column
    private String name;

    @Column
    private String surname;
    @JsonIgnore
    @Column
    private String password;

    @Column
    private String imgpath;

    @Column
    private String status;

    @Column
    private boolean sendOnInvites= true;

    @Column
    private boolean sendOnPost =true;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name ="login",nullable = true)
    private ConnectionData condata;

    @JsonIgnore
    @OneToMany(  fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Userpost> posts;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "followers",
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = @JoinColumn(name = "follower")
    )
    private List<Users> followers;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "dialog_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "dialog_id")
    )
    private List<Dialog> dialogs;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.login")
    private List<Developers> developers;

    @JsonIgnore
    @OneToMany()
    @JoinColumn(name="login")
    private List<Comments> comments;

    public List<Developers> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developers> developers) {
        this.developers = developers;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "subscribers")
    private List<Projects> subscriprions;

    @JsonIgnore
    @OneToMany(
            mappedBy = "id.login",
            fetch = FetchType.LAZY
    )
    private List<Donaters> donations;

    @JsonIgnore
    @OneToMany(
            mappedBy = "id.login",
            fetch = FetchType.LAZY
    )
    private List<Requests> requests;



    public  void addDialog(Dialog dialog){
        dialogs.add(dialog);
    }

    public  void sub(Projects proj){
        subscriprions.add(proj);
    }

    public  void unSub(Projects proj){
        subscriprions.remove(proj);
    }

    public  void addFollower(Users user){
        followers.add(user);
    }

    public  void dellFollower(Users user){
        followers.remove(user);
    }

    public  void addDeveloper(Developers user){
        developers.add(user);
    }

    public  void dellDeveloper(Developers user){
        developers.remove(user);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ConnectionData getCondata() {
        return condata;
    }

    public void setCondata(ConnectionData condata) {
        this.condata = condata;
    }

//    public  void setEmail(String email){
//        this.condata.seteMail(email);
//    }
//
//    public void setMobile(String mobile){
//        this.condata.setMobilenumb();
//    }


    public List<Userpost> getPosts() {
        return posts;
    }

    public void setPosts(List<Userpost> posts) {
        this.posts = posts;
    }

    public List<Users> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Users> followers) {
        this.followers = followers;
    }

    public List<Dialog> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<Dialog> dialogs) {
        this.dialogs = dialogs;
    }



    public void setInterlocutors(List<Projects> interlocutors) {
        this.subscriprions = interlocutors;
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

    public List<Projects> getSubscriprions() {
        return subscriprions;
    }

    public void setSubscriprions(List<Projects> subscriprions) {
        this.subscriprions = subscriprions;
    }

    public boolean isSendOnInvites() {
        return sendOnInvites;
    }

    public void setSendOnInvites(boolean sendOnInvites) {
        this.sendOnInvites = sendOnInvites;
    }

    public boolean isSendOnPost() {
        return sendOnPost;
    }

    public void setSendOnPost(boolean sendOnPost) {
        this.sendOnPost = sendOnPost;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
