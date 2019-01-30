package nike;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Moottorit {

	private EV3LargeRegulatedMotor moottoriB= new EV3LargeRegulatedMotor(MotorPort.B);
	private EV3LargeRegulatedMotor moottoriC= new EV3LargeRegulatedMotor(MotorPort.C);
	
	public Moottorit() {
		
	}
	public void MotorKayt() {
		moottoriB.setSpeed(300);
		moottoriC.setSpeed(300);
	}
	
	public void Eteen() {
		moottoriB.synchronizeWith(new RegulatedMotor[] {moottoriC});
		moottoriB.forward();
		moottoriC.forward();
	}
	
	public void Kaannos() {
		moottoriB.backward();
		moottoriC.backward();
		Delay.msDelay(1000);
		moottoriC.forward();
		Delay.msDelay(2000);
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
