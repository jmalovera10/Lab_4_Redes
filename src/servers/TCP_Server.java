package servers;
/**
 * TCP Server
 * @author juanm
 *
 */

import java.io.IOException;
import java.net.ServerSocket;

public class TCP_Server {

	private ServerSocket server;
	
	public TCP_Server() {
		try {
			server = new ServerSocket(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	}
}
