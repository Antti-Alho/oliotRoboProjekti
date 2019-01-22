package auto;

import lejos.utility.Delay;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.*;
import lejos.robotics.RegulatedMotor;
public class ev3Test {

	public static void main(String[] args) {
			
			
			EV3LargeRegulatedMotor moottoriB= new EV3LargeRegulatedMotor(MotorPort.B);
			EV3LargeRegulatedMotor moottoriC= new EV3LargeRegulatedMotor(MotorPort.C);
			moottoriB.synchronizeWith(new RegulatedMotor[] {moottoriC});
			
			moottoriB.setSpeed(2000);
			moottoriC.setSpeed(2000);
			moottoriB.forward();
			moottoriC.forward();
			Delay.msDelay(820);
			moottoriB.stop(true);
			moottoriC.stop(true);

			//eka mutka
			moottoriB.forward();
			moottoriC.backward();
			Delay.msDelay(800);
			moottoriB.stop(true);
			moottoriC.stop(true);
			
			// toinen suora 		
			moottoriB.forward();
			moottoriC.forward();
			Delay.msDelay(700);
			moottoriB.stop(true);
			moottoriC.stop(true);		
			
			// iso kaarre 
			moottoriC.setSpeed(350);
			moottoriB.setSpeed(1500);
			for (int i = 0; i < 17; i++) {
				moottoriB.forward();
				moottoriC.forward();
				Delay.msDelay(70);
				moottoriB.stop(true);
				moottoriC.stop(true);
				moottoriB.forward();
				Delay.msDelay(100);
				moottoriB.stop(true);
			}
			moottoriB.forward();
			Delay.msDelay(50);
			moottoriB.stop(true);
			Delay.msDelay(200);
			
			
			//toka suora
			moottoriB.setSpeed(2000);
			moottoriC.setSpeed(2000);
			moottoriB.forward();
			moottoriC.forward();
			Delay.msDelay(1800);
			moottoriB.stop(true);
			moottoriC.stop(true);
			Delay.msDelay(200);	

			//peruutus
			moottoriC.backward();
			Delay.msDelay(600);
			moottoriC.stop(true);
			Delay.msDelay(200);

			moottoriB.backward();
			moottoriC.backward();
			Delay.msDelay(400);
			moottoriB.stop(true);
			moottoriC.stop(true);
	
			moottoriC.backward();
			Delay.msDelay(300);
			moottoriC.stop(true);
			Delay.msDelay(200);

			moottoriB.backward();
			moottoriC.backward();
			Delay.msDelay(435);
			moottoriB.stop(true);
			moottoriC.stop(true);
			Delay.msDelay(200);
			
			//loppusuora
			moottoriB.forward();
			Delay.msDelay(650);
			moottoriB.stop(true);
			Delay.msDelay(200);
			
			moottoriB.setSpeed(2000);
			moottoriC.setSpeed(2000);
			moottoriB.forward();
			moottoriC.forward();
			Delay.msDelay(600);
			moottoriB.stop(true);
			moottoriC.stop(true);
			
			
			moottoriB.close();
			moottoriC.close();
	}

}
