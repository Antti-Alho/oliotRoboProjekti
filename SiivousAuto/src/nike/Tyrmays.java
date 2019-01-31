package nike;

import lejos.robotics.subsumption.Behavior;



public class Tyrmays implements Behavior{
	private KosketusAnturi kosketus;
	private Moottorit motor;

	private volatile boolean suppressed = false;
	
	public Tyrmays(KosketusAnturi k, Moottorit m) {
		this.kosketus = k;
		this.motor = m;
	}

	public boolean takeControl() { 
		return kosketus.painettu();  // törmättiinkö? 
	} 
	public void suppress() {
		suppressed = true;
	} 
	public void action() { 
		suppressed = false;
		motor.MotorClose();
		System.out.println("Laite suljettu.");
		while(!suppressed) Thread.yield();
		System.out.println("Asd");
 
		
		} 

}
