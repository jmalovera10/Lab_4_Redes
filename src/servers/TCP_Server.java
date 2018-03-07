package servers;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP Server
 * @author juanm
 *
 */
public class TCP_Server {

	private ServerSocket server;
	
	public TCP_Server() {
		try {
			server = new ServerSocket(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		while(true) {
			try {
				Socket client = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
}
