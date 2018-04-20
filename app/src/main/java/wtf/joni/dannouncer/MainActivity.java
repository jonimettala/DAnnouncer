package wtf.joni.dannouncer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Main activity of the application.
 *
 * @author Joni Mettälä
 */
public class MainActivity extends AppCompatActivity {
    static final int PICK_WEBHOOK_URL_REQUEST = 0;
    final String TAG = "MainActivity";

    /**
     * Handles creating the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Debug.loadDebug(this);
    }

    /**
     * Handles the button clicks in the activity.
     *
     * @param view Activity view
     */
    public void buttonClicked(View view) {
        switch (view.getId()) {
            case R.id.send:
                handleMessageSend();
                break;
            case R.id.list_webhooks:
                Intent intent = new Intent(this, WebhookListActivity.class);
                startActivityForResult(intent, PICK_WEBHOOK_URL_REQUEST);
                break;
        }
    }

    /**
     * Handles sending the message.
     */
    public void handleMessageSend() {
        Message message = new Message();
        message.setUrl(((EditText) findViewById(R.id.webhook_url)).getText().toString());
        message.setContent(((EditText) findViewById(R.id.content)).getText().toString());
        message.setUsername(((EditText) findViewById(R.id.user)).getText().toString());
        if (validateMessage(message)) {
            MessageSender sender = new MessageSender(message.getUrl(), this);
            sender.send(message);
        }
    }

    /**
     * Checks whether all the information set to a Message is ok and it can be sent to Discord.
     *
     * @param message Message which needs to be checked
     * @return True if Message information is ok, otherwise false.
     */
    private boolean validateMessage(Message message) {
        boolean validated = true;
        if (message.getUrl().length() == 0 || message.getContent().length() == 0) {
            validated = false;
            Toast.makeText(getApplicationContext(), "URL and message must be given.",
                    Toast.LENGTH_SHORT).show();
        }
        if (message.getUsername().length() == 1) {
            validated = false;
            Toast.makeText(getApplicationContext(), "Sender name must be empty or at least " +
                    "two characters long.", Toast.LENGTH_SHORT).show();
        }
        Debug.print(TAG, "validateMessage()", "Validation result: " + validated);
        return validated;
    }

    /**
     * Handles setting a webhook url to the right field if something was selected in the
     * WebhookListActivity.
     *
     * @param data Intent to be reviewed
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_WEBHOOK_URL_REQUEST) {
            if (resultCode == RESULT_OK) {
                String webhookUrl = data.getStringExtra("webhookUrl");
                ((EditText) findViewById(R.id.webhook_url)).setText(webhookUrl);
            }
        }
    }
}
