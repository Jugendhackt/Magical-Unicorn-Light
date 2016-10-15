package org.jugendhackt.magicalunicornlight;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class MusikImport {
	int k = 0;
	static double[] mainvolume = new double[512];
	static double[] maxvolume = new double[512];
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
	    Complex[] komplex = new Complex[512];
	  
	    int count;
	    while((count = input.read(buffer, 0, 1024)) != -1) {
	        line.write(buffer, 0, count);
	        
	        
	        for(int i = 0;i < 512;i++) {
	        	int ii = i * 2;
	        	int e = buffer[i] & 0xFF;
		        int f = buffer[ii];
	        	int gesamt = e + (256 * f);
	        	//System.out.println("" + (e + (256 * f)));
	        	komplex[i] = new Complex((double) gesamt,0);
	        }
	        System.out.println("Ihr Veggie-Burger ist fertig!");
    	        Complex[] ergebnis = FFT.fft(komplex);
    	        //System.out.println(ergebnis[0].abs() + "/" + ergebnis[1].abs() + "/" + ergebnis[2].abs() + "/" + ergebnis[3].abs() + "/" + ergebnis[4].abs() + "/");
    	        taktFinder(ergebnis);
	    }
	    line.drain();
	    line.stop();
	    line.close();
	} catch(Exception e) {
	    e.printStackTrace();
	}
	}
	
	static void taktFinder(Complex[] volume) {
	
		for(int i = 0;i < 512;i++){
			if(i == 0){
				System.out.println("Volume: " + volume[i].abs());	
			}
			
			
			mainvolume[i] = (0.995 * mainvolume[i]) + (0.005 * volume[i].abs());
			if(volume[i].abs() > maxvolume[i]){
				maxvolume[i] = volume[i].abs();
			}
			if(i == 0){
				System.out.println("MainVColume: " + mainvolume[i]);
				System.out.println("MaxVolume: " + maxvolume[i]);
			}
	
			
		}
	}
	
	public static void main(String[] args) {
		musikImport();
		System.out.println("Ihr Veggie-Burger ist fertig!");
		System.out.println("Jetzt kommt die Cola... (und eine menge schlechte Wortwitze und zitate)");
	}
}
