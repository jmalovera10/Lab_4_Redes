package servers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCP_Worker extends Thread{

	private boolean active;
	private Socket client;
	private BufferedReader input;
	private PrintWriter output;
	private String inMessage;
	private String outMessage;

	public TCP_Worker() {
		// TODO Auto-generated constructor stub
		active = false;
	}
	
	public TCP_Worker(Socket client) {
		this.client = client;
		try {
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			output = new PrintWriter(client.getOutputStream());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(!client.isClosed()) {
			try {
				if(input.ready()) {
					inMessage = input.readLine();
				}
			}
			catch(Exception e) {

			}
		}
	}

	public boolean isActive() {
		return active;
	}
	
}
