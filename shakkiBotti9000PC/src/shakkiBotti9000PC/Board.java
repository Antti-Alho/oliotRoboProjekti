package shakkiBotti9000PC;

import java.util.ArrayList;
import java.util.Stack;
import piece.*;

/**
 * Controls and updates the the positions of pieces
 * and contains utility methods for the minMax algorithm
 * @author Antti Alho
 */
public class Board {
	
	private Stack<Move> moves;
	private ArrayList<Piece> pieces;
	
	/**
	 * The board constructor generates the starting positions for each peace on the board.
	 */
	public Board() {
		pieces = new ArrayList<>();
		moves = new Stack<Move>();
		pieces.add(new Knight(Piece.BLACK, 0,1));
		pieces.add(new Knight(Piece.BLACK, 0,6));
		pieces.add(new Knight(Piece.WHITE,7,1));
		pieces.add(new Knight(Piece.WHITE,7,6));
		pieces.add(new Bishop(Piece.BLACK,0,2));
		pieces.add(new Bishop(Piece.BLACK,0,5));
		pieces.add(new Bishop(Piece.WHITE,7,2));
		pieces.add(new Bishop(Piece.WHITE,7,5));
		pieces.add(new Rook(Piece.BLACK,0,0));
		pieces.add(new Rook(Piece.BLACK,0,7));
		pieces.add(new Rook(Piece.WHITE,7,0));
		pieces.add(new Rook(Piece.WHITE,7,7));
		pieces.add(new Queen(Piece.BLACK,0,3));
		pieces.add(new Queen(Piece.WHITE,7,3));
		pieces.add(new King(Piece.BLACK,0,4));
		pieces.add(new King(Piece.WHITE,7,4));
		for (int i = 0; i <8; i++) {
			pieces.add(new Pawn(Piece.BLACK,1,i));
			pieces.add(new Pawn(Piece.WHITE,6,i));
		}
	}
	
	/**
	 * return true if piece can eat another piece from the target position
	 * @param piece that is going to eat
	 * @param x x coordinate where piece is going to eat
	 * @param y y coordinate where piece is going to eat
	 * @return true if eating is possible. False if can't eat.
	 */
	public Boolean canEat(Piece piece, int x,int y) {
		if (pieceAt(x, y) != null) {
			Piece enemy = pieceAt(x, y);
			if (piece.getColour() != enemy.getColour()) {
				return true;
			} 
		}
		return false;
	}
	
	/**
	 * goes trough all certain coloured pieces on the board and asks for all the legal moves
	 * @param colour boolean representation of the colour use Piece.WHITE or Piece.BLACK for clarity
	 * @return ArrayList of legal moves on the board player can take
	 */
	public ArrayList<Move> getLegalMoves(Boolean colour){
		ArrayList<Move> moves = new ArrayList<Move>();
		for (Piece piece : pieces) {
			if(piece.getColour() == colour) {
				moves.addAll(piece.getMoves(this));
			}
		}
		return moves;
	}

	/**
	 * executes the changes changes to the board that are saved in the move object.
	 * @param move object that is to be executed and saved in to the move stack.
	 */
	public void move(Move move) {
		Piece piece = pieceAt(move.getOldX(), move.getOldY());
		if (containsPiece(move.getNewX(), move.getNewY())) {
			pieces.remove(pieceAt(move.getNewX(), move.getNewY()));
		}
		piece.setX(move.getNewX());
		piece.setY(move.getNewY());
		moves.add(move);
	}
	

	/**
	 * takes the last move from the move stack and moves the piece back where it came from
	 */
	public void undo() {
		Move m = moves.pop();
		Piece piece = pieceAt(m.getNewX(), m.getNewY());
		piece.setX(m.getOldX());
		piece.setY(m.getOldY());
		if (m.getTarget() != null) {
			pieces.add(m.getTarget());
		}
	}
	
	/**
	 * returns true if the given position contains piece
	 * returns false if the position does not contain a piece
	 * @param x x coordinate where to look for piece
	 * @param y y coordinate where to look for piece 
	 * @return true / false
	 */
	public Boolean containsPiece(int x, int y) {
		for (Piece piece : pieces) {
			if (piece.getX() == x && piece.getY() == y) {
				return true;
			}
		} 
		return false;
	}
	
	/**
	 * Return true if target position contains enemy piece
	 * @param piece which piece is going to move
	 * @param x x coordinate of target position
	 * @param y y coordinate of target position
	 * @return returns true if piece at x y has different colour
	 */
	public Boolean containsEnemy(Piece piece, int x, int y) {
		if (pieceAt(x, y) != null) {
			Piece enemy = pieceAt(x, y);
			if (piece.getColour() != enemy.getColour()) {
				return true;
			} 
		}
		return false;
	}
	/**
	 * removes the piece from the piece list in the given position
	 * @param x x coordinate where to remove
	 * @param y y coordinate where to remove pieces
	 */
	public void removePiece(int x, int y) {
		Piece p = null;
		for (Piece piece: pieces) {
			if (piece.getX() == x && piece.getY() == y) {
				p = piece;
			}
		}
		if (p != null) pieces.remove(p);
	}
	
	/**
	 * return list of pieces that are currently on the board
	 * @return ArrayList that contain all the pieces on the board
	 */
	public ArrayList<Piece> getPieces(){
		return pieces;
	}
	
	/**
	 * creates an array of position so that the board can be more easily printed
	 * and handled in 
	 * @return return all position on the board in a 2D array
	 */
	public Position[][] getPositions(){
		Position[][] pos = new Position[8][8];
		for (int i = 0; i < pos.length; i++) {
			for (int j = 0; j < pos.length; j++) {
				pos[i][j] = new Position(i, j);
			}
		}
		for (Piece piece : pieces) {
			pos[piece.getX()][piece.getY()].setPiece(piece);
		}
		return pos;
	}
	
	/**
	 * returns piece at coordinate x, y;
	 * @param x coordinate
	 * @param y coordinate
	 * @return piece at coordinate
	 */
	public Piece pieceAt(int x, int y) {
		for (Piece piece : pieces) {
			if (piece.getX() == x && piece.getY() == y) return piece; 
		}
		return null;
	}
}
