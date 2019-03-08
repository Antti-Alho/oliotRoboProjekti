package tests;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionTest {
	
	public static void main(String[] args) {
		
		Socket s = null;
		boolean vuoro = false;
		boolean peli = true;
		
		try {
			s = new Socket("10.0.1.1", 1111);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
				while (peli==true) {
				vuoro = in.readBoolean();
				System.out.println(peli);
				if (peli == true) {
				}
				vuoro = false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
