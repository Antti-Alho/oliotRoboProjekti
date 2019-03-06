package tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

import piece.*;
import shakkiBotti9000PC.*;

public class MovesTest {

	/**
	 * launches the command line UI mainly used for logic testing.
	 * 
	 */
	public static void main(String[] args) {
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
		
		
		Board board = new Board();
		Position[][] positions = board.getPositions();
		
		System.out.println("  ");
		System.out.println("a b c d e f g h \n");
		for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length; j++) {
				System.out.print(positions[i][j].getPieceString() + " ");
			}
			System.out.println("  "+(i+1));
		}
		
		
	}
}
							