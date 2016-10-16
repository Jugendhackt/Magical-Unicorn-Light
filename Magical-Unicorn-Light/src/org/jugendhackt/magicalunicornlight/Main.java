package org.jugendhackt.magicalunicornlight;

import org.jugendhackt.magicalunicornlight.audio.input.IAudioReader;
import org.jugendhackt.magicalunicornlight.audio.input.MusikImport;
import org.jugendhackt.magicalunicornlight.audio.processor.HeightFilter;
import org.jugendhackt.magicalunicornlight.audio.processor.MaxFilter;
import org.jugendhackt.magicalunicornlight.audio.visualiser.SimpleScreenVisualiser;

public class Main {

	static IAudioReader reader;
	
	public static void main(String[] args) {
		reader = new MusikImport();
		
		setUpReader (reader);
		
		reader.musikImport();
	}
	
	private static void setUpReader (IAudioReader reader) {
		MaxFilter mf = new MaxFilter ();
		mf.addVisualiser(new SimpleScreenVisualiser());
		reader.addCallback(mf);
	} 
	
//	public static void test () {
//		Random rand = new Random();
//		
//		MagicSender output = new MagicSender ();
//		BufferedDMXSender dmxOutput = new BufferedDMXSender ();
//		try {
//			output.openPort();
//			output.setAddress("100.100.218.77");
//			output.setPort(1337);
//			
//			dmxOutput.openPort();
//			dmxOutput.setAddress("127.0.0.1");
//			dmxOutput.setPort(5555);
//			DMXFrame frame = new DMXFrame ();
////			frame.setChannelValue(101, 255);
////			int i = 0;
//			while (true) {
//				frame.setChannelValueDouble(10, 1);
//				frame.setChannelValueDouble(11, 0);
//				frame.setChannelValueDouble(12, 1);
//				output.sendFrame(frame);
//				
//				frame.setChannelValueDouble(101, 1);
//				frame.setChannelValueDouble(102, 0);
//				frame.setChannelValueDouble(103, 1);
//				
//				dmxOutput.sendFrame(frame);
//				
//				Thread.sleep(500);
//				System.out.println("Sending to " + output.getAddress().toString() + ":" + output.getPort());
////				i++;
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} finally {
//			output.closePort();
//		}
//	}
//	
//	public static void testMagicSender () {
//		Random rand = new Random();
//		
//		MagicSender output = new MagicSender ();
//		try {
//			output.openPort();
//			output.setAddress("100.100.246.62");
//			output.setPort(1337);
//			DMXFrame frame = new DMXFrame ();
////			frame.setChannelValue(101, 255);
////			int i = 0;
//			while (true) {
//				frame.setChannelValueDouble(1, rand.nextDouble());
//				frame.setChannelValueDouble(2, rand.nextDouble());
//				frame.setChannelValueDouble(3, rand.nextDouble());
//				output.sendFrame(frame);
//				
//				frame.setChannelValueDouble(101, rand.nextDouble());
//				frame.setChannelValueDouble(102, rand.nextDouble());
//				frame.setChannelValueDouble(103, rand.nextDouble());
////				dmxOutput.sendFrame(frame);
//				
//				Thread.sleep(500);
//				System.out.println("Sending to " + output.getAddress().toString() + ":" + output.getPort());
////				i++;
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} finally {
//			output.closePort();
//		}
//	}
//	
//	public static void testDMX2 () {
//		Random rand = new Random();
//		
//		BufferedDMXSender output = new BufferedDMXSender ();
//		try {
//			output.openPort();
//			output.setAddress("127.0.0.1");
//			output.setPort(5555);
//			DMXFrame frame = new DMXFrame ();
////			frame.setChannelValue(101, 255);
////			int i = 0;
//			while (true) {
//				frame.setChannelValueDouble(101, rand.nextDouble());
//				frame.setChannelValueDouble(102, rand.nextDouble());
//				frame.setChannelValueDouble(103, rand.nextDouble());
//				output.sendFrame(frame);
//				Thread.sleep(500);
//				System.out.println("Sending to " + output.getAddress().toString() + ":" + output.getPort());
////				i++;
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} finally {
//			output.closePort();
//		}
//	}
}
