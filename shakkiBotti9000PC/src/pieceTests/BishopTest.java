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
        assertEquals(moves.toString(), piece.getMoves(board).toString(), "List of Bishop start moves");
    }

    @Test
    public void testGetMovesBishopOneDirection() {
    	board.removePiece(1, 4);
        ArrayList<Move> moves = new ArrayList<Move>();        
        moves.add(new Move(piece, 1,4,board.pieceAt(1, 4)));
        moves.add(new Move(piece, 2,3,board.pieceAt(2, 3)));
        moves.add(new Move(piece, 3,2,board.pieceAt(3, 2)));
        moves.add(new Move(piece, 4,1,board.pieceAt(4, 1)));
        moves.add(new Move(piece, 5,0,board.pieceAt(5, 0)));
        Collections.sort(moves, new MoveComparator());
        
        ArrayList<Move> movesReal = piece.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        assertEquals(moves.toString(), movesReal.toString(), "List Bishop start moves without Pawn");
    }
    
    @Test
    public void testGetMovesBishopAllDirections() {
    	board.move(new Move(piece, 3,4, board.pieceAt(3, 4)));
        ArrayList<Move> moves = new ArrayList<Move>();
        
        moves.add(new Move(piece, 2,3,board.pieceAt(2, 3)));
        moves.add(new Move(piece, 2,5,board.pieceAt(2, 5)));
        
        moves.add(new Move(piece, 4,3,board.pieceAt(4, 3)));
        moves.add(new Move(piece, 5,2,board.pieceAt(5, 2)));
        moves.add(new Move(piece, 6,1,board.pieceAt(6, 1)));
        
        moves.add(new Move(piece, 4,5,board.pieceAt(4, 5)));
        moves.add(new Move(piece, 5,6,board.pieceAt(5, 6)));
        moves.add(new Move(piece, 6,7,board.pieceAt(6, 7)));
   
        Collections.sort(moves, new MoveComparator());
        
        ArrayList<Move> movesReal = piece.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        assertEquals(moves.toString(), movesReal.toString(), "List Bishop moves All directions");
    }

}
