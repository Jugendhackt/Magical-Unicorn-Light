package org.jugendhackt.magicalunicornlight.audio.processor;

import java.util.ArrayList;

import org.jugendhackt.magicalunicornlight.audio.utils.AudioMath;
import org.jugendhackt.magicalunicornlight.audio.visualiser.IVisualiser;

public abstract class BaseFilter implements IAudioProcessor {

	protected int bits;
	protected ArrayList<IAudioProcessor> procs;
	protected ArrayList<IVisualiser> visualisers;
	
	public BaseFilter() {
		procs = new ArrayList<>();
		visualisers = new ArrayList<>();
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor#addCallback(org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor)
	 */
	@Override
	public void addCallback(IAudioProcessor proccessor) {
		proccessor.setBitDepth(bits);
		procs.add(proccessor);
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor#removeCallback(org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor)
	 */
	@Override
	public void removeCallback(IAudioProcessor proccessor) {
		procs.remove(proccessor);
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor#clearCallbacks()
	 */
	@Override
	public void clearCallbacks() {
		procs.clear();
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor#addVisualiser(org.jugendhackt.magicalunicornlight.audio.visualiser.IVisualiser)
	 */
	@Override
	public void addVisualiser(IVisualiser visualiser) {
		visualiser.initialise();
		visualisers.add(visualiser);
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor#removeVisualiser(org.jugendhackt.magicalunicornlight.audio.visualiser.IVisualiser)
	 */
	@Override
	public void removeVisualiser(IVisualiser visualiser) {
		visualiser.destroy();
		visualisers.remove(visualiser);
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor#clearVisualiser()
	 */
	@Override
	public void clearVisualiser() {
		visualisers.clear();
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor#setBitDepth(int)
	 */
	@Override
	public void setBitDepth(int depth) {
		bits = depth;
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor#getBitDepth()
	 */
	@Override
	public int getBitDepth() {
		return bits;
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor#process(int[])
	 */
	@Override
	public abstract void process(int[] data);

	/**
	 * Method to invoke all callbacks of this Filter. First, all AudioProcessors are Invoked, then all Visualisers are Invoked.
	 * 
	 * @param data - the data to give to the other AudioProcessors
	 * @param r - Red component of the Color for the Visualiser
	 * @param g - Green component of the Color for the Visualiser
	 * @param b - Blue component of the Color for the Visualiser
	 */
	protected void invokeCallbacks (int[] data, double r, double g, double b) {
		for (IAudioProcessor proc : procs) {
			proc.process(data);
		}
		
		for (IVisualiser vis : visualisers) {
			vis.render(r, g, b);
		}
	}
	
	/**
	 * @return Returns the absolute Minimum value possible for the Audio Samples
	 */
	protected int getMin () {
		return AudioMath.getMinValue(bits);
	}
	
	/**
	 * @return Returns the absolute Maximum value possible for the Audio Samples
	 */
	protected int getMax () {
		return AudioMath.getMaxValue(bits);
	}
}
