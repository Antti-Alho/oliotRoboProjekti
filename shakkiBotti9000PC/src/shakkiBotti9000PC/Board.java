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
		moves = new Stack<Move>();
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
		for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length; j++) {
				if (positions[i][j].getPiece() != null) {
					pieces.add(positions[i][j].getPiece());
				}
			}
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
	 * @param move object that is to be executed and saved in to the moves stack.
	 */
	public void move(Move move) {
		Piece piece = positions[move.getOldPos().getX()][move.getOldPos().getY()].getPiece();
		piece.setPos(move.getNewPos());
		positions[move.getNewPos().getX()][move.getNewPos().getY()].setPiece(piece);
		positions[move.getOldPos().getX()][move.getOldPos().getY()].setPiece(null);
		moves.add(move);
	}
	

	/**
	 * takes the last move from the move stack and moves the piece back where it came from
	 * used in the MinMax algorithm for the AI
	 */
	public void undo() {
		Move m = moves.pop();
		m.getOldPos().setPiece(m.getP());
		m.getNewPos().setPiece(null);
		if (m.getTarget() == null) {
			m.getNewPos().setPiece(null);
		} else m.getNewPos().setPiece(m.getTarget());
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
	 * Return true if target position contains enemy piece
	 * @param pos
	 * @return
	 */
	public Boolean containsEnemy(Piece piece, Position pos) {
		if (piece.getPos() == pos && piece.getColour() != pos.getPiece().getColour()) {
			return true;
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
	 * removes the piece in the given position
	 * @param pos position where piece should be removed
	 */
	public void removePiece(Position pos) {
		positions[pos.getX()][pos.getY()].setPiece(null);
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
