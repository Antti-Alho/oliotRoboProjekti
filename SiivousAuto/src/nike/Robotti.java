package nike;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;


public class Robotti {
	
	public static void main(String[] args) {
		
		VariAnturi v = new VariAnturi();
		KosketusAnturi k = new KosketusAnturi();
		InfraAnturi i = new InfraAnturi();
		Moottorit m = new Moottorit(v, i);
		Alustus a = new Alustus(k,m,v);
		Behavior b1 = new EteenPäin(m);
		Behavior b2 = new Raja(v,m, i);
		Behavior b3 = new Tyrmays(k, m); 
		Behavior [] bArray = {b1, b2, b3};
		
		System.out.println("Paina nappia teipin paalla!");
		while (a.lahtoValmis() != true) {
			continue;
		}
		System.out.println("Siirra robotti siivousalueelle");
		Arbitrator arby = new Arbitrator(bArray);
		Delay.msDelay(2000);
		for (int j = 0; j < 3; j++) {
			System.out.println(j);
			Delay.msDelay(1000);
			
		}
		System.out.println("Tyo alkakoon!");
		arby.go();
		
	}
}
