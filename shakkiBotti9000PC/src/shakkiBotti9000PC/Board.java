package shakkiBotti9000PC;

import java.util.ArrayList;
import piece.Piece;

public class Board {
	ArrayList<Piece> pieces;
	public Board() {
		
	}
	
	public ArrayList<Move> getLegalMoves(){
		ArrayList<Move> moves = new ArrayList<Move>();
		return moves;
	}
	
	public ArrayList<Piece> getPieces(){
		return pieces;
	}

	public Board uglyMove(Move move) {
		// TODO Auto-generated method stub
		return null;
	}

	public Board move(Move move) {
		// TODO Auto-generated method stub
		return null;
	}

	public void undo() {
		// TODO undo Last move that was done needs at least 3 last moves
		
	}
}
