package robot;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Play {
	
	public static void main(String[] args) {
		
		Data d = new Data();
		Motors m = new Motors(d);
		Button b = new Button();
		Behavior b1 = new Waiting();
		Behavior b2 = new RobotsTurn(d, m);
		Behavior b3 = new Pressing(d, m, b);
		Behavior [] bArray = {b1, b2, b3};
		
		Arbitrator arby = new Arbitrator(bArray);
		arby.go();
	}
}
