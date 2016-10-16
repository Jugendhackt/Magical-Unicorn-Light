package org.jugendhackt.magicalunicornlight.audio.processor;


public class MaxFilter extends BaseFilter{

	@Override
	public void process(int[] data) {
		double sum = 0;
		
		for(int in : data) {
			sum = calc(sum,in);
		}
		double rms = Math.sqrt(sum/data.length);
		
		
		double factor = rms/this.getMax();
		System.out.println(getMax());
		
		
		this.invokeCallbacks(data, factor, 0, 0);
		
		
	
	}
	
	public double calc (double sum, int input) {
		return sum += Math.abs(input)*Math.abs(input); 

	}

}
