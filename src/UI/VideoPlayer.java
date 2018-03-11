package UI;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class VideoPlayer {


	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
	}
	
	private JFrame frame;
	private JLabel imageLabel;
	
	public void initGUI() {
		frame = new JFrame("Video Playback Example");  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setSize(400,400);  
		imageLabel = new JLabel();
		frame.add(imageLabel);
		frame.setVisible(true);       
	}

	public void runMainLoop(String file) throws InterruptedException {
		Mat webcamMatImage = new Mat();  
		Image tempImage;  
		VideoCapture capture = new VideoCapture();
		capture.open("./sent-data/"+file);
		if( capture.isOpened()){  
			while (true){  
				capture.read(webcamMatImage);  
				if( !webcamMatImage.empty() ){  
					tempImage= toBufferedImage(webcamMatImage);
					ImageIcon imageIcon = new ImageIcon(tempImage, "Video playback");
					imageLabel.setIcon(imageIcon);
					frame.pack();  //this will resize the window to fit the image
					Thread.sleep(50);
				}  
				else{  
					System.out.println(" Frame not captured or video has finished"); 
					break;  
				}
			}  
		}
		else{
			System.out.println("Couldn't open video file.");
		}
		
	}

	
	public BufferedImage toBufferedImage(Mat matrix){
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if ( matrix.channels() > 1 ) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = matrix.channels()*matrix.cols()*matrix.rows();
		byte [] buffer = new byte[bufferSize];
		matrix.get(0,0,buffer); // get all the pixels
		BufferedImage image = new BufferedImage(matrix.cols(),matrix.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);  
		return image;
	}
	
	public static void main(String[] args) throws InterruptedException {
		VideoPlayer app = new VideoPlayer();
		app.initGUI();
		app.runMainLoop("medium.mp4");
	}

}
