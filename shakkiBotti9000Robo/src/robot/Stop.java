package robot;

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
/**
 * The behavior which handles the situation in which the touch sensor is pressed for emergency stop while the motors are moving.
 * @author ville
 *
 */
public class Stop implements Behavior{
	/**
	 * Determinants the Motors class used in the behavior.
	 */
	private Motors motors;
	/**
	 * Determinants the Button class used in the behavior.
	 */
	private Button button;
	/**
	 * The boolean value used to determinate if the behavior is suppressed or not.
	 */
	private volatile boolean suppressed = false;
	/**
	 * Initializes the behavior.
	 * @param motors The Motors class used.
	 * @param button The Button class used.
	 */
	public Stop(Motors motors, Button button) {
		this.motors = motors;
		this.button = button;
	}
	/**
	 * The case in which this behavior takes control over the lower behaviors.
	 */
	public boolean takeControl() {
		return motors.isStopCheck();
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
		System.out.println("STOP");
		motors.setStopCheck(false);
		suppressed = true;
	}
}
