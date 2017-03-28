package ch.desm.middleware.app.module.tyrusserver.reference;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.*;

@javax.websocket.server.ServerEndpoint(value = "/{nr}")
public class ServerEndpoint {

    private static final Logger LOGGER = Logger.getLogger(ServerEndpoint.class);
    final static SessionStore sessionStore = new SessionStore();

    @OnOpen
    public void onOpen(Session session, @PathParam("nr") final String nr) {
        LOGGER.log(Level.INFO, "opened Session: " + session.getId() + "in room nr "+ nr + "; sub protocol:" + session.getNegotiatedSubprotocol() + "; protocol version:" + session.getProtocolVersion());
        sessionStore.addSession(session, nr);
    }

    @OnClose
    public void onConnectionClose(Session session) {
        sessionStore.removeSession(session);
    }


    @OnMessage
    public void onMessage(String message, Session session) {
        LOGGER.log(Level.INFO, "received message: " + message);

        Map<Session, String> sessions = sessionStore.getSessions();
        Iterator<Map.Entry<Session, String>> iter = sessions.entrySet().iterator();
        WebsocketMessage receivedWsMsg = new Gson().fromJson(message,WebsocketMessage.class);

        while(iter.hasNext()){
            Map.Entry<Session, String> entry = iter.next();

            try {
                Session actSession = entry.getKey();
                actSession.getBasicRemote().sendText(message);
                LOGGER.log(Level.INFO, "send message to session: " + session.getId() + "; sub protocol:" + session.getNegotiatedSubprotocol() + "; protocol version:" + session.getProtocolVersion());
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "failed send message with session: " + session.getId() + "; protocol:" + session.getNegotiatedSubprotocol() + "; protocol version:" + session.getProtocolVersion());
            }
        }
    }

    @OnError
    public void onError(Throwable t){
        LOGGER.log(Level.INFO, "websocket error: " + t);
    }

    /*
     *   TODO Implementation
     *   stay alive: server send ping and all clients send a pong
     */
    public void sendPing(){

    }
}