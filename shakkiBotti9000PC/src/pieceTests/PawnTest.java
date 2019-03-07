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
        this.pawn = (Pawn) board.getPositions()[1][4].getPiece();
        this.pawnWhite = (Pawn) board.getPositions()[6][4].getPiece();
    }
	
    @Test
    public void testGetMovesFirstMoveBlack() {
        ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(pawn, board.getPositions()[2][4]));
        movesExpected.add(new Move(pawn, board.getPositions()[3][4]));
        
        ArrayList<Move> movesReal = pawn.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected, movesReal, "List of Black Pawn's start moves");
    }
    
    @Test
    public void testGetMovesNotFirstMoveBlack() {
    	board.move(new Move(pawn, board.getPositions()[2][4]));
    	positions = board.getPositions(); 
    	
    	ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(pawn, board.getPositions()[3][4]));
        
        ArrayList<Move> movesReal = pawn.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected, movesReal, "List of Black Pawn's moves when pawn has already moved once");
    }
    
    @Test
    public void testGetMovesEatBlack() {
    	
    	positions = board.getPositions(); 
    	board.move(new Move(pawn, board.getPositions()[5][4]));
    	
    	ArrayList<Move> movesExpected = new ArrayList<Move>();
    	movesExpected.add(new Move(pawn, new Position(2, 4)));
    	movesExpected.add(new Move(pawn, new Position(3, 4)));
    	
    	ArrayList<Move> movesReal = pawn.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
    	
        assertEquals(movesExpected, movesReal, "list of black pawn's eating movements");
    }
    
    @Test
    public void testGetMovesFirstMoveWhite() {
        ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(pawnWhite, board.getPositions()[5][4]));
        movesExpected.add(new Move(pawnWhite, board.getPositions()[4][4]));
        
        ArrayList<Move> movesReal = pawnWhite.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected, movesReal, "List of white pawn's start moves");
    }
    
    @Test
    public void testGetMovesNotFirstMoveWhite() {
    	board.move(new Move(pawnWhite, board.getPositions()[5][4]));
    	positions = board.getPositions(); 
    	
    	ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(pawnWhite, board.getPositions()[4][4]));
        
        ArrayList<Move> movesReal = pawnWhite.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected, movesReal, "List of white pawn's moves when pawn has already moved once");
    }
    
    @Test
    public void testGetMovesEatWhite() {
    	
    	
    	positions = board.getPositions(); 
    	board.move(new Move(pawnWhite, board.getPositions()[2][1]));
    	
    	ArrayList<Move> movesExpected = new ArrayList<Move>();
    	movesExpected.add(new Move(pawnWhite, new Position(1, 0)));
    	movesExpected.add(new Move(pawnWhite, new Position(1, 2)));
    	
    	ArrayList<Move> movesReal = pawnWhite.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
    	
        assertEquals(movesExpected, movesReal, "list of white pawn's eating movements");
    }

}
