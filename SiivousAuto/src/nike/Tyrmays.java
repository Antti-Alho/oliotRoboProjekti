package nike;

import lejos.robotics.subsumption.Behavior;


public class Tyrmays implements Behavior{
	private KosketusAnturi kosketus;
	private volatile boolean suppressed = false;
	
	
	public Tyrmays(KosketusAnturi k) {
		this.kosketus = k;
	}

	public boolean takeControl() { 
		return kosketus.painettu();  // törmättiinkö? 
	} 
	public void suppress() {
		suppressed = true;
	} 
	public void action() { 
		suppressed = false;
		System.out.println("Laite suljettu.");
		while(!suppressed) Thread.yield();
		System.out.println("Asd");
		//while(käännös kesken && !suppressed) Thread.yield(); 
		//if(suppressed){ pysäytä moottorit } 
		
		} 

}
