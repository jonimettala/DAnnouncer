package wtf.joni.dannouncer;

/**
 * Created by Joni Mettälä on 17-Mar-18.
 */

public class Message {
    String url;
    String content;
    String username;

    public Message() {}

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
