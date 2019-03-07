package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

public class Knight extends Piece{
	public Knight(Position pos, Boolean colour) {
		super(pos, colour);
		if (colour) {
			super.setValue(30);
		} else {
			super.setValue(-30);
		}
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
	 * returns an ArrayList of moves the piece can currently take:
	 */
	@Override
	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> newLegalMoves = new ArrayList<Move>();
		int x = this.getPos().getX();
		int y = this.getPos().getY();
		
		int i = x+2;
		int j = y+1;
		if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
			if (board.getPositions()[i][j].getPiece() == null) {
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			} else if (board.getPositions()[i][j].getPiece().getColour() != this.getColour()){
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			}
		}

		
		i = x-2;
		j = y-1;
		if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
			if (board.getPositions()[i][j].getPiece() == null) {
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			} else if (board.getPositions()[i][j].getPiece().getColour() != this.getColour()){
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			}
		}
		
		i = x+2;
		j = y-1;
		if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
			if (board.getPositions()[i][j].getPiece() == null) {
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			} else if (board.getPositions()[i][j].getPiece().getColour() != this.getColour()){
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			}
		}
		
		i = x-2;
		j = y+1;
		if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
			if (board.getPositions()[i][j].getPiece() == null) {
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			} else if (board.getPositions()[i][j].getPiece().getColour() != this.getColour()){
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			}
		}
		
		i = x+1;
		j = y+2;
		if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
			if (board.getPositions()[i][j].getPiece() == null) {
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			} else if (board.getPositions()[i][j].getPiece().getColour() != this.getColour()){
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			}
		}

		
		i = x-1;
		j = y-2;
		if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
			if (board.getPositions()[i][j].getPiece() == null) {
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			} else if (board.getPositions()[i][j].getPiece().getColour() != this.getColour()){
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			}
		}
		
		i = x+1;
		j = y-2;
		if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
			if (board.getPositions()[i][j].getPiece() == null) {
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			} else if (board.getPositions()[i][j].getPiece().getColour() != this.getColour()){
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			}
		}
		
		i = x-1;
		j = y+2;
		if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
			if (board.getPositions()[i][j].getPiece() == null) {
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			} else if (board.getPositions()[i][j].getPiece().getColour() != this.getColour()){
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			}
		}
		return newLegalMoves;
	}
	
	/**
	 * Returns the string that represents this piece in the command line UI
	 */
	@Override
	public String getName(){
	    String s = "n";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	}
	
	private int[][] eval = {
			{ -5, -4, -3, -3, -3, -3, -5, -5},
			{ -4, -2,  0,  0,  0,  0, -2, -4},
			{ -3,  0,  1,  2,  2,  1,  0, -3},
			{ -3,  1,  2,  4,  4,  2,  1, -3},
			{ -3,  0,  2,  4,  4,  2,  0, -3},
			{ -3,  1,  1,  2,  2,  1,  1, -3},
			{ -4, -2,  0,  1,  1,  0, -2, -4},
			{ -5, -4, -3, -3, -3, -3, -4, -5}
	};

}
