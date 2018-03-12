package clients;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

import UI.UserInterface;
import protocol.Protocol;

/**
 * TCP Client
 * @author juanm
 *
 */
public class TCP_Client extends Observable{

	/**
	 * Client socket
	 */
	private Socket socket;

	/**
	 * Client input stream
	 */
	private BufferedReader input;

	/**
	 * Client output stream
	 */
	private PrintWriter output;

	private boolean finishTransfer;

	private boolean interrupt;

	private String packageReceived;
	
	/**
	 * Class constructor
	 */
	public TCP_Client(UserInterface ui) {
		addObserver(ui);
		this.socket = null;
		this.input = null;
		this.output = null;
	}

	/**
	 * Method that connects the client with the server
	 * @param host host name of server
	 * @param port port of server
	 * @return
	 */
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

	/**
	 * Method that disconnects the socket
	 * @return disconnection status
	 */
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

	/**
	 * Method that gets available files from server
	 * @return available files
	 * @throws Exception
	 */
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

	/**
	 * Method that gets files from server
	 * @param name filename
	 * @throws Exception 
	 */
	public boolean getFile(String name) {
		try {
			output.println(Protocol.GET);
			String msg = input.readLine();
			finishTransfer = false;

			if(msg.equals(Protocol.ACK)) {
				System.out.println("Sending starts!");
				output.println(name);
				File file = new File("./sent-data/"+name);
				if(!file.exists()) {
					file.createNewFile();
				}
				BufferedOutputStream outFile = new BufferedOutputStream(new FileOutputStream(file));
				BufferedInputStream inFile = new BufferedInputStream(socket.getInputStream());

				//Initial 10240
				byte[] buffer = new byte[51200];
				int bytesRead;
				finishTransfer = false;
				while((bytesRead = inFile.read(buffer))!=-1) {
					outFile.write(buffer, 0, bytesRead);
					packageReceived = new String(buffer, 0, bytesRead);
					System.out.println(packageReceived);
					setChanged();
					notifyObservers(packageReceived);
				}
				System.out.println("File received");
				finishTransfer = true;
				
				outFile.flush();
				outFile.close();
				inFile.close();
			}
			return finishTransfer;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Method that returns the connection status
	 * @return Connection status
	 */
	public boolean isConnected() {
		return socket!=null && socket.isConnected();
	}

	public boolean finishTransfer() {
		return finishTransfer;
	}

	public String getPackageReceived() {
		return packageReceived;
	}

}
