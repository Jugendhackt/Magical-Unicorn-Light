package org.jugendhackt.magicalunicornlight.audio.processor;

import org.jugendhackt.magicalunicornlight.audio.visualiser.IVisualiser;

public interface IAudioProcessor {
	
	public void addCallback (IAudioProcessor proccessor);
	public void removeCallback (IAudioProcessor proccessor);
	public void clearCallbacks ();
	
	public void addVisualiser (IVisualiser visualiser);
	public void removeVisualiser (IVisualiser visualiser);
	public void clearVisualiser ();
	
	public void setBitDepth (int depth);
	public int getBitDepth ();
	
	public void process (int[] data);
}
