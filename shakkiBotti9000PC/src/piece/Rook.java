package piece;

import shakkiBotti9000PC.Position;

public class Rook extends Piece{

	public Rook(Position pos, Boolean colour) {
		super(pos, colour);
		if (colour) {
			super.setArvo(50);
		} else {
			super.setArvo(-50);
		}
	}
	
	private int[][] eval = {
			{  0,  0,  0,  0,  0,  0,  0,  0},
			{  1,  2,  2,  2,  2,  2,  2,  1},
			{ -1,  0,  0,  0,  0,  0,  0, -1},
			{ -1,  0,  0,  0,  0,  0,  0, -1},
			{ -1,  0,  0,  0,  0,  0,  0, -1},
			{ -1,  0,  0,  0,  0,  0,  0, -1},
			{ -1,  0,  0,  0,  0,  0,  0, -1},
			{  0,  0,  0,  1,  1,  0,  0,  0}
		};

}
