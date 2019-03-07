package robot;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Button {
	private Port port = LocalEV3.get().getPort("S1");
	private SensorModes sensor;
	private SampleProvider touch;
	float[] sample;
	
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
		
	public boolean pressed() {		
			touch.fetchSample(sample, 0);
			if(sample[0]>0.5) {
				return true;
			}
		return false;
	}
}
