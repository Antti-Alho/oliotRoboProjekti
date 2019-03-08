package tests;

import java.util.Scanner;
import shakkiBotti9000PC.*;


public class MovesTest {

	static Scanner sc = new Scanner(System.in);

	/**
	 * launches the command line UI mainly used for logic testing.
	 * 
	 */

	public static void main(String[] args) {
		Board board = new Board();
		Camera cam = new Camera(board);
		String answer=null;
	    		
		while (answer != "x") {
			cam.takePicture();
			cam.getEnemymove();
				
			System.out.println("********************* \n\nAgain");
			answer = sc.next();
		}
	}
}

