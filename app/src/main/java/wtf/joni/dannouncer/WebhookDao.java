package wtf.joni.dannouncer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WebhookDao {
    @Query("SELECT * FROM webhook")
    List<Webhook> getAll();

    @Query("SELECT * FROM webhook WHERE uid IN (:userIds)")
    List<Webhook> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM webhook WHERE name LIKE :hookName AND "
            + "url LIKE :hookUrl LIMIT 1")
    Webhook findByName(String hookName, String hookUrl);

    @Insert
    void insertAll(Webhook... webhooks);

    @Delete
    void delete(Webhook webhook);
}
