package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

public class Pawn extends Piece{
	
	private Position firstPos;
	
	/*
	 * evaluation array witch tells how much the piece if worth in each position.
	 */
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
	
	public Pawn(Position pos, Boolean colour) {
		super(pos, colour);
		firstPos = pos;
		if (colour) {
			super.setValue(10);
		} else {
			super.setValue(-10);
		}
	}
  
	/**
	 * Returns the string that represents this piece in the command line UI
	 */
	@Override
	  public String getName(){
	    String s = "p";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	  }
	
	/**
	 * return the current value of the piece to the minMax algorithm,
	 * this including the position evaluation that is read from the evaluation table.
	 */
	@Override
	public int getValue() {
		return eval[super.getPos().getX()][super.getPos().getY()] + super.getValue();
	}
	
	/**
	 * returns an ArrayList of moves the piece can currently take.
	 */
	@Override
	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> newLegalMoves = new ArrayList<Move>();
		int x = this.getPos().getX();
		int y = this.getPos().getY();
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			if (super.getColour()) {
				if (board.getPositions()[x][y--].getPiece() == null) {
					newLegalMoves.add(new Move(this, board.getPositions()[x][y-1]));
				}
				if (board.canEat(this, board.getPositions()[x+1][y+1])){
					newLegalMoves.add(new Move(this, board.getPositions()[x+1][y-1]));
				} 
				if (board.canEat(this, board.getPositions()[x-1][y+1])) {
					newLegalMoves.add(new Move(this, board.getPositions()[x-1][y-1]));
				}
				if (board.getPositions()[x][y-2].getPiece() == null && this.getPos() == firstPos) {
					newLegalMoves.add(new Move(this, board.getPositions()[x][y-2]));
				}
			} else {
				if (board.getPositions()[x][y++].getPiece() == null) {
					newLegalMoves.add(new Move(this, board.getPositions()[x][y+1]));
				}
				if (board.canEat(this, board.getPositions()[x+1][y+1])){
					newLegalMoves.add(new Move(this, board.getPositions()[x+1][y+1]));
				} 
				if (board.canEat(this, board.getPositions()[x-1][y+1])) {
					newLegalMoves.add(new Move(this, board.getPositions()[x-1][y+1]));
				}
				if (board.getPositions()[x][y+2].getPiece() == null && this.getPos() == firstPos) {
					newLegalMoves.add(new Move(this, board.getPositions()[x][y+2]));
				}
			}
		}
		
		return newLegalMoves;
	}


}
