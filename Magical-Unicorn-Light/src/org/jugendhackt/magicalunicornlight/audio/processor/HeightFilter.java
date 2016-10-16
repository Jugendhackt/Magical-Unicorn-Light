package org.jugendhackt.magicalunicornlight.audio.processor;

public class HeightFilter extends BaseFilter{

	@Override
	public void process(int[] data) {
		double factor = 0;
		for (int in : data) {
			factor += calcFactor(in);
		}
		
		factor = factor/data.length;
		
		System.out.println(factor);
		
		this.invokeCallbacks(data, factor, factor, factor);
	}
	
	public double calcFactor (int input) {
		return Math.abs((input + this.getMin())/ (double)this.getMax()); 
	}

}
