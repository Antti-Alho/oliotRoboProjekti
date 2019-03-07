package pieceTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import piece.Bishop;
import piece.Pawn;
import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.MoveComparator;

class BishopTest {

	private Board board;
	private Bishop piece;

	@BeforeEach
	public void Before() {
		 this.board = new Board();
	     this.piece = (Bishop) board.getPositions()[0][5].getPiece();
	}

    @Test
    public void testGetMovesBishopCantMove() {
        ArrayList<Move> moves = new ArrayList<Move>();
        assertEquals(moves, piece.getMoves(board), "List of Bishop start moves");
    }

    @Test
    public void testGetMovesBishopOneDirection() {
    	board.getPositions()[1][4].setPiece(null);
        ArrayList<Move> moves = new ArrayList<Move>();        
        moves.add(new Move(piece, board.getPositions()[1][4]));
        moves.add(new Move(piece, board.getPositions()[2][3]));
        moves.add(new Move(piece, board.getPositions()[3][2]));
        moves.add(new Move(piece, board.getPositions()[4][1]));
        moves.add(new Move(piece, board.getPositions()[5][0]));
        Collections.sort(moves, new MoveComparator());
        
        ArrayList<Move> movesReal = piece.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        assertEquals(moves, movesReal, "List Bishop start moves without Pawn");
    }
    
    @Test
    public void testGetMovesBishopAllDirections() {
    	board.move(new Move(piece, board.getPositions()[3][4]));
        ArrayList<Move> moves = new ArrayList<Move>();
        
        moves.add(new Move(piece, board.getPositions()[2][3]));
        moves.add(new Move(piece, board.getPositions()[2][5]));
        
        moves.add(new Move(piece, board.getPositions()[3][4]));
        moves.add(new Move(piece, board.getPositions()[2][5]));
        moves.add(new Move(piece, board.getPositions()[1][6]));
        
        moves.add(new Move(piece, board.getPositions()[5][4]));
        moves.add(new Move(piece, board.getPositions()[6][5]));
        moves.add(new Move(piece, board.getPositions()[7][6]));
   
        Collections.sort(moves, new MoveComparator());
        
        ArrayList<Move> movesReal = piece.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        assertEquals(moves, movesReal, "List Bishop moves All directions");
    }

}
