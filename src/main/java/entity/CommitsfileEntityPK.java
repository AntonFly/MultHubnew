package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;


public class CommitsfileEntityPK implements Serializable {
    private String filename;
    private String commitid;

    @Column(name = "filename", nullable = false, length = -1)
    @Id
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Column(name = "commitid", nullable = false, length = -1)
    @Id
    public String getCommitid() {
        return commitid;
    }

    public void setCommitid(String commitid) {
        this.commitid = commitid;
    }

}
