package robot;

import lejos.robotics.subsumption.Behavior;

public class RobotsTurn implements Behavior{
	private Data data;
	private Motors motors;
	private volatile boolean suppressed = false;

	public RobotsTurn(Data data, Motors motors) {
		this.data = data;
		this.motors = motors;
	}
	@Override
	public boolean takeControl() {
		return data.haveMoves();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
	
	@Override
	public void action() {
		suppressed = false;
		motors.movePieces();
		data.clearCrdnts();
		suppressed = true;
		while (!suppressed)Thread.yield();
		motors.startMotors();
		data.clearCrdnts();
	}
}
