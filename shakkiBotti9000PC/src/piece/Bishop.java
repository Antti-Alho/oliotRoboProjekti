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
		Boolean eat = false;
		ArrayList<Move> newLegalMoves = new ArrayList<Move>();
		int x = this.getPos().getX();
		int y = this.getPos().getY();
		
		int i = x;
		int j = y;
		while (i <= 7 && j <= 7) {
			if (eat = true) {
				break;
			} else if (board.getPositions()[i][j].getPiece() == null) {
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
			} else if (board.getPositions()[i][j].getPiece().getColour() != super.getColour()){
				newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
				eat = true;
			} else {
				break;
			}
			i++;
			j++;
		}
		
		i = x;
		j = y;
		while (i >= 0 && j >= 0) {
			i++;
			j++;
		}
		
		i = x;
		j = y;
		while (i >= 0 && j <= 7) {
			i++;
			j++;
		}
		
		i = x;
		j = y;
		while (i <= 7 && j >= 0) {
			i++;
			j++;
		}
		
		return newLegalMoves;
	}

	/**
	 * array witch contains the pieces value in different positions
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
