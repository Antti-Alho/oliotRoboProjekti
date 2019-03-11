package pieceTests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import piece.Knight;
import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.MoveComparator;



class KnightTest {

	private Board board;
	private Knight piece;
	
	@BeforeEach
	public void Before() {
		 this.board = new Board();
	     this.piece = (Knight) board.pieceAt(0, 6);
	}

    @Test
    public void testGetMovesKnightFirstMove() {
        ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(piece, 2,7,board.pieceAt(2, 7)));
        movesExpected.add(new Move(piece, 2,5,board.pieceAt(2, 5)));
        
        Collections.sort(movesExpected, new MoveComparator());
        ArrayList<Move> movesReal = piece.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected.toString(), movesReal.toString(), "List Knight start moves");
    }

    @Test
    public void testGetMovesKnightAllDirections() {
    	board.move(new Move(piece,4,3,board.pieceAt(4, 3)));
        ArrayList<Move> movesExpected = new ArrayList<Move>();
        
        movesExpected.add(new Move(piece,6,2,board.pieceAt(6, 2))); 	// knight eats pawn
        movesExpected.add(new Move(piece,5,1,board.pieceAt(5, 1)));
        
        movesExpected.add(new Move(piece, 3,1,board.pieceAt(3, 1)));
        movesExpected.add(new Move(piece, 2,2,board.pieceAt(2, 2)));
        
        movesExpected.add(new Move(piece, 2,4,board.pieceAt(2, 4)));
        movesExpected.add(new Move(piece, 3,5,board.pieceAt(3, 5)));
        
        movesExpected.add(new Move(piece, 6,4,board.pieceAt(6, 4)));		// knight eats pawn
        movesExpected.add(new Move(piece, 5,5,board.pieceAt(5, 5)));
        
        Collections.sort(movesExpected, new MoveComparator());
        ArrayList<Move> movesReal = piece.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected.toString(), movesReal.toString(), "List Knight moves in middle of board");
    }
 

}
