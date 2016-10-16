package org.jugendhackt.magicalunicornlight;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import org.jugendhackt.magicalunicornlight.IO.dmx.BufferedDMXSender;
import org.jugendhackt.magicalunicornlight.IO.magicunicorndisplay.MagicSender;
import org.jugendhackt.magicalunicornlight.frames.DMXFrame;
import org.jugendhackt.magicalunicornlight.frames.MagicFrame;

public class MusikImport_copy {
	BufferedDMXSender output;
	
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
	    int limit = 0;
	    int maximum_value = 0;
        
	    while((count = input.read(buffer, 0, 1024)) != -1) {
	        line.write(buffer, 0, count);
	        
	
	        for(int i = 0;i < 512;i++) {
	        	int ii = i * 2;
	        	int e = buffer[i] & 0xFF;
		        int f = buffer[ii];
	        	int gesamt = e + (256 * f);
	        	if(gesamt>maximum_value){
		        	maximum_value=gesamt; 
		        } 
		        
	        		
	        }
	    
	        
	        limit++;
	        if (limit>3){
	        	System.out.println(maximum_value);
	        	double red = calculateColor(maximum_value);
	        	sendDisplay(red,0,0);
//	        	sendDMX(red,0,0);
	        	
	        	maximum_value=0;
	        	limit=0;
	        }
	        
	    }
	    line.drain();
	    line.stop();
	    line.close();
	} catch(Exception e) {
	    e.printStackTrace();
	}
	}
	
	
	
	public static double calculateColor(int max){
		double transparency= 0 ;   
		/*if (max>30000){
			transparency=((max-30000)/6000.0)+0.5;
		}
		else if(max != 0){
			transparency= 0.5*(max/30000.0);
		}*/
		if (max>30000){
			transparency=((max-32000)/1000.0);
		}
		
//		System.out.println(transparency);
		return transparency;
		
	}

	
	private static void sendDisplay(double r,double g, double b) {
		
		MagicSender output = new MagicSender ();
		try {
			output.openPort();
			output.setAddress("10.208.192.178");
			output.setPort(1337);
			MagicFrame frame = new MagicFrame ();
//			frame.setChannelValue(101, 255);
//			int i = 0;

			frame.setColor(r, b, g);
			output.sendFrame(frame);
//				
//			System.out.println("Sending to " + output.getAddress().toString() + ":" + output.getPort());

/*		} catch (InterruptedException e) {
			e.printStackTrace();*/
		} finally {
			output.closePort();
		}
	}
	
	public static void sendDMX(double r,double g, double b) {
		
		BufferedDMXSender output = new BufferedDMXSender ();
		try {
			output.openPort();
			output.setAddress("10.208.192.178");
			output.setPort(5555);
			DMXFrame frame = new DMXFrame ();
//			frame.setChannelValue(101, 255);
//			int i = 0;
	
			frame.setChannelValueDouble(100, r);
			frame.setChannelValueDouble(101, g);
			frame.setChannelValueDouble(102, b);
			frame.setChannelValueDouble(9, r);
			frame.setChannelValueDouble(10, r);  //g !!!!
			frame.setChannelValueDouble(11, b);
			output.sendFrame(frame);

			System.out.println("Sending to " + output.getAddress().toString() + ":" + output.getPort());
//				i++;
		
		
		} finally {
			output.closePort();
		}
	}
	public static void main(String[] args) {
		musikImport();
		System.out.println("Ihr Veggie-Burger ist fertig!");
		System.out.println("Jetzt kommt die Cola... (und eine menge schlechte Wortwitze und zitate)");
	}
}
