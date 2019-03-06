package shakkiBotti9000PC;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import com.github.sarxos.webcam.Webcam;

public class Camera {
	
		
	public void takePicture() {
		Webcam webcam = null;
		Pattern pat = Pattern.compile("Logitech QuickCam 3000 .");
		Matcher m;
		for (Webcam webcams : Webcam.getWebcams()) {
			System.out.println(webcams.getName());
			m = pat.matcher(webcams.getName());
			if (m.matches()) {
				webcam = webcams;
			//	webcam.open();
			}
		}
		try {
			webcam.setViewSize(webcam.getViewSizes()[2]);
			webcam.open();
			
			ImageIO.write(webcam.getImage(), "PNG", new File("board.png"));
			Dimension[] d = webcam.getViewSizes();
			for (int i = 0; i < d.length; i++) {
				System.out.println(d[i]);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void analyzePicture() {
		
		
	}
	
}
