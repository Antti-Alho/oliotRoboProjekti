package robot;

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Pressing implements Behavior{
	private Data data;
	private Motors motors;
	private Button button;
	private volatile boolean suppressed = false;
	
	public Pressing(Data data, Motors motors, Button button) {
		this.data = data;
		this.motors = motors;
		this.button = button;
	}
	
	public boolean takeControl() {
		return button.pressed();
	}
	
	public void suppress() {
		suppressed = true;
	} 
	
	public void action() { 
		suppressed = false;
		if(data.haveMoves() == true) {
			Delay.msDelay(2000);
			while (button.pressed() == false) {
				//onko jotai listenerii
			}
			data.clearCrdnts();
			suppressed = true;
		} else {
			data.playerDone();
			data.receiveMoves();
			suppressed = true;
		}
	}
}
