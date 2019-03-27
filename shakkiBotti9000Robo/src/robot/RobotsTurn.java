package robot;

import lejos.robotics.subsumption.Behavior;
/**
 * The behavior which handles moving the chess pieces.
 * @author ville
 *
 */
public class RobotsTurn implements Behavior{
	/**
	 * Determinants the Data class used in the behavior.
	 */
	private Data data;
	/**
	 * Determinants the Motors class used in the behavior.
	 */
	private Motors motors;
	/**
	 * The boolean value used to determinate if the behavior is suppressed or not.
	 */
	private volatile boolean suppressed = false;
	/**
	 * Initializes the behavior.
	 * @param data The Data class used.
	 * @param motors The Motors class used.
	 */
	public RobotsTurn(Data data, Motors motors) {
		this.data = data;
		this.motors = motors;
	}
	/**
	 * The case in which this behavior takes control over the lower behaviors.
	 */
	@Override
	public boolean takeControl() {
		return data.haveMoves();
	}
	/**
	 * When the behavior needs the be suppressed.
	 */
	@Override
	public void suppress() {
		suppressed = true;
	}
	/**
	 * The actions which this behavior executes.
	 */
	@Override
	public void action() {
		suppressed = false;
		try {
			motors.movePieces();
			data.clearCrdnts();
			suppressed = true;
		} catch (NullPointerException e) {
			data.clearCrdnts();
			motors.setStopCheck(true);
			suppressed = true;
		}
	}
}
