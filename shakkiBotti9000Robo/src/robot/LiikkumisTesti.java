package robot;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class LiikkumisTesti {

	public static void main(String[] args) {
		
		EV3LargeRegulatedMotor pitkittain = new EV3LargeRegulatedMotor(MotorPort.A);
		EV3LargeRegulatedMotor poikittain = new EV3LargeRegulatedMotor(MotorPort.B);
		EV3MediumRegulatedMotor korkeus = new EV3MediumRegulatedMotor(MotorPort.C);
		EV3MediumRegulatedMotor pihdit = new EV3MediumRegulatedMotor(MotorPort.D);
		
		pitkittain.setSpeed(320);
		poikittain.setSpeed(450);
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
		
		int pitkruutu = 96;
		int poikruutu = 280;
		int korkeusrotate = 282;
		int pihditrotate = 325;
		int laudalle = 372;
		
		int ruudustaX = 5;
		int ruudustaY = 5;
		
		int ruutuunX = 3;
		int ruutuunY = 3;
		
		//siirto
		/**
		pitkittain.rotate(laudalle);
		
		pitkittain.rotate(ruudustaX*pitkruutu);
		poikittain.rotate(ruudustaY*poikruutu);
		korkeus.rotate(-korkeusrotate);
		pihdit.rotate(-pihditrotate);
		korkeus.rotate(korkeusrotate);
		
		pitkittain.rotate((ruutuunX-ruudustaX)*pitkruutu);
		poikittain.rotate((ruutuunY-ruudustaY)*poikruutu);
		korkeus.rotate(-korkeusrotate);
		pihdit.rotate(pihditrotate);
		korkeus.rotate(korkeusrotate);
		
		poikittain.rotate(-ruutuunY*poikruutu);
		pitkittain.rotate(-ruutuunX*pitkruutu);
		pitkittain.rotate(-laudalle);
		**/
		
		pitkittain.close();
		poikittain.close();
		korkeus.close();
		pihdit.close();
		
	}
	
	
	
}
