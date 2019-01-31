package nike;


import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.*;
import lejos.utility.Delay;
import lejos.hardware.sensor.EV3TouchSensor;

public class KosketusAnturi {
	private int n = 0;
	private Port port = LocalEV3.get().getPort("S2");
	private SensorModes sensor = new EV3TouchSensor(port);
	private SampleProvider touch = ((EV3TouchSensor)sensor).getTouchMode();
	float[] sample = new float[touch.sampleSize()];
	
	public KosketusAnturi() {		
	}
		
	public boolean painettu() {		
			touch.fetchSample(sample, 0);
			if(sample[0]>0.5) {
				n++;
				System.out.println("Hataseis!" + n);
				Delay.msDelay(500);
				
				return true;
				
			}
		return false;
	}
	
	public SensorModes getSensor() {
		return sensor;
	}
	
}
