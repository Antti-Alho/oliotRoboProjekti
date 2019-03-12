package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import piece.King;
import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Camera;
import shakkiBotti9000PC.ChessAI;
import shakkiBotti9000PC.Move;

public class BoardController {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Board board = new Board();
		King white = (King) board.getPositions()[7][4].getPiece();
		King black = (King) board.getPositions()[0][4].getPiece();
		Scanner s = new Scanner(System.in);
		ChessAI ai = new ChessAI(board);
		int depth = 3;
		Socket socket = null;
		boolean vuoro = false;
		boolean peli = true;
		Camera cam = new Camera(board);	
		String answer=null;
/*
				while (board.getPieces().contains(white) && board.getPieces().contains(black)) {
					vuoro = in.readBoolean();
					if (peli==true) {
						System.out.println("-----------------------");
						System.out.println("A B C D E F G H");
						for (int i = 0; i < 8; i++) {
							
							for (int j = 0; j < 8; j++) {
								System.out.print(" "+board.getPositions()[i][j].getPieceString());
							}
							System.out.println(" "+(i+1));
						}
						System.out.println("");
					
						cam.takePicture();
						cam.getEnemymove();
						
//						board.move(new Move(board.pieceAt(x, y),x2,y2,board.pieceAt(x2, y2)));
						Move m = ai.calculateBestMove(depth);
						board.move(m);
						//fromX, toX, fromY, toY, target
						out.writeInt(m.getP().getX());
						out.flush();
						out.writeInt(m.getP().getY());
						out.flush();
						out.writeInt(m.getNewX());
						out.flush();
						out.writeInt(m.getNewY());
						out.flush();
						if (m.getTarget() == null) {
							out.writeInt(-1);
							out.flush();
						} else {
							out.writeInt(1);
							out.flush();
						}
					}
					vuoro = false;
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		while (board.getPieces().contains(white) && board.getPieces().contains(black)) {

			while (answer != "x") {
				
				
				cam.takePicture();
				cam.getEnemymove();
				System.out.println("lasketaan");
				Move m = ai.calculateBestMove(depth);
				board.move(m);
				System.out.println("Laskettu");
				System.out.println("-----------------------");
				System.out.println(" 0 1 2 3 4 5 6 7");
				for (int i = 0; i < 8; i++) {
					
					for (int j = 0; j < 8; j++) {
						System.out.print(" "+board.getPositions()[i][j].getPieceString());
					}
					System.out.println(" "+i);
				}
				System.out.println("********************* \n\nAgain");
				answer = sc.next();
			}
		}
	}
}