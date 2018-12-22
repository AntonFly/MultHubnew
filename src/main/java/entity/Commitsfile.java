package entity;

import javax.persistence.*;

@Entity
@Table(name = "commitsfile")
@NamedQuery(name = "Commitsfile.getAll", query = "SELECT u from Commitsfile u")
@IdClass(CommitsfileEntityPK.class)
public class Commitsfile {
    public Commitsfile(){}

    @Id
    private String filename;
    @Column
    private String filepath;
    @Id
    @Column(name = "commitid")
    private String commitid;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(
            name = "commitid",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private Commits commits;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getCommitid() {
        return commitid;
    }

    public void setCommitid(String commitid) {
        this.commitid = commitid;
    }

    public Commits getCommits() {
        return commits;
    }

    public void setCommits(Commits commits) {
        this.commits = commits;
    }
}

