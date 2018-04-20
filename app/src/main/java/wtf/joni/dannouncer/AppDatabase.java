package wtf.joni.dannouncer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Used by Room Persistence Library.
 *
 * @author Joni Mettälä
 */
@Database(entities = {Webhook.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WebhookDao webhookDao();
}