package wtf.joni.dannouncer;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * This class handles sending HTTP POST requests to Discord API.
 *
 * @author Joni Mettälä
 */
public class MessageSender {
    Context context;
    String url;
    final String TAG = "MessageSender";

    /**
     * Constructs the MessageSender object.
     *
     * @param url The Discord HTTP POST request url (also known as Discord webhook)
     * @param context Context of the activity which uses the object
     */
    public MessageSender(String url, Context context) {
        this.context = context;
        this.url = url;
    }

    /**
     * Sends given Message object to the defined webhook url.
     *
     * @param msg Message object which will be sent.
     * @see Message
     */
    public void send(Message msg) {
        final Message message = msg;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Debug.print(TAG, "send(): onResponse()", "Announcement sent");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Debug.print(TAG, "send(): onErrorResponse()");
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("content", message.getContent());
                String username = message.getUsername();
                if (username != null) {
                    Debug.print(TAG, "send(): getParams()", username);
                    params.put("username", username);
                }
                return params;
            }
        };
        Volley.newRequestQueue(context).add(postRequest);
    }
}
