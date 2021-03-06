package org.jugendhackt.magicalunicornlight.audio.processor;

public class HeightFilter extends BaseFilter{

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.processor.BaseFilter#process(int[])
	 */
	@Override
	public void process(int[] data) {
		double factor = 0;
		
		for (int in : data) {
//			System.out.println(calcFactor(in));
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			factor += calcFactor(in);
		}
		
		factor = factor / data.length;
		
//		System.out.println(factor);
		
		super.invokeCallbacks(data, factor, factor, factor);
	}
	
	private double calcFactor (int input) {
		return Math.abs((input + this.getMin())/ (double)this.getMax()); 
	}

}
