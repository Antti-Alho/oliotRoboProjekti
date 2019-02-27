package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Position;

public class Pawn extends Piece{

	public Pawn(Position pos, Boolean colour) {
		super(pos, colour);
		if (colour) {
			super.setArvo(10);
		} else {
			super.setArvo(-10);
		}
	}
	
	@Override
	public ArrayList<Position> getMoves() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int[][] eval = {
			{  0,  0,  0,  0,  0,  0,  0,  0},
			{  5,  5,  5,  5,  5,  5,  5,  5},
			{  1,  1,  2,  3,  3,  2,  1,  1},
			{  1,  0,  1,  2,  2,  1,  0,  1},
			{  0,  0,  1,  1,  1,  1,  0,  0},
			{  1, -1, -1,  0,  0, -1, -1,  1},
			{  1,  1,  1, -2, -2,  1,  1,  1},
			{  0,  0,  0,  0,  0,  0,  0,  0}
		};

}
