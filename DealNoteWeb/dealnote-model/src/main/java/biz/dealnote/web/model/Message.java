package biz.dealnote.web.model;

public class Message {
    private String type;
    private String message;
    private Boolean hasMessage;

    public Message(String type, String message) {
        this.type = type;
        this.message = message;
        this.hasMessage = true;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
    
    public Boolean getHasMessage(){
    	return this.hasMessage;
    }
}
