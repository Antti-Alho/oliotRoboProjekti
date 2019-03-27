package robot;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
/**
 * Handles the Lego EV3 Touch Sensor.
 * @author ville
 *
 */
public class Button {
	/**
	 * Determinates in which port the touch sensor is connected on the EV3.
	 */
	private Port port = LocalEV3.get().getPort("S1");
	/**
	 * Access the modes supported by leJOS sensors.
	 */
	private SensorModes sensor;
	/**
	 * Fetches data from a sensor.
	 */
	private SampleProvider touch;
	/**
	 * An array in which the data from the sensor is stored.
	 */
	float[] sample;
	/**
	 * Initializes the touch sensor.
	 */
	public Button() {
		while (sensor == null) {
			try {
				sensor = new EV3TouchSensor(port);
				touch = ((EV3TouchSensor)sensor).getTouchMode();
				sample = new float[touch.sampleSize()];
			} catch (IllegalArgumentException e) {
				System.out.println("Kiinnitä napin johto porttiin 1.");
			}
		}
	}
	/**
	 * 	Checks if the touch sensor is pressed.
	 * @return Returns true if the touch sensor is pressed. Returns false if the touch sensor is not pressed.
	 */
	public boolean pressed() {		
			touch.fetchSample(sample, 0);
			if(sample[0]>0.5) {
				return true;
			}
		return false;
	}	 
}
