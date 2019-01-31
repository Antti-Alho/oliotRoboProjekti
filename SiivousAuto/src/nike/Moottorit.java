package nike;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.PortException;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Moottorit {

  private VariAnturi vari;
	private EV3LargeRegulatedMotor moottoriB;
	private EV3LargeRegulatedMotor moottoriC;
	
	public Moottorit(VariAnturi v) {
    this.vari = v;
		while (moottoriB == null || moottoriC == null) {
			try {
			moottoriB = new EV3LargeRegulatedMotor(MotorPort.B);
			} catch (PortException e) {
				System.out.println("Laita moottori B kiinni");
				continue;
			}
			try {
			moottoriC = new EV3LargeRegulatedMotor(MotorPort.C);
			} catch (PortException e) {
				System.out.println("Laita moottori C kiinni");
				continue;
			}
		}

	}
	public void MotorKayt() {
		moottoriB.setSpeed(300);
		moottoriC.setSpeed(300);
	}
	
	public EV3LargeRegulatedMotor getMoottoriB() {
		return moottoriB;
	}
	public EV3LargeRegulatedMotor getMoottoriC() {
		return moottoriC;
	}
	public void Eteen() {
		moottoriB.synchronizeWith(new RegulatedMotor[] {moottoriC});
		moottoriB.forward();
		moottoriC.forward();
	}
	
	public void Kaannos() {
		int ylitys = 0;
		int min = 3000;
		int max = 12000;
		double random = min + Math.random()*(max-min) ;
		int aika = (int)random;
		System.out.println(aika);
		moottoriB.backward();
		moottoriC.backward();
		Delay.msDelay(1200);
		for (int i = 0; i < aika; i++) {
			if (vari.ylitys()!=true && ylitys == 0) {
				moottoriC.forward();
			} else {
				ylitys = 1;
				moottoriB.backward();
				moottoriC.backward();
				if (vari.ylitys()!=true) {
					moottoriB.stop(true);
					moottoriC.stop(true);
					moottoriB.forward();
					moottoriC.backward();
					Delay.msDelay(2500);
					i = aika -1;
				}
				
			}
		}
	}
	
	public void MotorKiinni() {
		moottoriB.synchronizeWith(new RegulatedMotor[] {moottoriC});
		moottoriB.stop(true);
		moottoriC.stop(true);
		
	}
	public void MotorClose() {
		moottoriB.stop(true);
		moottoriC.stop(true);
		moottoriB.close();
		moottoriC.close();
	}
}
