package study.java.multimedia;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class ImageTest {
	public static void main(String[] args) throws Exception{
		byte[] myimageBytes = FileUtils.toByteArray2("E://fctest.jpg");
		InputStream  is =  new ByteInputStream(myimageBytes,myimageBytes.length);
		BufferedImage sourceImg =ImageIO.read(is); 
		System.out.println(sourceImg.getWidth());
		System.out.println(sourceImg.getHeight());

	}

}
