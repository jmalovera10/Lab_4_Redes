package servers;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * TCP Server
 * @author juanm
 *
 */
public class TCP_Server {
	
	public static final String SMALL_ARCHIVE = "./data";
	
	public static final String MEDIUM_ARCHIVE = "./data";
	
	public static final String LARGE_ARCHIVE = "./data";

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
				System.out.println("Connection accepted!");
				TCP_Worker worker=null;
				for(int i = 0; i < workers.length; i++) {
					if (!workers[i].isActive()) {
						worker=workers[i];
					}
				}
				if(worker==null) {
					PrintWriter output = new PrintWriter(client.getOutputStream(),true);
					output.println("Error service unavailable, too many clients.");
					output.close();
				}
				else {
					worker.initWorker(client);
					worker.start();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		TCP_Server server = new TCP_Server();
		server.start();
		
	}
}
