package tests;


import java.util.ArrayList;

import piece.*;
import shakkiBotti9000PC.*;

public class MovesTest {
	
	public static void main(String[] args) {
		Camera cam = new Camera();
		
		cam.takePicture();
		
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
							