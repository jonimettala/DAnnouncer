package wtf.joni.dannouncer;

/**
 * Created by Joni Mettälä on 17-Mar-18.
 */

public class Webhook {
    private String name;
    private String url;

    public Webhook(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
