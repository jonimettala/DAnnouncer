package wtf.joni.dannouncer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Joni Mettälä on 17-Mar-18.
 */

@Entity
public class Webhook {
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "url")
    private String url;

    public Webhook(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
