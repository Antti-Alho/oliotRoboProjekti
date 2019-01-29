package nike;


import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;


public class Robotti {
	
	public static void main(String[] args) {
		KosketusAnturi k = new KosketusAnturi();
		Behavior b1 = new EteenPäin(); 
		Behavior b2 = new Tyrmays(k); 
		Behavior [] bArray = {b1, b2};
		
		Arbitrator arby = new Arbitrator(bArray); 
		arby.go(); 
		
		
		
		
		
	}
}
