package shakkiBotti9000PC;

import java.util.ArrayList;
import java.util.Stack;
import piece.*;
import piece.Piece;

public class Board {
	
	private Stack<Move> moves;
	ArrayList<Piece> pieces= new ArrayList<>();
	//Kaikki pelinappulat aloituspaikoilleen

	public Board() {
			pieces.add(new Knight(new Position(0, 1), Piece.BLACK));
			pieces.add(new Knight(new Position(0, 6), Piece.BLACK));
			pieces.add(new Knight(new Position(7,1), Piece.WHITE));
			pieces.add(new Knight(new Position(7,6), Piece.WHITE));
			pieces.add(new Bishop(new Position(0, 2), Piece.BLACK));
			pieces.add(new Bishop(new Position(0, 5), Piece.BLACK));
			pieces.add(new Bishop(new Position(7, 2), Piece.WHITE));
			pieces.add(new Bishop(new Position(7, 5), Piece.WHITE));
			pieces.add(new Rook(new Position(0, 0), Piece.BLACK));
			pieces.add(new Rook(new Position(0, 7), Piece.BLACK));
			pieces.add(new Rook(new Position(7, 0), Piece.WHITE));
			pieces.add(new Rook(new Position(7, 7), Piece.WHITE));
			pieces.add(new Queen(new Position(0, 3), Piece.BLACK));
			pieces.add(new Queen(new Position(7, 4), Piece.WHITE));
			pieces.add(new King(new Position(0, 4), Piece.BLACK));
			pieces.add(new King(new Position(7, 3), Piece.WHITE));
			for (int i = 0; i <8; i++) {
				pieces.add(new Pawn(new Position(1, i), Piece.BLACK));
				}
			for (int i = 0; i <8; i++) {
				pieces.add(new Pawn(new Position(6, i), Piece.WHITE));
			}
		
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
