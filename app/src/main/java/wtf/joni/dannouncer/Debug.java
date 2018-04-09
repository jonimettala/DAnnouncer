package wtf.joni.dannouncer;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by joni on 15/01/2018.
 */

public class Debug {
    private static int DEBUG_LEVEL;
    private static boolean DEBUG_CONSOLE;
    private static boolean DEBUG_TOAST;
    private static Context HOST;

    public static void loadDebug(Context host) {
        try {
            DEBUG_LEVEL = Integer.parseInt(host.getString(R.string.debugLevel));
            DEBUG_CONSOLE = Boolean.parseBoolean(host.getString(R.string.debugConsole));
            DEBUG_TOAST = Boolean.parseBoolean(host.getString(R.string.debugToast));
            HOST = host;
        } catch (Exception e) {
            DEBUG_LEVEL = 0;
        }
    }

    public static void print(String className, String methodName, String msg, int level) {
        if (DEBUG_LEVEL >= level) {
            if (BuildConfig.DEBUG) {
                String outputMsg = methodName;
                if (msg.length() > 0) {
                    outputMsg += ", " + msg;
                }
                if (DEBUG_CONSOLE) {
                    Log.d(className, outputMsg);
                }
                if (DEBUG_TOAST) {
                    Context context = HOST.getApplicationContext();
                    CharSequence text = outputMsg;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
            }
        }
    }
    public static void print(String className, String methodName) {
        print(className, methodName, "", 1);
    }
    public static void print(String className, String methodName, String msg) {
        print(className, methodName, msg, 1);
    }
    public static void print(String className, String methodName, int level) {
        print(className, methodName, "", level);
    }
}