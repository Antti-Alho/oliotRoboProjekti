package robot;

import java.io.IOException;
import java.util.ArrayList;

import lejos.hardware.DeviceException;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import robot.RobotsTurn;
/**
 * Handles the Lego EV3 motors and their movements.
 */
public class Motors {
	/**
	 * Determinates the motor which is used for moving the robot in the longitudinal direction.
	 */
	private EV3LargeRegulatedMotor length;
	/**
	 * Determinates the motor which is used for moving the robot in the width direction.
	 */
	private EV3LargeRegulatedMotor width;
	/**
	 * Determinates the motor which is used for rising and lowering the pliers.
	 */
	private EV3MediumRegulatedMotor height;
	/**
	 * Determinates the motor which is used for opening and closing the pliers.
	 */
	private EV3MediumRegulatedMotor pliers;
	/**
	 * Defines the Data class used for fetching data which will be used to determinate the moves.
	 */
	private Data data;
	/**
	 * Defines the Button class used for reading the touch sensor input.
	 */
	private Button button;
	/**
	 * Used in the emergency stop.
	 */
	private boolean StopCheck = false;
	
	//katsotaan pelaajan näkökulmasta
	//pitkittäin	lenght	default rotate suunta: + pelaajaa päin,	- pois päin
	//poikittain 	width 	default rotate suunta: + vasemmalle, 	- oikealle
	//korkeus		height	default rotate suunta: + alas, 			- ylös
	//pihdit 	 	pliers	default rotate suunta: + kiinni, 		- auki
	/**
	 * The amount of degrees the length-motor needs to rotate for advancing one square on the board. 
	 */
	int lenghtRot = 94;		// 94	yksi ruutu
	/**
	 * The amount of degrees the width-motor needs to rotate for advancing one square on the board. 
	 */
	int widthRot = -280;	// 280	yksi ruutu						HUOM. käänteellinen pyörimissuunta
	/**
	 * The maximum degrees the height-motor is able to rotate.
	 */
	int heightRot = -282;	// 282	max liikkumis					HUOM. käänteellinen pyörimissuunta
	/**
	 * The amount of degrees the pliers-motor needs to rotate to firmly grip on any of the chess pieces.
	 */
	int pliersRot = -320;	// 325	sopiva kaikkille nappuloille	HUOM. käänteellinen pyörimissuunta
	/**
	 * The amount of degrees the length-motor needs to rotate to reach the first square of the board when the robot is in it's starting position.
	 */
	int toBoard = 392; 		// 390	vakioetäisyys odotuspaikan ja ensimmäisen ruudun välillä
	/**
	 * Initializes the motors the robot will be using.
	 * @param data Determinants the Data class the motors will be utilizing.
	 * @param button Determinants the Button class the motors will be utilizing.
	 */
	public Motors(Data data, Button button) {
		this.data = data;
		this.button = button;
		while (length == null || width == null || height == null || pliers == null) {
			try {
				length = new EV3LargeRegulatedMotor(MotorPort.A);
				length.setSpeed(320);
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
	/**
	 * Sets how fast each of the motors will be rotating.
	 */
	public void startMotors() {
		length.setSpeed(320);
		width.setSpeed(550);	//450
		height.setSpeed(120);
		pliers.setSpeed(300);
	}
	/**
	 * Moves the chess pieces on the board. Starting and ending location of each move and possibility of target being eaten is fetched from the Data class in an arraylist.
	 * @throws NullPointerException If the touch sensor is pressed during the robot is performing it's move, this is send to end the movement.
	 */
	public void movePieces() throws NullPointerException {
		ArrayList<Integer> crdnts = new ArrayList<>();
		crdnts = data.getCrdnts();
		int fromX = crdnts.get(0);
		int fromY = crdnts.get(1);
		int toX = crdnts.get(2);
		int toY = crdnts.get(3);
		int target = crdnts.get(4);
		
		if (crdnts.get(0) == -1) {
			shutDown();
		}
		length.rotate(toBoard, true);
		largeCheck(length);
		if (target == 1) {
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
			if(fromX == -2) {
				shutDown();
			}
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
	/**
	 * Checks if the touch sensor is pressed during a EV3 medium motor is rotating. If it's pressed, exception is thrown which triggers emergency stop.
	 * @param motor Which medium sensor is rotating while the check is being performed.
	 */
	public void mediumCheck(EV3MediumRegulatedMotor motor) {
		while(motor.isMoving()) {
			if(button.pressed()==true) {
				motor.stop();
				throw new NullPointerException();
			}
		}
	}
	/**
	 * Checks if the touch sensor is pressed during a EV3 large motor is rotating. If it's pressed, exception is thrown which triggers emergency stop.
	 * @param motor Which large sensor is rotating while the check is being performed.
	 */
	public void largeCheck(EV3LargeRegulatedMotor motor) {
		while(motor.isMoving()) {
			if(button.pressed()==true) {
				motor.stop();
				throw new NullPointerException();
			}
		}
	}
	/**
	 * Checks if the touch sensor have been pressed while a motor have been moving.
	 * @return Returns true if the touch sensor have been pressed while a motor have been moved. Returns false if not.
	 */
	public boolean isStopCheck() {
		return StopCheck;
	}
	/**
	 * Sets the status of the emergency stop check.
	 * @param stopCheck Is the emergency stop check set false for resuming or true for continuing the emergency stop. 
	 */
	public void setStopCheck(boolean stopCheck) {
		StopCheck = stopCheck;
	}
	/**
	 * Stops the movement of all the motors.
	 */
	public void stopMotors() {
		length.stop(true);
		width.stop(true);
		height.stop(true);
		pliers.stop(true);
	}
	/**
	 * Shuts down all of the motors, data input and output streams and ends the program.
	 */
	public void shutDown() {
		try {
			data.in.close();
			data.out.close();
			length.close();
			width.close();
			height.close();
			pliers.close();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
