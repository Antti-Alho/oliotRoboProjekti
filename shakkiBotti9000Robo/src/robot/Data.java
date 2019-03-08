package robot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Data {
	ServerSocket serv;
	Socket s;
	DataOutputStream out;
	DataInputStream in;
	
	private ArrayList<Integer> crdnts = new ArrayList<>();
	
	public ArrayList<Integer> getCrdnts() {
		return crdnts;
	}
	
	public void clearCrdnts() {
		this.crdnts.clear();
	}
	
	public Data() {
		try {
			this.serv = new ServerSocket(1111);
			this.s = serv.accept();
			this.out = new DataOutputStream(s.getOutputStream());
			this.in = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void playerDone() {
		try {
			out.writeBoolean(true);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void receiveMoves() {
		try {
			for (int i = 0; i < 5; i++) {
				this.crdnts.add(in.readInt());
				System.out.println(this.crdnts.get(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean haveMoves() {
		if(crdnts.size()>1) {
			return true;
		}
		return false;
	}
}
