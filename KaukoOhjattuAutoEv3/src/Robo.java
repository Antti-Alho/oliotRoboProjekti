import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.PathFinder;
import lejos.robotics.pathfinding.ShortestPathFinder;
import lejos.utility.Delay;

public class Robo {
	public static void main(String[] args) {
		ServerSocket serv;
		Pose pose = new Pose();
		LineMap kartta = new LineMap();
		ArrayList<Waypoint> waypoints = new ArrayList<>();
		Waypoint wp = new Waypoint(0, 0);
		
		try {
			serv = new ServerSocket(1111);
			Socket s = serv.accept();
			DataInputStream in = new DataInputStream(s.getInputStream());
			int a = in.readInt();
			Delay.msDelay(2000);
			pose.loadObject(in);
			kartta.loadObject(in);
			for (int i = 0; i < a; i++) {
				wp.loadObject(in);
				waypoints.add(wp);
			}
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// DataOutputStream out = new DataOutputStream(s.getOutputStream());
		
		
		Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 32.4).offset(-84.85);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 32.4).offset(84.85);
		
		Chassis chassis = new WheeledChassis(new Wheel[] {wheel1,  wheel2}, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot pilot = new MovePilot(chassis);		
			
		
		Navigator navi = new Navigator(pilot,chassis.getPoseProvider()) ;
		ShortestPathFinder polunEtsijä = new ShortestPathFinder(kartta);
		polunEtsijä.lengthenLines(150);
		chassis.getPoseProvider().setPose(pose);
			
		for(Waypoint waypoint : waypoints) {
			aja(waypoint,navi, polunEtsijä, chassis);
		}

	}


	public static void aja(Waypoint wp, Navigator navi, PathFinder pf, Chassis c) {
		Path path;
		try {
			path = pf.findRoute(c.getPoseProvider().getPose(),wp);
			System.out.println("1");
			navi.setPath(path);
			System.out.println("2");
			navi.followPath();
			System.out.println("3");
			navi.waitForStop();
		} catch (DestinationUnreachableException e) {
			e.printStackTrace();
		}
	}
}
