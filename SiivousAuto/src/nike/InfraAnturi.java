package nike;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.*;
import lejos.hardware.sensor.EV3IRSensor;

public class InfraAnturi {
	private Port port = LocalEV3.get().getPort("S4");
	private SensorModes sensor = new EV3IRSensor(port);
	private SampleProvider distance = ((EV3IRSensor)sensor).getDistanceMode();
	float[] sample = new float[distance.sampleSize()];

	public InfraAnturi() {		
	}
	
	public boolean este() {
		distance.fetchSample(sample, 0);
		if (sample[0] <= 6) {
			return true;
		}
		return false;
	}
	
}