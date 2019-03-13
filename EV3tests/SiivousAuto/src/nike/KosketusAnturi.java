package nike;


import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.*;
import lejos.hardware.sensor.EV3TouchSensor;

public class KosketusAnturi {
	private Port port = LocalEV3.get().getPort("S2");
	private SensorModes sensor;
	private SampleProvider touch;
	float[] sample;
	
	public KosketusAnturi() {
		while (sensor == null) {
			try {
				sensor = new EV3TouchSensor(port);
				touch = ((EV3TouchSensor)sensor).getTouchMode();
				sample = new float[touch.sampleSize()];
				
			} catch (IllegalArgumentException e) {
				System.out.println("Laita kosketus anturi kiinni!");
			}
		}
	}
		
	public boolean painettu() {		
			touch.fetchSample(sample, 0);
			if(sample[0]>0.5) {
				return true;
				
			}
		return false;
	}
	
	public SensorModes getSensor() {
		return sensor;
	}
	
}
