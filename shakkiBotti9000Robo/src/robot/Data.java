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
	
	private ArrayList<Integer> crdnts = new ArrayList<>();
	
	public ArrayList<Integer> getCrdnts() {
		return crdnts;
	}
	
	public void clearCrdnts() {
		this.crdnts.clear();
	}
	
	public Data() {
		try {
			serv = new ServerSocket(1111);
			s = serv.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playerDone() {
		DataOutputStream out;
		if (s != null) {
			try {
				out = new DataOutputStream(s.getOutputStream());
				out.writeBoolean(true);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void receiveMoves() {
		DataInputStream in;
		try {
			in = new DataInputStream(s.getInputStream());
			for (int i = 0; i < 6; i++) {
				this.crdnts.add(in.readInt());
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
