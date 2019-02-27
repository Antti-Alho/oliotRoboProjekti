package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Position;

public class Bishop extends Piece{

	public Bishop(Position pos, Boolean colour) {
		super(pos, colour);
		super.setArvo(30);
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
