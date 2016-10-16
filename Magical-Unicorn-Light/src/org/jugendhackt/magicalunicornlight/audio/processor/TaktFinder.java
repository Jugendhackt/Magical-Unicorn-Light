package org.jugendhackt.magicalunicornlight.audio.processor;

import org.jugendhackt.magicalunicornlight.Complex;
import org.jugendhackt.magicalunicornlight.FFT;

public class TaktFinder extends BaseFilter {
	
	public double[] mainvolume = new double[512];
	public double[] maxvolume = new double[512];
	
	@Override
	public void process (int[] data) {
		Complex[] komplex = new Complex[data.length];
		for(int i = 0;i < komplex.length;i++) {
			komplex[i] = new Complex(data[i],0);
		}
		process(FFT.fft(komplex));
		
		this.invokeCallbacks(data, 0, 0, 0);
	}
	
	public void process (Complex[] volume) {
		
		for(int i = 0;i < 512;i++){
			if(i == 0){
				System.out.println("Volume: " + volume[i].abs());	
			}
			
			
			mainvolume[i] = (0.995 * mainvolume[i]) + (0.005 * volume[i].abs());
			if(volume[i].abs() > maxvolume[i]){
				maxvolume[i] = volume[i].abs();
			}
			if(i == 0){
				System.out.println("MainVColume: " + mainvolume[i]);
				System.out.println("MaxVolume: " + maxvolume[i]);
			}
	
			
		}
	}
}
