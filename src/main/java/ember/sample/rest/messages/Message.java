package ember.sample.rest.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

    @JsonProperty("type")
    private String type;

    private String message;

    public Message() {
        this("Message");
    }

    public Message(String type) {
        super();
        this.type = type;
    }

    public Message(String type, String message) {
        super();
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
