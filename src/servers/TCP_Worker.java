package servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import protocol.Protocol;

public class TCP_Worker extends Thread{

	private boolean active;
	private Socket client;
	private BufferedReader input;
	private PrintWriter output;
	private String inMessage;
	private String outMessage;
	private String state;

	public TCP_Worker() {
		// TODO Auto-generated constructor stub
		active = false;
	}


	public void initWorker(Socket client) {
		this.client = client;
		try {
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			output = new PrintWriter(client.getOutputStream(),true);
			active = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		this.client=null;
		try {
			this.input.close();
			this.input= null;
			this.output.close();
			this.output=null;
			this.active=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		state="begining";
		while(!client.isClosed()) {
			try {
				if(input.ready()) {
					inMessage = input.readLine();
					switch (state) {
					case "begining":
						if(inMessage==Protocol.HELLO) {
							output.println(Protocol.HELLO);
							output.println(Protocol.DIR);
							output.print("small.PNG");
							output.print("medium.mp4");
							output.print("big.mp4");
							state="established";
						}
						break;
					case "established":
						if(inMessage==Protocol.GET) {
							output.println(Protocol.ACK);
							state="onDemand";
						}
						else if(inMessage==Protocol.BYE) {
							output.println(Protocol.BYE);
							state="closed";
							client.close();
							this.closeConnection();
						}
						break;
					case "onDemand":
						if(inMessage=="small.PNG") {
							//send small
							state="sending";
						}
						else if(inMessage=="medium.mp4") {
							//send m
							state="sending";
						}
						else if(inMessage=="big.mp4") {
							//send m
							state="sending";
						}
						else if(inMessage==Protocol.BYE) {
							output.println(Protocol.BYE);
							state="closed";
							client.close();
							this.closeConnection();
						}
						break;
					case "sending":
						if(inMessage==Protocol.BYE) {
							output.println(Protocol.BYE);
							state="closed";
							client.close();
							this.closeConnection();
						}
						break;
					}

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
