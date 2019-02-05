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
	Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 43.2).offset(-99.2);
	Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 43.2).offset(99.2);
	
	
	Chassis chassis = new WheeledChassis(new Wheel[] {wheel1,  wheel2}, WheeledChassis.TYPE_DIFFERENTIAL);
	MovePilot pilot = new MovePilot(chassis);
	pilot.travel(1000);
	
	
	Rectangle suorakulmio = new Rectangle(0, 0, 1000, 900);
	Line[] janat = new Line[8];
	  
	// rajaavan suorakulmion sivut
	janat[0] = new Line(0,0,1000,0);
	janat[1] = new Line(1000,0,1000,900);
	janat[2] = new Line(0,900,1000,900);
	janat[3] = new Line(0,0,0,900);
	// esteet
	janat[4] = new Line(400,0,400,300);
	janat[5] = new Line(700,0,700,300);
	janat[6] = new Line(600,600,600,900);
	janat[7] = new Line(0,600,300,600);
	  
	LineMap kartta = new LineMap(janat, suorakulmio);
	Pose pose = new Pose(200, 200, 90);
	Navigator navi = new Navigator(pilot) ;
	PathFinder polunEtsijä = new ShortestPathFinder(kartta);
	Path path;
	try {
		path = polunEtsijä.findRoute(pose, new Waypoint(150, 850));
		navi.setPath(path);
		navi.followPath();
		navi.waitForStop();
	} catch (DestinationUnreachableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	

}