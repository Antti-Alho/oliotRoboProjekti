package nike;

import lejos.robotics.subsumption.Behavior;

public class Raja implements Behavior{
	private VariAnturi variAnturi;
	private volatile boolean suppressed = false; 

	public Raja (VariAnturi v) {
		this.variAnturi = v;
	}

	public boolean takeControl() {
		return variAnturi.ylitys();
	}

	public void action() {
		suppressed = false;
		System.out.println("Kaannos");
		while(!suppressed)Thread.yield();
		System.out.println("dsa");
		
	}

	public void suppress() {
		suppressed = true;
		
		
	}
	

}
