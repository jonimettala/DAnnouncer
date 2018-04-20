package wtf.joni.dannouncer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity which is used to add new webhooks to the application.
 *
 * @author Joni Mettälä
 */
public class NewWebhookActivity extends AppCompatActivity {

    /**
     * Handles creating the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_webhook);
    }

    /**
     * Handles button click in the activity. Checks if the information set to new webhook is ok
     * and returns the parameters to WebhookListActivity.
     *
     * @param view Activity view
     */
    public void clicked(View view) {
        Intent intent = this.getIntent();
        String webhookName = ((EditText) findViewById(R.id.webhook_name)).getText().toString();
        String webhookUrl = ((EditText) findViewById(R.id.webhook_url)).getText().toString();

        if (webhookName.length() == 0 || webhookUrl.length() == 0) {
            Toast.makeText(getApplicationContext(), "Name and URL must be given.",
                    Toast.LENGTH_SHORT).show();
        } else {
            intent.putExtra("webhookName", webhookName);
            intent.putExtra("webhookUrl", webhookUrl);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
