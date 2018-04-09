package wtf.joni.dannouncer;

/**
 * Created by Joni Mettälä on 17-Mar-18.
 */

public class Message {
    String url;
    String content;
    String user;

    public Message(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
