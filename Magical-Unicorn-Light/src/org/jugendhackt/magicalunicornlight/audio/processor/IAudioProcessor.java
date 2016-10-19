package org.jugendhackt.magicalunicornlight.audio.processor;

import org.jugendhackt.magicalunicornlight.audio.visualiser.IVisualiser;

public interface IAudioProcessor {
	
	/**
	 * Add AudioProcessor <b>processor</b> to this AudioProcessor's callback list. 
	 * 
	 * @param proccessor
	 */
	public void addCallback (IAudioProcessor proccessor);
	/**
	 * Remove AudioProcessor <b>processor</b> from this AudioProcessor's callback list. 
	 * 
	 * @param proccessor
	 */
	public void removeCallback (IAudioProcessor proccessor);
	/**
	 * Remove all AudioProcessor's from this AudioProcessor.
	 */
	public void clearCallbacks ();
	
	/**
	 * Add Visualiser <b>visualiser</b> to this AudioProcessor's callback list.
	 * 
	 * @param visualiser
	 */
	public void addVisualiser (IVisualiser visualiser);
	/**
	 * Remove Visualiser <b>visualiser</b> from this AudioProcessor's callback list.
	 * 
	 * @param visualiser
	 */
	public void removeVisualiser (IVisualiser visualiser);
	/**
	 * Remove all Visualisers from this AudioProcessor.
	 */
	public void clearVisualiser ();
	
	/**
	 * Set BitDepth of audio clip
	 * 
	 * @param depth - Audio Bitrate
	 */
	public void setBitDepth (int depth);
	/**
	 * @return Bitrate of this audiodata
	 */
	public int getBitDepth ();
	
	/**
	 * Processes 
	 * 
	 * @param data
	 */
	public void process (int[] data);
}
