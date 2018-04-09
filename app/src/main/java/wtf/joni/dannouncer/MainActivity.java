package wtf.joni.dannouncer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final int PICK_WEBHOOK_URL_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Debug.loadDebug(this);
    }

    public String handleGetContent() {
        String message = ((EditText) findViewById(R.id.content)).getText().toString();
        if (message.length() > 0) {
            return message;
        } else {
            return "Placeholder message because no message set and I need to invent something better here";
        }
    }

    public void buttonClicked(View view) {
        switch (view.getId()) {
            case R.id.send:
                sendMessage();
                break;
            case R.id.list_webhooks:
                Intent intent = new Intent(this, WebhookListActivity.class);
                startActivityForResult(intent, PICK_WEBHOOK_URL_REQUEST);
                break;
        }
    }

    public void sendMessage() {
        String url = ((EditText) findViewById(R.id.webhook_url)).getText().toString();
        String content = ((EditText) findViewById(R.id.content)).getText().toString();
        if (url.length() == 0 || content.length() == 0) {
            Toast.makeText(getApplicationContext(), "URL and message must be given.",
                    Toast.LENGTH_SHORT).show();
        } else {
            MessageSender sender = new MessageSender(url, this);
            Message message = new Message(url, handleGetContent());
            handleSetOptionalData(message);
            sender.send(message);
        }
    }

    private void handleSetOptionalData(Message message) {
        String user = ((EditText) findViewById(R.id.user)).getText().toString();
        if (user.length() > 0) {
            message.setUser(user);
        }
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
