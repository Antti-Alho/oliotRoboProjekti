import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;

public class Kone {
	public static void main(String[] args) {
	
		Rectangle suorakulmio = new Rectangle(0, 0, 1000, 900);
		Line[] janat = new Line[13];
		Pose pose = new Pose(200, 100, 90);

		// rajaavan suorakulmion sivut
		janat[0] = new Line(0,0,1000,0);
		janat[1] = new Line(1000,0,1000,900);
		janat[2] = new Line(0,900,1000,900);
		janat[3] = new Line(0,0,0,900);
		// esteet
		janat[4] = new Line(400,0,400,250);
		janat[5] = new Line(700,0,700,300);
		janat[6] = new Line(600,900,600,600);
		janat[7] = new Line(0,600,300,600);
		
		janat[8] = new Line(350,250,399,250);
		janat[9] = new Line(701,250,750,250);
		janat[10]= new Line(550,650,650,650);
		janat[11]= new Line(350,580,350,620);
		janat[12]= new Line(701,300,750,300);
		
		LineMap kartta = new LineMap(janat, suorakulmio);
		
		ArrayList<Waypoint> waypoints = new ArrayList();
		waypoints.add(new Waypoint(180, 800));
		waypoints.add(new Waypoint(850, 750));
		waypoints.add(new Waypoint(550, 150));
		waypoints.add(new Waypoint(200, 100));
			
		Socket s = null;
		DataOutputStream out;
		try {
			s = new Socket("10.0.1.1", 1111);
			out = new DataOutputStream(s.getOutputStream());
			out.writeInt(waypoints.size());
			out.flush();
			pose.dumpObject(out);
			out.flush();
			kartta.dumpObject(out);
			out.flush();
			for (Waypoint waypoint : waypoints) {
				waypoint.dumpObject(out);
			}
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DataInputStream in;
		if (s != null) {
			try {
				in = new DataInputStream(s.getInputStream());
				for (int i = 0; i < waypoints.size(); i++) {
					System.out.println("X: " + in.readFloat());
					System.out.println("Y: " + in.readFloat());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
