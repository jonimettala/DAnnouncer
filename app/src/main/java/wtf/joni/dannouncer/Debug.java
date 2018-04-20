package wtf.joni.dannouncer;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Used to debug the application.
 *
 * @author Joni Mettälä
 */
public class Debug {
    private static int DEBUG_LEVEL;
    private static boolean DEBUG_CONSOLE;
    private static boolean DEBUG_TOAST;
    private static Context HOST;

    /**
     * Loads the debug information from the debug.xml file.
     * @param host Host activity
     */
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

    /**
     * Prints something to console.
     *
     * @param className Class name to be logged
     * @param methodName Method name to be logged
     * @param msg Message to be logged
     * @param level Debug level
     */
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

    /**
     * Prints something to console.
     *
     * @param className Class name to be logged
     * @param methodName Method name to be logged
     */
    public static void print(String className, String methodName) {
        print(className, methodName, "", 1);
    }

    /**
     * Prints something to console.
     *
     * @param className Class name to be logged
     * @param methodName Method name to be logged
     * @param msg Message to be logged
     */
    public static void print(String className, String methodName, String msg) {
        print(className, methodName, msg, 1);
    }

    /**
     * Prints something to console.
     *
     * @param className Class name to be logged
     * @param methodName Method name to be logged
     * @param level Debug level
     */
    public static void print(String className, String methodName, int level) {
        print(className, methodName, "", level);
    }
}