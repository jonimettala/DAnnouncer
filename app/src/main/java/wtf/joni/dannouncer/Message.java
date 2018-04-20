package wtf.joni.dannouncer;

/**
 * Message object contains all the information which will be sent to Discord webhook as a HTTP POST
 * request.
 *
 * @author Joni Mettälä
 */
public class Message {
    /**
     * Discord webhook url to which the Message will be sent as a HTTP POST request.
     */
    private String url;

    /**
     * Text which will be shown as a message in Discord.
     */
    private String content;

    /**
     * A name which will be shown in Discord as the message sender.
     */
    private String username;


    /**
     * Constructs a blank Message.
     */
    public Message() {}

    /**
     * Constructs a Message with url and content.
     *
     * @param url Discord webhook url
     * @param content Message content
     *
     * @see #url
     * @see #content
     */
    public Message(String url, String content) {
        this.url = url;
        this.content = content;
    }

    /**
     * Gets url defined to Message.
     *
     * @return Message url
     *
     * @see #url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url to Message.
     *
     * @param url Message url
     *
     * @see #url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets content defined to Message.
     *
     * @return Message content
     *
     * @see #content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content to Message.
     *
     * @param content Message content
     *
     * @see #content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets username defined to Message.
     *
     * @return Message username
     *
     * @see #username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username dto Message.
     *
     * @param username Message username
     *
     * @see #username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
