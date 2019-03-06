package robot;

import lejos.robotics.subsumption.Behavior;

public class Waiting implements Behavior{
	private volatile boolean suppressed = false;

	public boolean takeControl() {
		return true;
	}
	
	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
	}	
}	
