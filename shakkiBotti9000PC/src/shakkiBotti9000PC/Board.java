package shakkiBotti9000PC;

import java.util.ArrayList;
import java.util.Stack;

import piece.Piece;

public class Board {
	
	private ArrayList<Piece> pieces;
	private Stack<Move> moves;
	
	public Board() {
		
	}
	
	public ArrayList<Move> getLegalMoves(){
		ArrayList<Move> moves = new ArrayList<Move>();
		for (Piece piece : pieces) {
			moves.addAll(piece.getMoves(this));
		}
		return moves;
	}
	
	public ArrayList<Piece> getPieces(){
		return pieces;
	}

	public void move(Move move) {
		for (Piece piece : pieces) {
			if(move.getP() == piece) {
				piece.setPos(move.getNewPos());
				moves.add(move);
			}
		}
	}

	public void undo() {
		Piece p = moves.peek().getP();
		for (Piece piece : pieces) {
			if (p == piece) {
				piece.setPos(moves.pop().getOldPos());
			}
		}
	}
	
	public Boolean containsPiece(Position pos) {
		for (Piece piece : pieces) {
			if (piece.getPos() == pos) {
				return true;
			}
		} 
		return false;
	}
}
