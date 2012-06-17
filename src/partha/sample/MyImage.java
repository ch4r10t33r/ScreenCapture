package partha.sample;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.Serializable;

public class MyImage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon img;
	public MyImage () {
		img = new ImageIcon();
	}
	public Image getImage() {
		return img.getImage();
	}
	public void setImage(BufferedImage img) {
		this.img.setImage(img);
	}
}