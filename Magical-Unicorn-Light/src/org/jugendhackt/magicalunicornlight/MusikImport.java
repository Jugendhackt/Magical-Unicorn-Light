package org.jugendhackt.magicalunicornlight;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class MusikImport {
	static void musikImport() {
		
	FileChooser chooser = new FileChooser();
	String filePath = chooser.popupFileLocation();
	try {
		
	    AudioInputStream input = AudioSystem.getAudioInputStream(new File(filePath));
		
		System.out.println(input.getFormat().toString());
	    SourceDataLine line = AudioSystem.getSourceDataLine(input.getFormat());
	    line.open(input.getFormat());
	    line.start();
	    byte[] buffer = new byte[1024];
	    int count;
	    while((count = input.read(buffer, 0, 1024)) != -1) {
	        line.write(buffer, 0, count);
	        
	        
	        for(int i = 0;i < 512;i++) {
	        	int ii = i * 2;
	        	int e = buffer[i] & 0xFF;
		        int f = buffer[ii];
	        	int gesamt = e + (256 * f);
	        	System.out.println("" + (e + (256 * f)));
	        }
	        
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
		System.out.println("Jetzt kommt die Cola... (und eine menge schlechte Wortwitze und zitate)");
	}
}
