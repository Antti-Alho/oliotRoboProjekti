package nike;

import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.port.PortException;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class VariAnturi{

	private Port port = LocalEV3.get().getPort("S1");	//mistä portista
	private SensorModes sensor;
	private SampleProvider colorProvider = ((EV3ColorSensor)sensor).getRGBMode();	//mitä se anturi tekee
	private float[] sample = new float[colorProvider.sampleSize()];	// tallennetaan anturin ottama väriarvo talteen
	private int[] vari = {205, 45, 21};
	int[] tulos;

	public VariAnturi() {
		while (sensor == null) {
			try {
				sensor = new EV3ColorSensor(port);
			} catch (PortException e) {
				System.out.println("laita Vari anturi kiinni");
			}
		}
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
	
	public void setVari(int[] vari) {
		this.vari = vari;
	}
	
	public void talennaVari() {
		int[] vanha = {0, 0, 0};
		while(true) {
			((EV3ColorSensor)sensor).setFloodlight(Color.WHITE);
			
			//luetaan värit
			colorProvider.fetchSample(sample, 0);
			
			//muutetaan väriarvot 0-255 luokkaan
			int r = Math.round(sample[0]*765);
			int g = Math.round(sample[1]*765);
			int b = Math.round(sample[2]*765);
			
			int[] uusi = {r, g, b};
			if(variEroPyth(uusi, vanha) > 30) {

				for (int j = 0; j < uusi.length; j++) {
					vari[j] = uusi[j];
				}
				System.out.println(variEroPyth(uusi, vanha));
				vanha = uusi;
				break;
			}	else {
				vanha=uusi;
			}
			Sound.beep();
			Delay.msDelay(3000);
		}
	}
	
	public SensorModes getSensor() {
		return sensor;
	}
}
