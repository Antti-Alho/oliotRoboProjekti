package nike;


import lejos.robotics.subsumption.Behavior;


public class EteenPäin implements Behavior{
	private Moottorit motor;
	private volatile boolean suppressed = false;
	
	public EteenPäin(Moottorit m) {
		this.motor = m;
	}
	
	public boolean takeControl() { return true; }
	//aina valmis menemään eteenpäin
	
	public void suppress() { 
		suppressed = true;
	} 
	public void action() { 		
		suppressed = false;
		motor.MotorKayt();
		motor.Eteen();		
		while (!suppressed)Thread.yield();
		motor.MotorKiinni();
		
		}

		
} 


