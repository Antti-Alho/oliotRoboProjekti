package nike;


import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;


public class Robotti {
	
	public static void main(String[] args) {
		KosketusAnturi k = new KosketusAnturi();
		VariAnturi v = new VariAnturi();
		Moottorit m = new Moottorit(v);
		Alustus a = new Alustus(k,m,v);
		Behavior b1 = new EteenPäin(m);
		Behavior b2 = new Raja(v,m);
		Behavior b3 = new Tyrmays(k, m); 
		Behavior [] bArray = {b1, b2, b3};

		if (a.lahtoValmis()) {
			Arbitrator arby = new Arbitrator(bArray);
			arby.go(); 
		} else {
			System.out.println("ei toimi");
		}
		
		
		
		
		
	}
}
