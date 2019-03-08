package robot;

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Stop implements Behavior{
	private Motors motors;
	private Button button;
	private volatile boolean suppressed = false;
	
	public Stop(Motors motors, Button button) {
		this.motors = motors;
		this.button = button;
	}
	
	public boolean takeControl() {
		return motors.isStopCheck();
	}
	
	public void suppress() {
		suppressed = true;
	} 
	
	public void action() { 
		suppressed = false;
		motors.setStopCheck(false);
		suppressed = true;
	}
}
