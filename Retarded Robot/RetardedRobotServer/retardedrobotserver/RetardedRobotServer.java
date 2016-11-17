/**
 * Retarded Robot Server.
 * @author Lesti | Studio.
 * @version 1.0.1
 */
package retardedrobotserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retardedia.*;

public class RetardedRobotServer {

    private static int NTHREADS = 100;
    private static final ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);

    private static Graph graph;

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: RetardedRobotServer <port>");
            System.exit(0);
        }

        int port = 0;
        ServerSocket server = null;
        
        try {

            System.out.println("Starting RetardedRobotServer...");
            port = Integer.parseInt(args[0]);
            server = new ServerSocket(port);
            System.out.println("Started RetardedRobotServer on port " + port);

            System.out.println("Starting IA...");
            graph = new Graph("./graph.dat");
            System.out.println("IA started successfully.");

            while (true) {
                Socket socket = server.accept();
                System.out.println("Socket accepted " + socket.toString());
                executor.execute(new SessionProtocol(socket, graph));
            }

        } catch (Exception e) {
            System.out.println("Cannot start server or an error occurred.");
            System.exit(1);
        }
    }
}
