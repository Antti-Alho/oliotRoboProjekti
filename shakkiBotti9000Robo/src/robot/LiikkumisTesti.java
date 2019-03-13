package robot;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class LiikkumisTesti {

	public static void main(String[] args) {
		
		EV3LargeRegulatedMotor pitkittain = new EV3LargeRegulatedMotor(MotorPort.A);
		EV3LargeRegulatedMotor poikittain = new EV3LargeRegulatedMotor(MotorPort.B);
		EV3MediumRegulatedMotor korkeus = new EV3MediumRegulatedMotor(MotorPort.C);
		EV3MediumRegulatedMotor pihdit = new EV3MediumRegulatedMotor(MotorPort.D);
		
		pitkittain.setSpeed(320);
		poikittain.setSpeed(1000);
		korkeus.setSpeed(120);
		pihdit.setSpeed(300);
		
		//katsotaan pelaajan näkökulmasta
		//pitkittäin + pelaajaa päin, - pois päin
		//poikittain + on vasemmalle, - oikealle
		//korkeus rotate + on alas, rotate - on ylös
		//pihdit rotate + on kiinni, rotate - on auki
		//poikittain 0->1 = 372
		//poikittain  koko 1030
		//poikittain ruutu 280
		//pitkittäin koko 1960
		//pitkittäin 95
		//korkeus 280
		//pihdit 325
		
		int pitkr = 94;
		int poikr = -280;
		int korkeusr = -282;
		int pihditr = -320;
		int laudalle = 385;
		
		int fromX = 1;
		int fromY = 0;
		
		int toX = 2;
		int toY = 1;
		/*
		while(toY==1){
			pihdit.rotate(-pihditr);
			Delay.msDelay(1000);
			pihdit.rotate(pihditr);
			Delay.msDelay(3000);
		}
		*/
		
		for (int i = 0 ; i < 8; i++) {
			pitkittain.rotate(laudalle);
			pitkittain.rotate(pitkr*i);
			poikittain.rotate(poikr*i);
			//nosto
			korkeus.rotate(-korkeusr);
			pihdit.rotate(-pihditr);
			korkeus.rotate(korkeusr);
			//lasku
			korkeus.rotate(-korkeusr);
			pihdit.rotate(pihditr);
			korkeus.rotate(korkeusr);
			poikittain.rotate(-poikr*i);
			pitkittain.rotate(-pitkr*i);
			pitkittain.rotate(-laudalle);
			Delay.msDelay(500);
		}
		
		
		pitkittain.close();
		poikittain.close();
		korkeus.close();
		pihdit.close();
	}
}