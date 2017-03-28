package ch.desm.middleware.app.module.tyrusserver;

import ch.desm.middleware.app.module.tyrusserver.reference.ServerEndpoint;
import org.glassfish.tyrus.server.Server;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Start {

    public static void main(String[] args){
       runServer();
    }


    public static void runServer() {
        Server server = new Server("localhost", 8070, "/room", null, ServerEndpoint.class);

        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please press a key to stop the server.");
            reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }

}