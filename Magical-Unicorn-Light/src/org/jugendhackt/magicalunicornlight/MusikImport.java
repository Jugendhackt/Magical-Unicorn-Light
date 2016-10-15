package org.jugendhackt.magicalunicornlight;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class MusikImport {
	static void musikImport() {
	try {
	    AudioInputStream input = AudioSystem.getAudioInputStream(new File("/home/jh/jh2016/Musik/Everlasting.wav"));
	    SourceDataLine line = AudioSystem.getSourceDataLine(input.getFormat());
	    line.open(input.getFormat());
	    line.start();
	    byte[] buffer = new byte[1024];
	    int count;
	    while((count = input.read(buffer, 0, 1024)) != -1) {
	        line.write(buffer, 0, count);
	        System.out.println("" + buffer);
	    }
	    line.drain();
	    line.stop();
	    line.close();
	} catch(Exception e) {
	    e.printStackTrace();
	}
	}
	public static void main(String[] args) {
		musikImport();
		System.out.println("Ihr Veggie-Burger ist fertig!");
	}
}
