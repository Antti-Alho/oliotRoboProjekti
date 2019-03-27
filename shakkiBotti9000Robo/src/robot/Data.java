package robot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
/**
 * Initializes the connection between the EV3 and a PC for communication between the devices and transmiting data.
 * @author ville
 *
 */
public class Data {
	/**
	 * Implements which server socket is used for the network connection.
	 */
	ServerSocket serv;
	/**
	 * Implements which client socket the EV3 will use for the communication between the devices.
	 */
	Socket s;
	/**
	 * Writes data into the output stream, which is send to an another device.
	 */
	DataOutputStream out;
	/**
	 * Reads data from the input stream, coming from other devices in the connection.
	 */
	DataInputStream in;
	/**
	 * The received integers are saved into this arraylist.
	 */
	private ArrayList<Integer> crdnts = new ArrayList<>();
	/**
	 * Used the fetch the arraylist of integers the EV3 have received from the input stream.
	 * @return An arraylist of five integers.
	 */
	public ArrayList<Integer> getCrdnts() {
		return crdnts;
	}
	/**
	 * Clears the arraylist of received integers so new ones can be saved on it to replace the old ones.
	 */
	public void clearCrdnts() {
		this.crdnts.clear();
	}
	/**
	 * Initializes the connection network and sets up the output and input streams.
	 */
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
	/**
	 * Used to write true boolean value into the output stream.
	 */
	public void playerDone() {
		try {
			out.writeBoolean(true);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Reads five integers from the input stream and saves them into the crdnts-arraylist.
	 */
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
	/**
	 * Checks if the crdnts-arraylist have any integers in it.
	 * @return Returns true if the arraylist have integers in it. Returns false if the arraylist doesn't have the required integers.
	 */
	public boolean haveMoves() {
		if(crdnts.size()>1) {
			return true;
		}
		return false;
	}
}
