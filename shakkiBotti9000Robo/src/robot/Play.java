package robot;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Play {
	
	public static void main(String[] args) {
		
		Button b = new Button();
		System.out.println("Odotetaan yhteyttä tietokoneeseen.");
		Data d = new Data();
		System.out.println("Yhteys muodostettu.");
		Motors m = new Motors(d);
		
		Behavior b1 = new Waiting();
		Behavior b2 = new RobotsTurn(d, m);
		Behavior b3 = new Pressing(d, m, b);
		Behavior [] bArray = {b1, b2, b3};
		
		Arbitrator arby = new Arbitrator(bArray);
		arby.go();
	}
}
