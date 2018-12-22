package entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class CommitsfileEntityPK implements Serializable {
    private String filename;
    @ManyToOne
    private Commits commitid;

    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Commits getCommitid() {
        return commitid;
    }
    public void setCommitid(Commits commitid) {
        this.commitid = commitid;
    }

}
