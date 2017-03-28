package ch.cloudlabx.chat_server_websocket;

import org.glassfish.tyrus.server.Server;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerStart {

    public static void main(String[] args){
        String hostIp = args[0]; //localhost
        int port = new Integer(args[1]); //port
        String rootContext = "/room";
        runServer(hostIp, port, rootContext);
    }


    public static void runServer(String hostIp, int port, String rootContext) {
        Server server = new Server(hostIp, port, rootContext, null, ServerEndpoint.class);

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