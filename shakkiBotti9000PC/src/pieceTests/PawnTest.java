package pieceTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import piece.Pawn;
import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.MoveComparator;
import shakkiBotti9000PC.Position;
import org.junit.jupiter.api.BeforeEach;


class PawnTest {

	private Pawn pawn;
	private Pawn pawnWhite;
	private Board board;
	private Position[][] positions;
	
    @BeforeEach
    public void initTestSystem() {
        System.out.println("--------------------");
        this.board = new Board();
        this.pawn = (Pawn) board.pieceAt(1,4);
        this.pawnWhite = (Pawn) board.pieceAt(6,4);
    }
	
    @Test
    public void testGetMovesFirstMoveBlack() {
        ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(pawn, 2,4,board.pieceAt(2, 4)));
        movesExpected.add(new Move(pawn, 3,4,board.pieceAt(3, 4)));
        
        ArrayList<Move> movesReal = pawn.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected.toString(), movesReal.toString(), "List of Black Pawn's start moves");
    }
    
    @Test
    public void testGetMovesNotFirstMoveBlack() {
    	board.move(new Move(pawn, 2,4,board.pieceAt(2, 4)));
    	positions = board.getPositions(); 
    	
    	ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(pawn, 3,4,board.pieceAt(3, 4)));
        
        ArrayList<Move> movesReal = pawn.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected.toString(), movesReal.toString(), "List of Black Pawn's moves when pawn has already moved once");
    }
    
    @Test
    public void testGetMovesEatBlack() {
    	
    	positions = board.getPositions(); 
    	board.move(new Move(pawn, 5,4,board.pieceAt(5, 4)));
    	
    	ArrayList<Move> movesExpected = new ArrayList<Move>();
    	movesExpected.add(new Move(pawn, 6,3,board.pieceAt(6, 3)));
    	movesExpected.add(new Move(pawn, 6,5,board.pieceAt(6, 5)));
    	
    	ArrayList<Move> movesReal = pawn.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
    	
        assertEquals(movesExpected.toString(), movesReal.toString(), "list of black pawn's eating movements");
    }
    
    @Test
    public void testGetMovesFirstMoveWhite() {
        ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(pawnWhite, 5,4,board.pieceAt(5, 4)));
        movesExpected.add(new Move(pawnWhite, 4,4,board.pieceAt(4, 4)));
        Collections.sort(movesExpected, new MoveComparator());
        ArrayList<Move> movesReal = pawnWhite.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected.toString(), movesReal.toString(), "List of white pawn's start moves");
    }
    
    @Test
    public void testGetMovesNotFirstMoveWhite() {
    	board.move(new Move(pawnWhite, 5,4,board.pieceAt(5, 4)));
    	positions = board.getPositions(); 
    	
    	ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(pawnWhite, 4,4,board.pieceAt(4, 4)));
        
        ArrayList<Move> movesReal = pawnWhite.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected.toString(), movesReal.toString(), "List of white pawn's moves when pawn has already moved once");
    }
    
    @Test
    public void testGetMovesEatWhite() {
    	
    	
    	positions = board.getPositions(); 
    	board.move(new Move(pawnWhite,2,1,board.pieceAt(2, 1)));
    	
    	ArrayList<Move> movesExpected = new ArrayList<Move>();
    	movesExpected.add(new Move(pawnWhite, 1, 0,board.pieceAt(1, 0)));
    	movesExpected.add(new Move(pawnWhite, 1, 2,board.pieceAt(1, 2)));
    	
    	ArrayList<Move> movesReal = pawnWhite.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
    	
        assertEquals(movesExpected.toString(), movesReal.toString(), "list of white pawn's eating movements");
    }

}
