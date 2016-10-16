package org.jugendhackt.magicalunicornlight;

import org.jugendhackt.magicalunicornlight.audio.input.IAudioReader;
import org.jugendhackt.magicalunicornlight.audio.input.MusikImport;
import org.jugendhackt.magicalunicornlight.audio.processor.*;
import org.jugendhackt.magicalunicornlight.audio.visualiser.ColorChangingScreenVisualiser;

public class Main {

	static IAudioReader reader;
	
	public static void main(String[] args) {
		reader = new MusikImport();
		
		setUpReader (reader);
		
		reader.musikImport();
	}
	
	private static void setUpReader (IAudioReader reader) {
		TaktFinder hf = new TaktFinder ();
		hf.addVisualiser(new ColorChangingScreenVisualiser());
		reader.addCallback(hf);
	}
}
