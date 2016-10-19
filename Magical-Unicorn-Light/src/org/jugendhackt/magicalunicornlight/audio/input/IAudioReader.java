package org.jugendhackt.magicalunicornlight.audio.input;

import org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor;

public interface IAudioReader {
	
	/**
	 * Add AudioProcessor <b>processor</b> to this AudioReader's callback list. 
	 * 
	 * @param proccessor
	 */
	public void addCallback (IAudioProcessor proccessor);
	
	/**
	 * Remove AudioProcessor <b>processor</b> from this AudioReader's callback list. 
	 * 
	 * @param proccessor
	 */
	public void removeCallback (IAudioProcessor proccessor);
	
	/**
	 * Remove all Callbacks from this AudioReader.
	 */
	public void clearCallbacks ();
	
	/**
	 * Start reading of Audio.
	 */
	public void musikImport();
}
