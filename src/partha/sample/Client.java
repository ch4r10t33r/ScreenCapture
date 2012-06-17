package partha.sample;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Robot;
import java.lang.Runnable;
import java.lang.Thread;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.util.Random;
import partha.sample.MyImage;

public class Client implements Runnable {

	private Socket client;
	private Random rangen;
	private Robot robot;
	private ObjectOutputStream oos;
	public Client() {
		try {
			rangen = new Random();
			robot = new Robot();
			client = new Socket("10.165.200.82",5000);
			oos = new ObjectOutputStream(client.getOutputStream());
			MyImage theImg = new MyImage();
			theImg.setImage(robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
		BufferedImage screenShot = null;
		while(true) {
			if(rangen.nextInt(1000)%5 == 0) {
				screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				MyImage theImg = new MyImage();
				theImg.setImage(screenShot);
				try {
					oos.writeObject(theImg);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
	public static void main(String []args) {
		Thread th = new Thread(new Client());
		th.start();
	}
}