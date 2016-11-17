
import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException {

		if (args.length != 1) {
			System.err.println("Usage: java Server <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		ServerSocket socket = new ServerSocket(portNumber);
		while (true) {
			final Socket connection = socket.accept();
			Runnable task = new Runnable() {
				public void run() {
					//handleRequest(connection);
					try (
							PrintWriter out
							= new PrintWriter(connection.getOutputStream(), true);
							BufferedReader in = new BufferedReader(
									new InputStreamReader(connection.getInputStream()));) {

						String inputLine, outputLine;

						// Initiate conversation with client
						BotProtocol bp = new BotProtocol();
						outputLine = bp.processInput(""); //lui mandava null
						out.println(outputLine);

						while ((inputLine = in.readLine()) != null) {
							outputLine = bp.processInput(inputLine);
							out.println(outputLine);
							if (outputLine.equals("Bye.")) {
								break;
							}
						}
					} catch (IOException e) {
						System.out.println("Exception caught when trying to listen on port "
								+ portNumber + " or listening for a connection");
						System.out.println(e.getMessage());
					}

				}
			};
			new Thread(task).start();
		}

		/*

		 try (
		 ServerSocket serverSocket = new ServerSocket(portNumber);
		 Socket clientSocket = serverSocket.accept();
		 PrintWriter out =
		 new PrintWriter(clientSocket.getOutputStream(), true);
		 BufferedReader in = new BufferedReader(
		 new InputStreamReader(clientSocket.getInputStream()));
		 ) {

		 String inputLine, outputLine;

		 // Initiate conversation with client
		 KnockKnockProtocol kkp = new KnockKnockProtocol();
		 outputLine = kkp.processInput(""); //lui mandava null
		 out.println(outputLine);

		 while ((inputLine = in.readLine()) != null) {
		 outputLine = kkp.processInput(inputLine);
		 out.println(outputLine);
		 if (outputLine.equals("Bye."))
		 break;
		 }
		 } catch (IOException e) {
		 System.out.println("Exception caught when trying to listen on port "
		 + portNumber + " or listening for a connection");
		 System.out.println(e.getMessage());
		 } */
	}
}
