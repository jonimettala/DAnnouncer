package wtf.joni.dannouncer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Webhook.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WebhookDao webhookDao();
}