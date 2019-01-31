package nike;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.*;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3TouchSensor;

public class InfraAnturi {
	private Port port = LocalEV3.get().getPort("S4");
	private SensorModes sensor;
	private SampleProvider distance;
	float[] sample;

	public InfraAnturi() {
		while (sensor == null) {
			try {
				sensor = new EV3IRSensor(port);
				distance = ((EV3IRSensor)sensor).getDistanceMode();
				sample = new float[distance.sampleSize()];
			} catch (IllegalArgumentException e) {
				System.out.println("Laita infra-Anturi anturi kiinni!");
			}
		}
	}
	
	public boolean este() {
		distance.fetchSample(sample, 0);
		if (sample[0] <= 6) {
			return true;
		}
		return false;
	}
	public boolean vaisto() {
		distance.fetchSample(sample, 0);
		if (sample[0] <= 1) {
			return true;
		}
		return false;
	}
	
	
}