package org.jugendhackt.magicalunicornlight.audio.processor;


public class MaxFilter extends BaseFilter{

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.processor.BaseFilter#process(int[])
	 */
	@Override
	public void process(int[] data) {
		double sum = 0;
		
		for(int in : data) {
			sum = calc(sum,in);
		}
		double rms = Math.sqrt(sum/data.length);
		
		
		double factor = rms/this.getMax();
		factor= factor*10 -5;
		System.out.println(factor);
		
		
		super.invokeCallbacks(data, factor, 0, 0);
		
		
	
	}
	
	private double calc (double sum, int input) {
		return sum += Math.abs(input)*Math.abs(input); 
	}

}
