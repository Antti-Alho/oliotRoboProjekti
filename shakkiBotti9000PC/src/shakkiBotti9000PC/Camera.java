package shakkiBotti9000PC;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

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
				webcam.open();
			}
		}
		try {
			ImageIO.write(webcam.getImage(), "PNG", new File("hello-world.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void analyzePicture() {
		
	}
	
}
