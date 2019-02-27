package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Position;

public class Queen extends Piece{

	public Queen(Position pos, Boolean colour) {
		super(pos, colour);
		if (colour) {
			super.setArvo(100);
		} else {
			super.setArvo(-100);
		}
	}
	
	@Override
	public ArrayList<Position> getMoves() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int[][] eval = {
			{ -2, -1, -1, -1, -1, -1, -1, -2},
			{ -1,  0,  0,  0,  0,  0,  0, -1},
			{ -1,  0,  0,  1,  1,  0,  0, -1},
			{ -1,  0,  0,  1,  1,  0,  0, -1},
			{ -1,  0,  1,  1,  1,  1,  0, -1},
			{ -1,  1,  1,  1,  1,  1,  1, -1},
			{ -1,  0,  0,  0,  0,  0,  0, -1},
			{ -2, -1, -1, -1, -1, -1, -1, -2}
		};

}
