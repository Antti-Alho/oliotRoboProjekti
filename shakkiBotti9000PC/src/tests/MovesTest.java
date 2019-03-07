package tests;


import java.util.ArrayList;
import java.util.Scanner;

import piece.*;
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
			cam.analyzePicture();
			cam.situation();
	
			
			Position[][] positions = board.getPositions();
			Position[][] enemy = cam.getEnemymove();
	
			
	
			System.out.println("--------------------");
			System.out.println("a b c d e f g h \n");
			for (int i = 0; i < positions.length; i++) {
				for (int j = 0; j < positions.length; j++) {
					System.out.print(enemy[i][j].getPieceString() + " ");
				}
				System.out.println("  "+(i+1));
			}
			
			System.out.println(positions[5][2].getPieceString());
			System.out.println("--------------------");
			System.out.println("a b c d e f g h \n");
			for (int i = 0; i < positions.length; i++) {
				for (int j = 0; j < positions.length; j++) {
					System.out.print(positions[i][j].getPieceString() + " ");
				}
				System.out.println("  "+(i+1));
			}
			System.out.println("********************* \n\nAgain");
			answer = sc.next();
		}
	}
}

