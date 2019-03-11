package shakkiBotti9000PC;

import java.util.ArrayList;
import piece.Piece;

public class ChessAI {
	
	Board board;
	
	public ChessAI(Board board) {
		this.board = board;
	}
	/**
	 * The root of the minMax algorithm that returns the "best" move the AI can take
	 * @param depth how deep do you wan't the AI to check good moves 3-5 are good values
	 * @return returns the move witch opens the possibility of the best result
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
		return bestMove;
	}
	
	/**
	 * recursive part of the minMax never call this directly
	 * @param depth
	 * @param alpha
	 * @param beta
	 * @param player
	 * @return integer value of the of the best board evaluation in top of search tree 
	 */
	private int minMax(int depth, Board board, int alpha, int beta, Boolean player) {
		if (depth == 0) {
			return -eval(board.getPieces());
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
			return eval(board.getPieces());
		}
	}
	/*
	 * final evaluation of board value in top of minMax algorithm.
	 */
	private int eval(ArrayList<Piece> pieces) {
		int totalEvaluation = 0;
		for (Piece piece : pieces) {
			totalEvaluation = totalEvaluation + piece.getValue();
		}
	    return totalEvaluation;
	}

}
