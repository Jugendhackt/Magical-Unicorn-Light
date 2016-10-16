package org.jugendhackt.magicalunicornlight;

import org.jugendhackt.magicalunicornlight.audio.input.IAudioReader;
import org.jugendhackt.magicalunicornlight.audio.input.MusikImport;
import org.jugendhackt.magicalunicornlight.audio.processor.HeightFilter;
import org.jugendhackt.magicalunicornlight.audio.visualiser.SimpleScreenVisualiser;

public class Main {

	static IAudioReader reader;
	
	public static void main(String[] args) {
		reader = new MusikImport();
		
		setUpReader (reader);
		
		reader.musikImport();
	}
	
	private static void setUpReader (IAudioReader reader) {
		HeightFilter hf = new HeightFilter ();
		hf.addVisualiser(new SimpleScreenVisualiser());
		reader.addCallback(hf);
	}
}
