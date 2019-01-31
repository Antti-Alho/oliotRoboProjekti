package nike;

import lejos.robotics.subsumption.Behavior;


public class Raja implements Behavior{
	private VariAnturi variAnturi;
	private Moottorit moottori;
	private volatile boolean suppressed = false; 

	public Raja (VariAnturi v, Moottorit m) {
		this.variAnturi = v;
		this.moottori = m;
	}
	

	public boolean takeControl() {
		return variAnturi.ylitys();
	}

	public void action() {
		suppressed = false;
		moottori.MotorKayt();
		moottori.Kaannos();
		moottori.MotorKiinni();	
		suppressed = true;
		
		
	}

	public void suppress() {
		suppressed = true;
		
		
	}
	

}
