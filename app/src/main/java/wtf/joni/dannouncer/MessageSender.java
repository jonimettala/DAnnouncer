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
 * Created by Joni Mettälä on 17-Mar-18.
 */

public class MessageSender {
    Context context;
    String url;
    String TAG = "MessageSender";

    public MessageSender(String url, Context context) {
        this.context = context;
        this.url = url;
    }

    public void send(Message msg) {
        final Message message = msg;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*
                        try {
                            JSONObject jsonResponse = new JSONObject(response).getJSONObject("form");
                            String site = jsonResponse.getString("site"),
                                    network = jsonResponse.getString("network");
                            System.out.println("Site: "+site+"\nNetwork: "+network);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        */
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
                return params;
            }
        };
        Volley.newRequestQueue(context).add(postRequest);
    }
}
