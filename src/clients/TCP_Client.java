package clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TCP Client
 * @author juanm
 *
 */
public class TCP_Client {
	
	private Socket socket;
	
	private BufferedReader input;
	
	private PrintWriter output;
	
	public TCP_Client() {
		try {
			socket = new Socket("localhost", 8080);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(),true);
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
