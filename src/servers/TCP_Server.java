package servers;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * TCP Server
 * @author juanm
 *
 */
public class TCP_Server {

	public static final int MAX_CONN = 10;
	
	private ServerSocket server;
	
	private TCP_Worker[] workers;
	
	public TCP_Server() {
		try {
			workers = new TCP_Worker[10];
			for (int i = 0; i < workers.length; i++) {
				workers[i] = new TCP_Worker();
			}
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
