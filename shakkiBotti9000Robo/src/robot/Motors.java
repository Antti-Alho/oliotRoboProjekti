package robot;

import lejos.hardware.DeviceException;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Motors {

	private EV3LargeRegulatedMotor length;
	private EV3LargeRegulatedMotor width;
	private EV3MediumRegulatedMotor height;
	private EV3MediumRegulatedMotor pliers;
	
	//katsotaan pelaajan näkökulmasta
	//pitkittäin	lenght	default rotate suunta: + pelaajaa päin,	- pois päin
	//poikittain 	width 	default rotate suunta: + vasemmalle, 	- oikealle
	//korkeus		height	default rotate suunta: + alas, 			- ylös
	//pihdit 	 	pliers	default rotate suunta: + kiinni, 		- auki
	
	int lenghtRot = 96;		// 96	yksi ruutu
	int widthRot = -280;	// 280	yksi ruutu						HUOM. käänteellinen pyörimissuunta
	int heightRot = -282;	// 282	max liikkumis					HUOM. käänteellinen pyörimissuunta
	int pliersRot = -325;	// 325	sopiva kaikkille nappuloille	HUOM. käänteellinen pyörimissuunta
	int toBoard = 372;		// 372	vakio etäisyys odotuspaikan ja ensimmäisen ruudun välillä
	
	public Motors() {
		while (length == null || width == null || height == null || pliers == null) {
			try {
				length = new EV3LargeRegulatedMotor(MotorPort.A);
			} catch (DeviceException e) {
				System.out.println("Laita moottori A kiinni");
				continue;
			}
			try {
				width = new EV3LargeRegulatedMotor(MotorPort.B);
			} catch (DeviceException e) {
				System.out.println("Laita moottori B kiinni");
				continue;
			}
			try {
				height = new EV3MediumRegulatedMotor(MotorPort.C);
			} catch (DeviceException e) {
				System.out.println("Laita moottori C kiinni");
				continue;
			}
			try {
				pliers = new EV3MediumRegulatedMotor(MotorPort.D);
			} catch (DeviceException e) {
				System.out.println("Laita moottori D kiinni");
				continue;
			}
		}
	}
	
	public void StartMove() {
		length.setSpeed(320);
		width.setSpeed(450);
		height.setSpeed(120);
		pliers.setSpeed(300);
		length.rotate(toBoard);
	}
	
	
	public void MovePiece(int fromX, int fromY, int toX, int toY) {
		length.rotate(fromX * lenghtRot);
		width.rotate(fromY * widthRot);
		height.rotate(-heightRot);
		pliers.rotate(-pliersRot);
		height.rotate(heightRot);
		if (toX < 0) {				// jos syödään nappula
			width.rotate(-fromY * widthRot);
			length.rotate(-fromX * lenghtRot);
			length.rotate(-toBoard);
			pliers.rotate(pliersRot);	
		} else {
			length.rotate( (toX - fromX) * lenghtRot);
			width.rotate( (toY - fromY) * widthRot);
			height.rotate(-heightRot);
			pliers.rotate(pliersRot);
			height.rotate(heightRot);
			width.rotate(-toY * widthRot);
			length.rotate(-toX * lenghtRot);
			length.rotate(-toBoard);
		}
	}
	
	
	
}
