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

	public static void main(String[] args){
		Board board = new Board();
		King white = (King) board.getPositions()[7][4].getPiece();
		King black = (King) board.getPositions()[0][4].getPiece();
		Scanner s = new Scanner(System.in);
		ChessAI ai = new ChessAI(board);
		int depth = 3;
		Socket socket = null;
		boolean vuoro = false;
		Camera cam = new Camera(board);

		try {
			socket = new Socket("10.0.1.1", 1111);
			DataInputStream in = new DataInputStream(socket.getInputStream());
		    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		    while (board.getPieces().contains(white) && board.getPieces().contains(black)) {
				vuoro = in.readBoolean();
				System.out.println(board.getPieces().contains(white)+ "    " +board.getPieces().contains(black));
				if (vuoro==true) {
					cam.takePicture();
					cam.enemyMove();
					System.out.println("-----------------------");
					System.out.println(" A B C D E F G H");
					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++) {
							System.out.print(" "+board.getPositions()[i][j].getPieceString());
						}
						System.out.println(" "+(i+1));
					}
					System.out.println("");
					if(board.getPieces().contains(black)==false) {
						break;
					}
					if (board.getPieces().size()<=13) {
						depth = 5;
					}
					Move m = ai.calculateBestMove(depth);
					board.move(m);
					if (board.getPieces().contains(white)==false) {
						out.writeInt(-2);
						out.flush();
					} else {
						out.writeInt(m.getOldX());
						out.flush();
					}
					out.writeInt(m.getOldY());
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
					System.out.println("-----------------------");
					System.out.println(" A B C D E F G H");
					for (int i = 0; i < 8; i++) {
						
						for (int j = 0; j < 8; j++) {
							System.out.print(" "+board.getPositions()[i][j].getPieceString());
						}
						System.out.println(" "+(i+1));
					}
					System.out.println("");
				}
				vuoro = false;
			}
		    for (int i = 0; i < 6; i++) {
		    	out.writeInt(-1);
				out.flush();
			}
		    System.out.println("Peli päättyi.");
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
