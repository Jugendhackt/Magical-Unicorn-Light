package org.jugendhackt.magicalunicornlight;

import org.jugendhackt.magicalunicornlight.audio.input.IAudioReader;
import org.jugendhackt.magicalunicornlight.audio.input.MusikImport;
import org.jugendhackt.magicalunicornlight.audio.processor.*;
import org.jugendhackt.magicalunicornlight.audio.visualiser.ColorChangingDMXVisualiser;
import org.jugendhackt.magicalunicornlight.audio.visualiser.ColorChangingScreenVisualiser;

public class Main {

	static String IP1 = "10.23.1.36";
	static String IP2 = "10.23.0.72";
	
	static IAudioReader reader;
	
	public static void main(String[] args) {
		reader = new MusikImport();
		
		setUpReader (reader);
		
		reader.musikImport();
	}
	
	private static void setUpReader (IAudioReader reader) {
		TaktFinder hf = new TaktFinder ();
		
		ColorChangingDMXVisualiser vis = new ColorChangingDMXVisualiser();
		vis.baseChannel = 101;
		
		TaktFinder tf = new TaktFinder ();
		tf.dVolume = 4;
		
		ColorChangingDMXVisualiser vis2 = new ColorChangingDMXVisualiser();
		vis2.baseChannel = 10;
		
		hf.addVisualiser(vis);
		reader.addCallback(hf);
		
		tf.addVisualiser(vis2);
		reader.addCallback(tf);
		
		MaxFilter mf = new MaxFilter();
		
		ColorChangingScreenVisualiser sv1 = new ColorChangingScreenVisualiser();
		sv1.address = IP1;
		
		ColorChangingScreenVisualiser sv2 = new ColorChangingScreenVisualiser();
		sv2.address = IP2;
		
		mf.addVisualiser(sv1);
		mf.addVisualiser(sv2);
		reader.addCallback(mf);
	}
}



    
    
    
    
    
    
    
    
    
    
