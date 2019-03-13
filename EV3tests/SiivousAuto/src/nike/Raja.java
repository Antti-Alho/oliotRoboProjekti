package nike;

import lejos.robotics.subsumption.Behavior;


public class Raja implements Behavior{
	private VariAnturi variAnturi;
	private Moottorit moottori;
	private InfraAnturi infra;
	private volatile boolean suppressed = false; 

	public Raja (VariAnturi v, Moottorit m, InfraAnturi i) {
		this.variAnturi = v;
		this.moottori = m;
		this.infra = i;
	}
	

	public boolean takeControl() {
		return variAnturi.ylitys() || infra.este();
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
