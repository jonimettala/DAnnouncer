package wtf.joni.dannouncer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final int PICK_WEBHOOK_URL_REQUEST = 0;
    final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Debug.loadDebug(this);
    }

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_WEBHOOK_URL_REQUEST) {
            if (resultCode == RESULT_OK) {
                String webhookUrl = data.getStringExtra("webhookUrl");
                ((EditText) findViewById(R.id.webhook_url)).setText(webhookUrl);
            }
        }
    }
}
