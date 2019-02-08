import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Delayed;

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
		Socket s = null;
		DataInputStream in = null;
		Pose pose = new Pose();
		LineMap kartta = new LineMap();
		ArrayList<Waypoint> waypoints = new ArrayList<>();
		
		try {
			serv = new ServerSocket(1111);
			s = serv.accept();
			in = new DataInputStream(s.getInputStream());
			int a = in.readInt();
			Delay.msDelay(2000);
			pose.loadObject(in);
			kartta.loadObject(in);
			for (int i = 0; i < a; i++) {
				Waypoint wp = new Waypoint(0,0);
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
		
		System.out.println("Starting point: " + pose);
			
		for(Waypoint waypoint : waypoints) {
			System.out.println("Moving to the next waypoint " + waypoint);
			aja(waypoint,navi, polunEtsijä, chassis);
			dataa(s, chassis);
			System.out.println("Arrived to the waypoint. ");
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static void aja(Waypoint wp, Navigator navi, ShortestPathFinder pf, Chassis c) {
		Path path;
		try {
			path = pf.findRoute(c.getPoseProvider().getPose(),wp);
			navi.setPath(path);
			navi.followPath();
			navi.waitForStop();
		} catch (DestinationUnreachableException e) {
			e.printStackTrace();
		}
	}
	public static void dataa(Socket s, Chassis c) {
		DataOutputStream out;
		if (s != null) {
			try {
				out = new DataOutputStream(s.getOutputStream());
				out.writeFloat(c.getPoseProvider().getPose().getX());
				out.writeFloat(c.getPoseProvider().getPose().getY());
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
