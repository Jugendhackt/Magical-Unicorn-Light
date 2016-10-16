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
	
	@Override
	public void addCallback(IAudioProcessor proccessor) {
		proccessor.setBitDepth(bits);
		procs.add(proccessor);
	}

	@Override
	public void removeCallback(IAudioProcessor proccessor) {
		procs.remove(proccessor);
	}

	@Override
	public void clearCallbacks() {
		procs.clear();
	}

	@Override
	public void addVisualiser(IVisualiser visualiser) {
		visualiser.initialise();
		visualisers.add(visualiser);
	}

	@Override
	public void removeVisualiser(IVisualiser visualiser) {
		visualiser.destroy();
		visualisers.remove(visualiser);
	}

	@Override
	public void clearVisualiser() {
		visualisers.clear();
	}

	@Override
	public void setBitDepth(int depth) {
		bits = depth;
	}

	@Override
	public int getBitDepth() {
		return bits;
	}

	@Override
	public abstract void process(int[] data);

	protected void invokeCallbacks (int[] data, double r, double g, double b) {
		for (IAudioProcessor proc : procs) {
			proc.process(data);
		}
		
		for (IVisualiser vis : visualisers) {
			vis.render(r, g, b);
		}
	}
	
	protected int getMin () {
		return AudioMath.getMinValue(bits);
	}
	
	protected int getMax () {
		return AudioMath.getMaxValue(bits);
	}
}
