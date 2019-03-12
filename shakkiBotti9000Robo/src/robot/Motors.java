package robot;

import java.util.ArrayList;

import lejos.hardware.DeviceException;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import robot.RobotsTurn;

public class Motors {

	private EV3LargeRegulatedMotor length;
	private EV3LargeRegulatedMotor width;
	private EV3MediumRegulatedMotor height;
	private EV3MediumRegulatedMotor pliers;
	private Data data;
	private Button button;
	private boolean StopCheck = false;
	
	//katsotaan pelaajan näkökulmasta
	//pitkittäin	lenght	default rotate suunta: + pelaajaa päin,	- pois päin
	//poikittain 	width 	default rotate suunta: + vasemmalle, 	- oikealle
	//korkeus		height	default rotate suunta: + alas, 			- ylös
	//pihdit 	 	pliers	default rotate suunta: + kiinni, 		- auki
	
	int lenghtRot = 94;		// 94	yksi ruutu
	int widthRot = -280;	// 280	yksi ruutu						HUOM. käänteellinen pyörimissuunta
	int heightRot = -282;	// 282	max liikkumis					HUOM. käänteellinen pyörimissuunta
	int pliersRot = -335;	// 325	sopiva kaikkille nappuloille	HUOM. käänteellinen pyörimissuunta
	int toBoard = 390; 		// 390	vakioetäisyys odotuspaikan ja ensimmäisen ruudun välillä
	
	public Motors(Data data, Button button) {
		this.data = data;
		this.button = button;
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
		width.setSpeed(500);	//450
		height.setSpeed(120);
		pliers.setSpeed(300);
	}
	
	public void movePieces() throws NullPointerException {
		ArrayList<Integer> crdnts = new ArrayList<>();
		crdnts = data.getCrdnts();
		int fromX = crdnts.get(0);
		int fromY = crdnts.get(1);
		int toX = crdnts.get(2);
		int toY = crdnts.get(3);
		int target = crdnts.get(4);
		
		length.rotate(toBoard, true);
		largeCheck(length);
		if (target >= 0) {
			//haetaan syötävä
			length.rotate(toX * lenghtRot, true);
			largeCheck(length);
			width.rotate(toY * widthRot, true);
			largeCheck(width);
			height.rotate(-heightRot, true);
			mediumCheck(height);
			pliers.rotate(-pliersRot, true);
			mediumCheck(pliers);
			height.rotate(heightRot, true);
			mediumCheck(height);
			
			//viedään syöty hautausmaalle
			width.rotate(-toY * widthRot, true);
			largeCheck(width);
			length.rotate(-toX * lenghtRot, true);
			largeCheck(length);
			length.rotate(-toBoard, true);
			largeCheck(length);
			pliers.rotate(pliersRot, true);
			mediumCheck(pliers);
			length.rotate(toBoard, true);
			largeCheck(length);
		}
		//haetaan siirrettävä
		length.rotate(fromX * lenghtRot, true);
		largeCheck(length);
		width.rotate(fromY * widthRot, true);
		largeCheck(width);
		height.rotate(-heightRot, true);
		mediumCheck(height);
		pliers.rotate(-pliersRot, true);
		mediumCheck(pliers);
		height.rotate(heightRot, true);
		mediumCheck(height);
		//siirretään kohteeseen
		length.rotate( (toX - fromX) * lenghtRot, true);
		largeCheck(length);
		width.rotate( (toY - fromY) * widthRot, true);
		largeCheck(width);
		height.rotate(-heightRot, true);
		mediumCheck(height);
		pliers.rotate(pliersRot, true);
		mediumCheck(pliers);
		height.rotate(heightRot, true);
		mediumCheck(height);
		//pois laudalta
		width.rotate(-toY * widthRot, true);
		largeCheck(width);
		length.rotate(-toX * lenghtRot, true);
		largeCheck(length);
		length.rotate(-toBoard, true);
		largeCheck(length);
	}
	
	public void mediumCheck(EV3MediumRegulatedMotor motor) {
		while(motor.isMoving()) {
			if(button.pressed()==true) {
				motor.stop();
				throw new NullPointerException();
			}
		}
	}
	
	public void largeCheck(EV3LargeRegulatedMotor motor) {
		while(motor.isMoving()) {
			if(button.pressed()==true) {
				motor.stop();
				throw new NullPointerException();
			}
		}
	}
	
	public boolean isStopCheck() {
		return StopCheck;
	}

	public void setStopCheck(boolean stopCheck) {
		StopCheck = stopCheck;
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
