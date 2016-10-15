package org.jugendhackt.magicalunicornlight;

import java.util.Random;

import org.jugendhackt.magicalunicornlight.IO.dmx.BufferedDMXSender;
import org.jugendhackt.magicalunicornlight.IO.magicunicorndisplay.MagicSender;
import org.jugendhackt.magicalunicornlight.frames.DMXFrame;
import org.jugendhackt.magicalunicornlight.frames.MagicFrame;

public class Main {

	public static void main(String[] args) {
		test ();
	}
	
	public static void test () {
		Random rand = new Random();
		
		MagicSender output = new MagicSender ();
		BufferedDMXSender dmxOutput = new BufferedDMXSender ();
		try {
			output.openPort();

			output.setAddress("100.100.219.84");

			output.setAddress("100.100.218.77");
			output.setPort(1337);
			
			dmxOutput.openPort();
			dmxOutput.setAddress("127.0.0.1");
			dmxOutput.setPort(5555);
			DMXFrame frame = new DMXFrame ();
//			frame.setChannelValue(101, 255);
//			int i = 0;
			while (true) {
				frame.setChannelValueDouble(1, rand.nextDouble());
				frame.setChannelValueDouble(2, rand.nextDouble());
				frame.setChannelValueDouble(3, rand.nextDouble());
				output.sendFrame(frame);
				
				frame.setChannelValueByte(101, frame.getChannelValue(1));
				frame.setChannelValueByte(102, frame.getChannelValue(2));
				frame.setChannelValueByte(103, frame.getChannelValue(3));
				
				dmxOutput.sendFrame(frame);
				
				Thread.sleep(500);
				System.out.println("Sending to " + output.getAddress().toString() + ":" + output.getPort());
//				i++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			output.closePort();
		}
	}
	
	public static void testMagicSender () {
		Random rand = new Random();
		
		MagicSender output = new MagicSender ();
		try {
			output.openPort();
			output.setAddress("100.100.246.62");
			output.setPort(1337);
			DMXFrame frame = new DMXFrame ();
//			frame.setChannelValue(101, 255);
//			int i = 0;
			while (true) {
				frame.setChannelValueDouble(1, rand.nextDouble());
				frame.setChannelValueDouble(2, rand.nextDouble());
				frame.setChannelValueDouble(3, rand.nextDouble());
				output.sendFrame(frame);
				
				frame.setChannelValueDouble(101, rand.nextDouble());
				frame.setChannelValueDouble(102, rand.nextDouble());
				frame.setChannelValueDouble(103, rand.nextDouble());
//				dmxOutput.sendFrame(frame);
				
				Thread.sleep(500);
				System.out.println("Sending to " + output.getAddress().toString() + ":" + output.getPort());
//				i++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			output.closePort();
		}
	}
	
	public static void testDMX2 () {
		Random rand = new Random();
		
		BufferedDMXSender output = new BufferedDMXSender ();
		try {
			output.openPort();
			output.setAddress("127.0.0.1");
			output.setPort(5555);
			DMXFrame frame = new DMXFrame ();
//			frame.setChannelValue(101, 255);
//			int i = 0;
			while (true) {
				frame.setChannelValueDouble(101, rand.nextDouble());
				frame.setChannelValueDouble(102, rand.nextDouble());
				frame.setChannelValueDouble(103, rand.nextDouble());
				output.sendFrame(frame);
				Thread.sleep(500);
				System.out.println("Sending to " + output.getAddress().toString() + ":" + output.getPort());
//				i++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			output.closePort();
		}
	}
}
