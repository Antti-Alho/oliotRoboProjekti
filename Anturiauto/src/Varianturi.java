import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import java.math.*;

public class Varianturi {

		public static void main(String[] args) {
			
			int[][] vertailuarvot = talennaVarit(3);
			
			Port port = LocalEV3.get().getPort("S1");	//mistä portista
			SensorModes sensor = new EV3ColorSensor(port);	//mitä siinä portissa on
			SampleProvider colorProvider = ((EV3ColorSensor)sensor).getRGBMode();	//mitä se anturi tekee
			float[] sample = new float[colorProvider.sampleSize()];	// tallennetaan anturin ottama väriarvo talteen
			
			//luetaan värejä kokoajan
			while (true) {
				
				//laitetaan lamppu päälle
				((EV3ColorSensor)sensor).setFloodlight(Color.WHITE);
				
				//luetaan värit
				colorProvider.fetchSample(sample, 0);
				
				//muutetaan väriarvot 0-255 luokkaan
				int r = Math.round(sample[0]*765);
				int g = Math.round(sample[1]*765);
				int b = Math.round(sample[2]*765);
				
				//tulostetaan saatujen värien arvot
				System.out.println(r + " " + g + " " + b);
				
				//
				
				Delay.msDelay(1000);
			}
		}
		
		public static int[] listaaErot(int[] vari, int[][] vertailuarvot) {
			int[] tulos = new int[vari.length];
			for (int i = 0; i < vertailuarvot.length; i++) {
				for (int j = 0; j < vari.length; j++) {
					tulos[i] = tulos[i] + vari[j] - vertailuarvot[i][j] * vari[j] - vertailuarvot[i][j];
				}
				tulos[i] = (int) Math.sqrt(tulos[i]);
			}
			return tulos;
		}
		
		public static int[][] talennaVarit(int maara) {
			int[][] tulos = new int[maara][maara];
			
			return tulos;
		}
		
		public static int variEro(int[] a, int[] b) {
			int tulos = 0;
			for (int i = 0; i < a.length; i++) {
				if (a[i]-b[i] >= tulos) {
					tulos = a[i] - b[i];
				}
			}
			return tulos;
		}
		
		public static int variEroPyth(int[] a, int[] b) {
			int tulos = 0;
			for (int i = 0; i < a.length; i++) {
				tulos = tulos + a[i] - b[i] * a[i] - b[i];
			}
			return tulos;
		}
}
