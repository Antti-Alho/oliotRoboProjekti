import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.pathfinding.DijkstraPathFinder;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.PathFinder;
import lejos.robotics.pathfinding.ShortestPathFinder;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.mapping.LineMap;

public class Pilot {
	
	public static void main(String[] args) {
	Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 32.4).offset(-84.85);
	Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 32.4).offset(84.85);
	
	
	Chassis chassis = new WheeledChassis(new Wheel[] {wheel1,  wheel2}, WheeledChassis.TYPE_DIFFERENTIAL);
	MovePilot pilot = new MovePilot(chassis);
//	pilot.travel(1000);
//	pilot.rotate(360*10);


	
	
	Rectangle suorakulmio = new Rectangle(0, 0, 1000, 900);
	Line[] janat = new Line[13];
	  
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
	Pose pose = new Pose(200, 100, 90);
	Navigator navi = new Navigator(pilot,chassis.getPoseProvider()) ;
	ShortestPathFinder polunEtsijä = new ShortestPathFinder(kartta);
	polunEtsijä.lengthenLines(150);
	chassis.getPoseProvider().setPose(pose);
	
	Path path;
	try {
		path = polunEtsijä.findRoute(chassis.getPoseProvider().getPose(), new Waypoint(180, 800));
		navi.setPath(path);
		navi.followPath();
		
		navi.waitForStop();
	} catch (DestinationUnreachableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		path = polunEtsijä.findRoute(chassis.getPoseProvider().getPose(), new Waypoint(850, 750));
		navi.setPath(path);
		navi.followPath();
		navi.waitForStop();
	} catch (DestinationUnreachableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		path = polunEtsijä.findRoute(chassis.getPoseProvider().getPose(), new Waypoint(550, 150));
		navi.setPath(path);
		navi.followPath();
		navi.waitForStop();
	} catch (DestinationUnreachableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		path = polunEtsijä.findRoute(chassis.getPoseProvider().getPose(), new Waypoint(200, 100));
		navi.setPath(path);
		navi.followPath();
		navi.waitForStop();
	} catch (DestinationUnreachableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}


}