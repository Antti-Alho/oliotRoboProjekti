package tests;

import java.util.ArrayList;

import piece.*;
import shakkiBotti9000PC.*;

public class MovesTest {
	
	public static void main(String[] args) {
		
		Board board = new Board();
		ArrayList<Piece> pieces = board.getPieces();
		System.out.println("a b c d e f g h \n");
			
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece p = null;
				for (Piece piece : pieces) {
					if (piece.getPos().getX() == i && piece.getPos().getY() == j) {
						p = piece;
					} 
				}
				if (p == null) {
			System.out.print("X ");}
				else System.out.print(p.getName() + " ");
			}
			System.out.println(" " + (i+1));
			
		}
	}
}
							