package ch.desm.middleware.app.module.tyrusserver.reference;

/**
 * Created by heisenberg on 28.03.17.
 */
public class WebsocketMessage {

    public String room;
    public String message;

    public WebsocketMessage(){}

    public WebsocketMessage(String room, String message){
        this.room = room;
        this.message = message;
    }

    public void setRoom(String room){
        this.room = room;
    }
}
