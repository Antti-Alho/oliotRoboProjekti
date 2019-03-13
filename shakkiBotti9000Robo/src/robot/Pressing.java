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
		System.out.println("PAINETTU");
		data.playerDone();
		System.out.println("KONEELLE ILMOTETTU");
		data.receiveMoves();
		System.out.println("Siirron pitäisi olla saapunut");
		suppressed = true;
	}
}
