package robot;

import lejos.robotics.subsumption.Behavior;
/**
 * This is the lowest priority behavior which will be in action while all the other behaviors are being suppressed.
 * @author ville
 *
 */
public class Waiting implements Behavior{
	/**
	 * The boolean value used to determinate if the behavior is suppressed or not.
	 */
	private volatile boolean suppressed = false;
	/**
	 * The case in which this behavior takes control over the lower behaviors. Always true.
	 */
	public boolean takeControl() {
		return true;
	}
	/**
	 * When the behavior needs the be suppressed.
	 */
	public void suppress() {
		suppressed = true;
	}
	/**
	 * The actions which this behavior executes.
	 */
	public void action() {
		suppressed = false;
	}	
}	
