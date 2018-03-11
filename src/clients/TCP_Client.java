package clients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
		this.socket = null;
		this.input = null;
		this.output = null;
	}
	
	public boolean connect() {
		try {
			socket = new Socket("localhost", 8080);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(),true);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean disconnect() {
		try {
			socket.close();
			input.close();
			output.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isConnected() {
		return socket!=null && socket.isConnected();
	}

}
