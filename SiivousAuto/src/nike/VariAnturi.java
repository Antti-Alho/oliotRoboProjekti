package nike;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;


public class VariAnturi{

	private Port port = LocalEV3.get().getPort("S1");	//mistä portista
	private SensorModes sensor = new EV3ColorSensor(port);	//mitä siinä portissa on
	private SampleProvider colorProvider = ((EV3ColorSensor)sensor).getRGBMode();	//mitä se anturi tekee
	private float[] sample = new float[colorProvider.sampleSize()];	// tallennetaan anturin ottama väriarvo talteen
	private int[] vari = {205, 45, 21};
	
	public VariAnturi() {
		
	}
	public boolean ylitys() {
		colorProvider.fetchSample(sample, 0);
		int r = Math.round(sample[0]*765);
		int g = Math.round(sample[1]*765);
		int b = Math.round(sample[2]*765);
		
		int[] havaittu = {r, g, b};
		if(variEroPyth(havaittu, vari) < 60) {
			return true;
					
			}
		return false;
	}
	public static int variEroPyth(int[] a, int[] b) {
		int tulos = 0;
		for (int i = 0; i < a.length; i++) {
			tulos = tulos + (a[i] - b[i]) * (a[i] - b[i]);
		}
		return (int)Math.sqrt(tulos);
	}
}
