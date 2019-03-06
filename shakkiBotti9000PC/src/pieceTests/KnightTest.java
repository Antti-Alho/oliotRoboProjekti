package pieceTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import piece.Knight;
import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;

class KnightTest {

	private Board board;
	private Knight piece;
	
	@Before
	public void Before() {
		 this.board = new Board();
	     this.piece = (Knight) board.getPositions()[0][6].getPiece();
	}

    @Test
    public void testGetMovesKnightFirstMove() {
        ArrayList<Move> moves = new ArrayList<Move>();
        moves.add(new Move(piece, board.getPositions()[2][7]));
        moves.add(new Move(piece, board.getPositions()[2][5]));
        assertEquals(moves, piece.getMoves(board), "List Knight start moves");
    }

    @Test
    public void testGetMovesKnightAllDirections() {
    	board.move(new Move(piece, board.getPositions()[4][3]));
        ArrayList<Move> moves = new ArrayList<Move>();
        moves.add(new Move(piece, board.getPositions()[6][2]));
        moves.add(new Move(piece, board.getPositions()[5][1]));
        
        moves.add(new Move(piece, board.getPositions()[3][1]));
        moves.add(new Move(piece, board.getPositions()[2][2]));
        
        moves.add(new Move(piece, board.getPositions()[2][4]));
        moves.add(new Move(piece, board.getPositions()[3][5]));
        
        moves.add(new Move(piece, board.getPositions()[6][4]));
        moves.add(new Move(piece, board.getPositions()[5][5]));
        
        assertEquals(moves, piece.getMoves(board), "List Kinght moves in middle of board");
    }
 

}
