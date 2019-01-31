import java.util.Arrays;

import org.jfree.chart.axis.StandardTickUnitSource;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.RegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.*;
import lejos.utility.Delay;
import lejos.hardware.Sound;

public class VariAnturi {

		public static void main(String[] args) {

			Port port = LocalEV3.get().getPort("S1");	//mistä portista
			SensorModes sensor = new EV3ColorSensor(port);	//mitä siinä portissa on
			SampleProvider colorProvider = ((EV3ColorSensor)sensor).getRGBMode();	//mitä se anturi tekee
			float[] sample = new float[colorProvider.sampleSize()];	// tallennetaan anturin ottama väriarvo talteen
			
			int[] vanha = {0, 0, 0};
			
			EV3LargeRegulatedMotor moottoriB= new EV3LargeRegulatedMotor(MotorPort.B);
			EV3LargeRegulatedMotor moottoriC= new EV3LargeRegulatedMotor(MotorPort.C);
			moottoriB.synchronizeWith(new RegulatedMotor[] {moottoriC});
			
			Port nelos = LocalEV3.get().getPort("S4");
	        SensorModes infra = new EV3IRSensor(nelos);
	        SampleProvider etä = ((EV3IRSensor)infra).getDistanceMode();
	        float[] infrapuna = new float[etä.sampleSize()];
			
			int eka = 0;
			int toka = 0;
			int pakki = 0;
			
			Sound.beep();
			int[][] vertailuarvot = talennaVarit(3, sample, colorProvider, sensor);
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(vertailuarvot[i][j]+",");
				}
				System.out.println("");
			}
			
			Sound.beep();
			System.out.println("Lahtolaskenta:");
			for ( int l = 5 ; l > 0 ; l-- ) {
				System.out.println(l);
				Delay.msDelay(1000);
			}
			System.out.println("Liikkeelle!");
			
			moottoriB.setSpeed(300);
			moottoriC.setSpeed(300);
			moottoriB.forward();
			moottoriC.forward();
			
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
				
				//System.out.println(r+" "+g+" "+b);
				
				int[] nyky = {r, g, b};
				if(variEroPyth(nyky, vertailuarvot[1]) < 40) {

					Sound.beep();
					eka++;
					if (eka >= 2 && toka >= 2) {
						System.out.println("Stop!");
						moottoriB.stop(true);
						moottoriC.stop(true);
						moottoriB.close();
						moottoriC.close();
					}
				}
				
				if(variEroPyth(nyky, vertailuarvot[2]) < 40) {
					Sound.beep();
					toka++;
				}
				
				etä.fetchSample(infrapuna, 0);
				if ( infrapuna[0] <= 10) {
					if ( pakki == 0 ) {
						pakki++;
						System.out.println("Pakki paalle.");
					}
					moottoriB.backward();
					moottoriC.backward();
				}
				
				Delay.msDelay(50);
				
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
		
		public static int[][] talennaVarit(int maara, float[] sample, SampleProvider colorProvider, SensorModes sensor) {
			int[][] tulos = new int[maara][maara];
			int[] vanha = {0, 0, 0};
			
			for (int k = 0 ; k < 3 ; k++) {
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
							tulos[k][j] = uusi[j];
						}
					
						tulos[k] = uusi;
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
			return tulos;
		}
		
		public static int variEroPyth(int[] a, int[] b) {
			int tulos = 0;
			for (int i = 0; i < a.length; i++) {
				tulos = tulos + (a[i] - b[i]) * (a[i] - b[i]);
			}
			return (int)Math.sqrt(tulos);
		}
}
