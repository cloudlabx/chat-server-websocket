package ch.cloudlabx.chat_server_websocket;

import org.apache.log4j.Logger;
import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerStart {

    private static final Logger LOGGER = Logger.getLogger(ServerStart.class);
    private static boolean shutdown = false;

    public static void main(String[] args) throws InterruptedException, DeploymentException {
        String hostIp = args[0]; //localhost
        int port = new Integer(args[1]); //port
        String rootContext = "/room";

        final Server server = new Server(hostIp, port, rootContext, null, ServerEndpoint.class);
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                synchronized (this){
                    System.out.println("************ shutdown **************");
                    shutdown=true;
                    server.stop();
                }
            }
        });

        while (!shutdown)
        {
            Thread.sleep(5000);
        }


    }




}