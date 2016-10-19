package org.jugendhackt.magicalunicornlight.audio.processor;

import org.jugendhackt.magicalunicornlight.Complex;
import org.jugendhackt.magicalunicornlight.FFT;

public class TaktFinder extends BaseFilter {
	
	protected int k = 0;
	protected boolean test = false;
	protected int taktfrequenz = 150;
	protected double takt = 0;
	protected double a = System.currentTimeMillis();
	protected double b = 0;
	protected double[] mainvolume = new double[512];
	protected double[] maxvolume = new double[512];
	protected double[] lowHighDifferenz = new double[512];
	protected Complex[] volume = new Complex[512];

	public double red = 1.0;
	public double green = 1.0;
	public double blue = 1.0;
	
	public double dVolume = 3;
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.processor.BaseFilter#process(int[])
	 */
	@Override
	public void process (int[] data) {
		Complex[] komplex = new Complex[data.length];
		for(int i = 0;i < komplex.length;i++) {
			komplex[i] = new Complex(data[i],0);
		}
		frequenzFinder(FFT.fft(komplex), data);
	}
	
	private void frequenzFinder(Complex[] volume, int[] data) {
		//		System.out.println("01");
		for(int i = 0;i < 512;i++){
			if(i == 0){
				//System.out.println("Volume: " + volume[i].abs());	
			}


			mainvolume[i] = (0.995 * mainvolume[i]) + (0.005 * volume[i].abs());
			if(volume[i].abs() > maxvolume[i]){
				maxvolume[i] = volume[i].abs();
			}
			lowHighDifferenz[i] = ((0.995 * lowHighDifferenz[i]) + (0.005 * (maxvolume[i] - mainvolume[i])));
			if(lowHighDifferenz[i] > lowHighDifferenz[taktfrequenz]){
				taktfrequenz = i;
			}
			//System.out.println("Taktfrequenz: " + taktfrequenz);
			if(i == 0){
				//System.out.println("MainVColume: " + mainvolume[i]);
				//System.out.println("MaxVolume: " + maxvolume[i]);
				//System.out.println("Low-High-Differenz: " + lowHighDifferenz[i]);
			}



		}

		this.volume = volume;
		taktFinder(data);
	}

	private void taktFinder(int[] data){
		//		System.out.println("02");
		//		System.out.println(volume[taktfrequenz].abs());

		if(volume[taktfrequenz].abs() > (dVolume * mainvolume[taktfrequenz] )){
			super.invokeCallbacks(data, red, green, blue);
			a = System.currentTimeMillis();
			
			if(test){
				b = System.currentTimeMillis();
				takt = b - a;
				System.out.println(takt);
			}
			test = true;

		}
	}
	
}
