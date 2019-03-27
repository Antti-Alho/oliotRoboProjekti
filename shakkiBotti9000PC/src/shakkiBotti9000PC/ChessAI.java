package shakkiBotti9000PC;

import java.util.ArrayList;
import piece.Piece;

/**
 * MinMax algorithm. 
 * Calculates the "best" move for the robot.
 */
public class ChessAI {
	
	Board board;
	
	public ChessAI(Board board) {
		this.board = board;
	}
	/**
	 * The root of the minMax algorithm that returns the "best" move the AI can take
	 * @param depth how deep do you wan't the AI to check good moves 3,5 are good values
	 * 7 probably takes too long, use odd values so that the "AI" always sees the "cost" of the move 
	 * @return returns the move witch opens the possibility for the best evaluated board position
	 */
	public Move calculateBestMove(int depth) {
		ArrayList<Move> moves = this.board.getLegalMoves(true);
		int bestMoveVal = -99999;
		int moveValue;
		Move bestMove = moves.get(0);
		for (Move move : moves) {
			this.board.move(move);
			moveValue = minMax(depth, this.board, -100000, 100000, false);
			this.board.undo();
			if(moveValue >= bestMoveVal) {
				bestMoveVal = moveValue;
				bestMove = move;
			}
		}
		System.out.println(bestMove);
		System.out.println(bestMoveVal);
		return bestMove;
	}
	
	/**
	 * recursive part of the minMax algorithm
	 * @param depth how deep to the search tree we still have to go
	 * @param board current board situation
	 * @param alpha current largest board value found
	 * @param beta current lowest board value found
	 * @param player true/false which player we are currently evaluating true for AI false for player
	 * @return integer value of the of the best board evaluation in top of search tree 
	 */
	private int minMax(int depth, Board board, int alpha, int beta, Boolean player) {
		if (depth == 0) {
			return eval(board.getPieces());
		}
		ArrayList<Move> bestMoves = board.getLegalMoves(player);
		if (player) {
			int bestMoveVal = -99999;
			for (Move move : bestMoves) {
				board.move(move);
				bestMoveVal = Math.max(bestMoveVal, minMax(depth - 1,board, alpha, beta, !player));
				board.undo();
	            alpha = Math.max(alpha, bestMoveVal);
	            if (beta <= alpha) {
	                return bestMoveVal;
	            }
			}
			return bestMoveVal;
		} else {
			int bestMoveVal = 99999;
			for (Move move : bestMoves) {
				board.move(move);
				bestMoveVal = Math.min(bestMoveVal, minMax(depth - 1,board, alpha, beta, !player));
				board.undo();
	            beta = Math.min(beta, bestMoveVal);
	            if (beta <= alpha) {
	                return bestMoveVal;
	            }
			}
			return bestMoveVal;
		}
	}
	/**
	 * Final evaluation of board value in top of minMax algorithm. 
	 * Enemy pieces add negative values the AIs pieces add positive values.
	 * @param pieces ArrayList of pieces currently on the board
	 * @return returns the total value of the pieces on the board
	 */
	private int eval(ArrayList<Piece> pieces) {
		int totalEvaluation = 0;
		for (Piece piece : pieces) {
			totalEvaluation = totalEvaluation + piece.getValue();
		}
	    return totalEvaluation;
	}

}
