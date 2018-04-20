package wtf.joni.dannouncer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Includes method used by Room Persistence Library.
 *
 * @author Joni Mettälä
 */
@Dao
public interface WebhookDao {

    /**
     * Gets all webhooks saved in phone memory.
     *
     * @return List of the webhooks
     */
    @Query("SELECT * FROM webhook")
    List<Webhook> getAll();

    /**
     * Gets a specific webhook from phone memory.
     *
     * @param hookName Webhook name
     * @param hookUrl Webhook url
     * @return Webhook which mathec the search query
     */
    @Query("SELECT * FROM webhook WHERE name LIKE :hookName AND "
            + "url LIKE :hookUrl LIMIT 1")
    Webhook findByName(String hookName, String hookUrl);

    /**
     * Inserts given webhooks to phone memory
     *
     * @param webhooks Webhooks to be saved
     */
    @Insert
    void insertAll(Webhook... webhooks);

    /**
     * Deletes a specific webhook from phone memory.
     *
     * @param webhook Webhook to be deleted
     */
    @Delete
    void delete(Webhook webhook);
}
