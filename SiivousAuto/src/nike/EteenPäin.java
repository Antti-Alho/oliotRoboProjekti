package nike;


import lejos.robotics.subsumption.Behavior;
import lejos.robotics.RegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.*;
import lejos.utility.Delay;


public class EteenPäin implements Behavior{
	private volatile boolean suppressed = false;
	private EV3LargeRegulatedMotor moottoriB= new EV3LargeRegulatedMotor(MotorPort.B);
	private EV3LargeRegulatedMotor moottoriC= new EV3LargeRegulatedMotor(MotorPort.C);
	
	public boolean takeControl() { return true; }
	//aina valmis menemään eteenpäin
	
	public void suppress() { 
		suppressed = true;
	} 
	public void action() { 		
		moottoriB.synchronizeWith(new RegulatedMotor[] {moottoriC});
		suppressed = false;
		moottoriB.setSpeed(300);
		moottoriC.setSpeed(300);
		moottoriB.forward();
		moottoriC.forward();
		while (!suppressed)Thread.yield();
		moottoriB.stop(true);
		moottoriC.stop(true);
		moottoriB.close();
		moottoriC.close();
		}

		
} 


