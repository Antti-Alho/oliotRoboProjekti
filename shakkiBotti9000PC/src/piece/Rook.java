package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

public class Rook extends Piece{

	public Rook(Position pos, Boolean colour) {
		super(pos, colour);
		if (colour) {
			super.setValue(50);
		} else {
			super.setValue(-50);
		}
	}
	
	/**
	 * returns an ArrayList of moves the piece can currently take:
	 */
	@Override
	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> newLegalMoves = new ArrayList<Move>();
		int x = this.getPos().getX();
		int y = this.getPos().getY();
		while (x <= 8 && y <= 8 ) {
			x++;
			y++;
			newLegalMoves.add(new Move(this,new Position(x, y)));
			if (board.containsPiece(new Position(x, y))) {
				break;
			}
		}
		
		return null;
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
