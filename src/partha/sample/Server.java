
package partha.sample;

import java.awt.image.BufferedImage;
import java.lang.Thread;
import java.lang.Runnable;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.awt.Graphics;
import java.awt.FlowLayout;
import partha.sample.MyImage;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class Server extends JLabel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyImage theImg;
	private ObjectInputStream oin;
	private Socket theClient;

	public Server(Socket theClient) {
		// TODO Auto-generated constructor stub
		try {
			
			this.theClient = theClient;
			oin = new ObjectInputStream(this.theClient.getInputStream());
			theImg = (MyImage) oin.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				theImg = (MyImage) oin.readObject();
				System.out.println("In run");
				repaint();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void paint(Graphics g) {
		if (theImg != null) {
			System.out.println(theImg);
			g.drawImage(theImg.getImage(),0,0,this.getWidth(),this.getHeight(),this);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JFrame jf = new JFrame("Screen Capture");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new FlowLayout());
		jf.setSize(800,800);
		jf.setVisible(true);
		ServerSocket srv = new ServerSocket(5000);
		while (true) {
			Socket theClient = srv.accept();
			Server theLabel = new Server(theClient);
			theLabel.setSize(100,100);
			jf.getContentPane().add(theLabel);
			Thread th = new Thread(theLabel);
			th.start();
			jf.getContentPane().validate();
					
		}
	}
}