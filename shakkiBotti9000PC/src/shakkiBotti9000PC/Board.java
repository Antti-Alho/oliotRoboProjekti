package shakkiBotti9000PC;

import java.util.ArrayList;
import java.util.Stack;
import piece.*;

public class Board {
	
	private Stack<Move> moves;
	ArrayList<Piece> pieces= new ArrayList<>();
	Position[][] positions = new Position[8][8];
	
	/**
	 * The board constructor creates the starting position of the game.
	 */
	public Board() {
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				positions[i][j] = new Position(i, j);
			}
		}
		
		positions[0][1].setPiece(new Knight(positions[0][1], Piece.BLACK));
		positions[0][6].setPiece(new Knight(positions[0][6], Piece.BLACK));
		positions[7][1].setPiece(new Knight(positions[7][1], Piece.WHITE));
		positions[7][6].setPiece(new Knight(positions[7][6], Piece.WHITE));
		positions[0][2].setPiece(new Bishop(positions[0][2], Piece.BLACK));
		positions[0][5].setPiece(new Bishop(positions[0][5], Piece.BLACK));
		positions[7][2].setPiece(new Bishop(positions[7][2], Piece.WHITE));
		positions[7][5].setPiece(new Bishop(positions[7][5], Piece.WHITE));
		positions[0][0].setPiece(new Rook(positions[0][0], Piece.BLACK));
		positions[0][7].setPiece(new Rook(positions[0][7], Piece.BLACK));
		positions[7][0].setPiece(new Rook(positions[7][0], Piece.WHITE));
		positions[7][7].setPiece(new Rook(positions[7][7], Piece.WHITE));
		positions[0][3].setPiece(new Queen(positions[0][3], Piece.BLACK));
		positions[7][4].setPiece(new Queen(positions[7][4], Piece.WHITE));
		positions[0][4].setPiece(new King(positions[0][4], Piece.BLACK));
		positions[7][3].setPiece(new King(positions[7][3], Piece.WHITE));
		for (int i = 0; i <8; i++) {
			positions[1][i].setPiece(new Pawn(new Position(1, i), Piece.BLACK));
			positions[6][i].setPiece(new Pawn(new Position(6, i), Piece.WHITE));
		}
	}
	
	/**
	 * return true if piece can eat other piece from the target position
	 * @param piece that if going to eat
	 * @param pos target position
	 * @return true if can eat. False if can't eat.
	 */
	public Boolean canEat(Piece piece, Position pos) {
		if (positions[pos.getX()][pos.getY()].getPiece() != null && positions[pos.getX()][pos.getY()].getPiece().getColour() != piece.getColour()) {
			return true;
		} else return false;
	}
	
	/**
	 * goes trough all the pieces on the board and asks for all the legal moves
	 * @return ArrayList of legal moves on the board
	 */
	public ArrayList<Move> getLegalMoves(){
		ArrayList<Move> moves = new ArrayList<Move>();
		for (Piece piece : pieces) {
			moves.addAll(piece.getMoves(this));
		}
		return moves;
	}

	/**
	 * does the changes to move piece in board and saves the move in the move stack
	 * @param move move object that is to be executed and saved in to the moves stack.
	 */
	public void move(Move move) {
		for (Piece piece : pieces) {
			if(move.getP() == piece) {
				piece.setPos(move.getNewPos());
				piece.getPos().setPiece(piece);
				moves.add(move);
			}
		}
	}

	/**
	 * takes the last move from the move stack and moves the piece back where it came from
	 * used in the MinMax algorithm for the AI
	 */
	public void undo() {
		Piece p = moves.peek().getP();
		for (Piece piece : pieces) {
			if (p == piece) {
				piece.setPos(moves.pop().getOldPos());
			}
		}
	}
	
	/**
	 * returns true if the given position contains piece
	 * returns false if the position does not contain a piece
	 * @param positon where 
	 * @return true / false
	 */
	public Boolean containsPiece(Position pos) {
		for (Piece piece : pieces) {
			if (piece.getPos() == pos) {
				return true;
			}
		} 
		return false;
	}
	
	/**
	 * returns true if the target position is empty or the piece can eat the piece that is on the way
	 * @param piece that is going to move or eat
	 * @param newPos position where to move or eat
	 * @return boolean value if the piece can move true if can false if can't.
	 */
	public boolean canEatOrMove(Piece piece, Position newPos) {
		if (!containsPiece(newPos)) {
			return true;
		} else if (positions[newPos.getX()][newPos.getY()].getPiece().getColour() != piece.getColour()) {
			return true;
		} else return false;
	}
	/**
	 * getPieces
	 * @return ArrayList<piece> that contain all the pieces on the board
	 */
	public ArrayList<Piece> getPieces(){
		return pieces;
	}
	/**
	 * getPositions
	 * @return return all position on the board in a 2D array
	 */
	public Position[][] getPositions(){
		return positions;
	}
}
