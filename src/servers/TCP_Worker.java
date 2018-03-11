package servers;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

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
		while(client.isConnected()) {
			try {
				if(input.ready()) {
					inMessage = input.readLine();
					switch (state) {
					case "begining":
						if(inMessage.equals(Protocol.HELLO)) {
							output.println(Protocol.HELLO);
							output.println(Protocol.DIR+Protocol.SEP+"3");
							output.println("small.PNG");
							output.println("medium.mp4");
							output.println("big.mp4");
							state="established";
						}
						break;
					case "established":
						if(inMessage.equals(Protocol.GET)) {
							output.println(Protocol.ACK);
							state="onDemand";
						}
						else if(inMessage.equals(Protocol.BYE)) {
							output.println(Protocol.BYE);
							state="closed";
							client.close();
							this.closeConnection();
						}
						break;
					case "onDemand":
						if(inMessage.equals("small.PNG")) {
							File file = new File("./data/small.PNG");
							BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());
							BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));

							byte[] buffer = new byte[4096];
							int bytesRead;
							while((bytesRead = reader.read(buffer))!=-1) {
								out.write(buffer, 0, bytesRead);
							}

							reader.close();
							out.flush();
							out.close();
							/*
					        BufferedImage image = ImageIO.read(new File("/data/small.PNG"));
					        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					        ImageIO.write(image, "jpg", byteArrayOutputStream);

					        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
					        output.println(size);
					        output.println(byteArrayOutputStream.toByteArray());
					        output.flush();
					        System.out.println("Flushed: " + System.currentTimeMillis());
							//send small*/
							state="established";
						}
						else if(inMessage.equals("medium.mp4")) {
							File file = new File("./data/medium.mp4");
							BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());
							BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));

							byte[] buffer = new byte[4096];
							int bytesRead;
							while((bytesRead = reader.read(buffer))!=-1) {
								out.write(buffer, 0, bytesRead);
							}

							reader.close();
							out.flush();
							out.close();
							/*
							  BufferedImage image = ImageIO.read(new File("/data/medium.mp4"));
						        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
						        ImageIO.write(image, "jpg", byteArrayOutputStream);

						        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
						        output.println(size);
						        output.println(byteArrayOutputStream.toByteArray());
						        output.flush();
						        System.out.println("Flushed: " + System.currentTimeMillis());*/
							state="established";
						}
						else if(inMessage.equals("big.mp4")) {
							File file = new File("./data/big.mp4");
							BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());
							BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));

							byte[] buffer = new byte[4096];
							int bytesRead;
							while((bytesRead = reader.read(buffer))!=-1) {
								out.write(buffer, 0, bytesRead);
							}

							reader.close();
							out.flush();
							out.close();
							/*
							BufferedImage image = ImageIO.read(new File("/data/big.mp4"));
					        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					        ImageIO.write(image, "jpg", byteArrayOutputStream);

					        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
					        output.println(size);
					        output.println(byteArrayOutputStream.toByteArray());
					        output.flush();
					        System.out.println("Flushed: " + System.currentTimeMillis());
							//send small*/
							state="established";
						}
						else if(inMessage.equals(Protocol.BYE)) {
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
				e.printStackTrace();
			}
		}
	}

	public boolean isActive() {
		return active;
	}

}
