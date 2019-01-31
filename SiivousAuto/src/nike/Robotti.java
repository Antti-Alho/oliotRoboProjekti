package nike;


import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;


public class Robotti {
	
	public static void main(String[] args) {
		KosketusAnturi k = new KosketusAnturi();
		VariAnturi v = new VariAnturi();
		InfraAnturi i = new InfraAnturi();
		Moottorit m = new Moottorit(v, i);
		Behavior b1 = new EteenPäin(m);
		Behavior b2 = new Raja(v,m, i);
		Behavior b3 = new Tyrmays(k, m); 
		Behavior [] bArray = {b1, b2, b3};
		
		Arbitrator arby = new Arbitrator(bArray); 
		arby.go(); 
		
		
		
		
		
	}
}
