package ch.cloudlabx.chat_server_websocket;

import javax.websocket.Session;
import java.util.*;

/**
 * Created by heisenberg on 27.03.17.
 */
public class SessionStore {

    private HashMap<Session, String> sessions = new HashMap();
    private Object synchronize = new Object();

    public void addSession(Session session, String room){
        synchronized (synchronize){
            sessions.put(session, room);
        }
    }

    public void removeSession(Session session){
        synchronized (synchronize){
            sessions.remove(session);
        }
    }

    public boolean contains(Session session){
        return sessions.containsKey(session);
    }

    public HashMap<Session, String> getSessions(){
        synchronized (synchronize){
            return sessions;
        }
    }

}
