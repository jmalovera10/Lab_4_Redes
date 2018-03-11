package clients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import protocol.Protocol;

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

	public boolean connect(String host, String port) {
		try {
			int portm = Integer.parseInt(port);
			socket = new Socket(host, portm);
			socket.setKeepAlive(true);
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
			socket = null;
			input = null;
			output = null;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<String> getAvailableFiles() throws Exception{
		ArrayList<String> files = new ArrayList<String>();
		output.println(Protocol.HELLO);
		while(!input.ready());
		String line = input.readLine();
		if(line.equals(Protocol.HELLO)) {
			while(!input.ready());
			line = input.readLine();
			if(line.startsWith(Protocol.DIR)) {
				int num = Integer.parseInt(line.split(Protocol.SEP)[1]);
				while(num-->0) {
					files.add(input.readLine());
				}
			}
		}else throw new Exception("Protocol Error");

		return files;
	}

	public boolean isConnected() {
		return socket!=null && socket.isConnected();
	}

}
