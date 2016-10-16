package org.jugendhackt.magicalunicornlight.audio.input;

import org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor;

public interface IAudioReader {
	
	public void addCallback (IAudioProcessor proccessor);
	public void removeCallback (IAudioProcessor proccessor);
	
	public void clearCallbacks ();
	public void musikImport();
}
