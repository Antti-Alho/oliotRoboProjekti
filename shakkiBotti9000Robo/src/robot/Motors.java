package robot;

import java.util.ArrayList;

import lejos.hardware.DeviceException;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Motors {

	private EV3LargeRegulatedMotor length;
	private EV3LargeRegulatedMotor width;
	private EV3MediumRegulatedMotor height;
	private EV3MediumRegulatedMotor pliers;
	private Data data;
	
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
	
	public Motors(Data data) {
		this.data = data;
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
	
	public void startMotors() {
		length.setSpeed(320);
		width.setSpeed(450);
		height.setSpeed(120);
		pliers.setSpeed(300);
	}
	
	public void movePieces() {
		ArrayList<Integer> crdnts = new ArrayList<>();
		crdnts = data.getCrdnts();
		int firstFromX 	= crdnts.get(0); 
		int firstFromY 	= crdnts.get(1);
		int firstToX 	= crdnts.get(2);
		int firstToY 	= crdnts.get(3);
		int secondFromX = crdnts.get(4);
		int secondFromY = crdnts.get(5);
		length.rotate(toBoard);
		length.rotate(firstFromX * lenghtRot);
		width.rotate(firstFromY * widthRot);
		height.rotate(-heightRot);
		pliers.rotate(-pliersRot);
		height.rotate(heightRot);
		if (firstToX >= 0) {				// siirretään nappula
			length.rotate( (firstToX - firstFromX) * lenghtRot);
			width.rotate( (firstToY - firstFromY) * widthRot);
			height.rotate(-heightRot);
			pliers.rotate(pliersRot);
			height.rotate(heightRot);
			width.rotate(-firstToY * widthRot);
			length.rotate(-firstToX * lenghtRot);
		} else {						// syödään nappula
			width.rotate(-firstFromY * widthRot);
			length.rotate(-firstFromX * lenghtRot);
			length.rotate(-toBoard);
			pliers.rotate(pliersRot);
			length.rotate(toBoard);		//takasin laudalla
			//alotetaan toisen nappulan siirto
			length.rotate(secondFromX * lenghtRot);
			width.rotate(secondFromY * widthRot);
			height.rotate(-heightRot);
			pliers.rotate(-pliersRot);
			height.rotate(heightRot);
			length.rotate( (firstFromX - secondFromX) * lenghtRot);
			width.rotate( (firstFromY - secondFromY) * widthRot);
			height.rotate(-heightRot);
			pliers.rotate(pliersRot);
			height.rotate(heightRot);
			width.rotate(-firstFromY * widthRot);
			length.rotate(-firstFromX * lenghtRot);
		}
		length.rotate(-toBoard);
	}
	
	public void stopMotors() {
		length.stop(true);
		width.stop(true);
		height.stop(true);
		pliers.stop(true);
	}
	
	public void closeMotors() {
		length.close();
		width.close();
		height.close();
		pliers.close();
	}
	
}
