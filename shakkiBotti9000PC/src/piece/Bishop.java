package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

public class Bishop extends Piece{

	/**
	 * Creates a new Bishop piece.
	 * @param pos
	 * @param colour
	 */
	public Bishop(Position pos, Boolean colour) {
		super(pos, colour);
		super.setValue(30);
	}
  
	/**
	 * Returns the string that represents this piece in the command line UI
	 */
	@Override
	  public String getName(){
	    String s = "b";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	  }
	
	/**
	 * return the current value of the piece to the minMax algorithm,
	 * this includes the position evaluation that is read from the evaluation table.
	 */
	@Override
	public int getValue() {
		return eval[super.getPos().getX()][super.getPos().getY()] + super.getValue();
	}
	
	/**
	 * returns an ArrayList of all possible moves the piece can currently take.
	 */
	@Override
	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> newLegalMoves = new ArrayList<Move>();
		int x = this.getPos().getX();
		int y = this.getPos().getY();
		
		int i = x+1;
		int j = y+1;
		while (i <= 7 && j <= 7) {
			if (board.getPositions()[i][j].getPiece() == null) {
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			} else if (board.getPositions()[i][j].getPiece().getColour() != this.getColour()){
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
				break;
			} else {
				break;
			}
			i++;
			j++;
		}
		
		i = x-1;
		j = y-1;
		while (i >= 0 && j >= 0) {
			if (board.getPositions()[i][j].getPiece() == null) {
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			} else if (board.getPositions()[i][j].getPiece().getColour() != this.getColour()){
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
				break;
			} else {
				break;
			}
			i--;
			j--;
		}
		
		i = x-1;
		j = y+1;
		while (i >= 0 && j <= 7) {
			if (board.getPositions()[i][j].getPiece() == null) {
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			} else if (board.getPositions()[i][j].getPiece().getColour() != this.getColour()){
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
				break;
			} else {
				break;
			}
			i--;
			j++;
		}
		
		i = x+1;
		j = y-1;
		while (i <= 7 && j >= 0) {
			if (board.getPositions()[i][j].getPiece() == null) {
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			} else if (board.getPositions()[i][j].getPiece().getColour() != this.getColour()){
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
				break;
			} else {
				break;
			}
			i++;
			j--;
		}
		
		return newLegalMoves;
	}

	/**
	 * An array that contains piece's value in different positions
	 */
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
