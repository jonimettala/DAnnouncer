package wtf.joni.dannouncer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Webhook objects which are saved to phone memory will be created from this class.
 * Used by Room Persistence Library.
 *
 * @author Joni Mettälä
 */
@Entity
public class Webhook {

    /**
     * Unique id of the webhook.
     */
    @PrimaryKey(autoGenerate = true)
    private int uid;

    /**
     * Name of the webhook.
     */
    @ColumnInfo(name = "name")
    private String name;

    /**
     * Url of the webhook. HTTP POST requests will be sent to this.
     */
    @ColumnInfo(name = "url")
    private String url;

    /**
     * Constructor for the webhook.
     *
     * @param name Webhook name.
     * @param url Webhook url.
     * @see #name
     * @see #url
     */
    public Webhook(String name, String url) {
        this.name = name;
        this.url = url;
    }

    /**
     * Gets webhook id.
     *
     * @return Webhook id
     * @see #uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * Sets webhook id.
     *
     * @param uid Webhook id
     * @see #uid
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * Gets webhook name.
     *
     * @return Webhook name
     * @see #name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets webhook name.
     *
     * @return Webhook name
     * @see #name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets webhook url.
     *
     * @return Webhook url
     * @see #url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets webhook url.
     *
     * @return Webhook url
     * @see #url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
