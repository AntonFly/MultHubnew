package entity;

import javax.persistence.*;

@Entity
@Table(name = "commitsfile")
@NamedQuery(name = "Commitsfile.getAll", query = "SELECT u from Commitsfile u")
@AssociationOverrides({
        @AssociationOverride(name = "id.commitid",
                joinColumns = @JoinColumn(name = "commitid"))
        })
public class Commitsfile {
    public Commitsfile(){
        this.id = new CommitsfileEntityPK();
    }

    @EmbeddedId
    private CommitsfileEntityPK id;
    @Column
    private String filepath;

    //@ManyToOne( fetch = FetchType.EAGER)
//    @JoinColumn(
//            name = "commitid",
//            referencedColumnName = "id",
//            insertable = false,
//            updatable = false
//    )
    //private Commits commits;

    public CommitsfileEntityPK getId() {
        return id;
    }

    public void setId(CommitsfileEntityPK id) {
        this.id = id;
    }
    @Transient
    public String getFilename() {
        return getId().getFilename();
    }
    public void setFilename(String filename) {
        getId().setFilename(filename);
    }

    @Transient
    public void setCommitid(Commits commitid) {
        getId().setCommitid(commitid);
    }

//    public Commits getCommits() {
//        return commits;
//    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Commits getCommitid() {
        return getId().getCommitid();
    }

//    public void setCommits(Commits commits) {
//        this.commits = commits;
//    }
}

