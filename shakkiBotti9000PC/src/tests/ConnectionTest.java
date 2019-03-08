package tests;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionTest {
	
	public static void main(String[] args) {
		
		Socket s = null;
		boolean testi = false;
		int peli = 0;
		
		try {
			s = new Socket("10.0.1.1", 1111);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
				while (peli==0) {
				testi = in.readBoolean();
				System.out.println(testi);
				if (testi == true) {
						out.writeInt(1);
						out.flush();
						out.writeInt(5);
						out.flush();
						out.writeInt(2);
						out.flush();
						out.writeInt(5);
						out.flush();
						out.writeInt(0);
						out.flush();
						out.writeInt(0);
						out.flush();
				}
				testi = false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
