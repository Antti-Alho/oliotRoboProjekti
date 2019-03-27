package robot;

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
/**
 * The behavior for which handles the situation in which the touch sensor is pressed while the motors aren't moving.
 * @author ville
 *
 */
public class Pressing implements Behavior{
	/**
	 * Determinants the Data class used in the behavior.
	 */
	private Data data;
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
	 * @param data The Data class used.
	 * @param motors The Motors class used.
	 * @param button The Button vlass used.
	 */
	public Pressing(Data data, Motors motors, Button button) {
		this.data = data;
		this.motors = motors;
		this.button = button;
	}
	/**
	 * The case in which this behavior takes control over the lower behaviors.
	 */
	public boolean takeControl() {
		return button.pressed();
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
		System.out.println("PAINETTU");
		data.playerDone();
		System.out.println("KONEELLE ILMOTETTU");
		data.receiveMoves();
		System.out.println("Siirron pitäisi olla saapunut");
		suppressed = true;
	}
}
