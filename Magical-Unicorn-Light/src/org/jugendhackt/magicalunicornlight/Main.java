package org.jugendhackt.magicalunicornlight;

import java.util.Random;

import org.jugendhackt.magicalunicornlight.IO.dmx.BufferedSerialDMXOutput;
import org.jugendhackt.magicalunicornlight.IO.dmx.SerialDMXInterfaces;
import org.jugendhackt.magicalunicornlight.IO.magicunicorndisplay.MagicSender;
import org.jugendhackt.magicalunicornlight.frames.DMXFrame;
import org.jugendhackt.magicalunicornlight.frames.MagicFrame;

public class Main {

	public static void main(String[] args) {
		testMagicSender ();
		
	}
	
	public static void testMagicSender () {
		Random rand = new Random();
		
		MagicSender output = new MagicSender ();
		try {
			output.openPort();
			output.setAddress("100.100.246.62");
			output.setPort(1337);
			MagicFrame frame = new MagicFrame ();
//			frame.setChannelValue(101, 255);
//			int i = 0;
			while (true) {
				frame.setColor(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
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
	
	public static void testDMX () {
		BufferedSerialDMXOutput output = new BufferedSerialDMXOutput(SerialDMXInterfaces.DMX4ALL_NANODMX, "/dev/ttyS81");
		
		try {
			output.openPort();
		
			byte[] data = new byte[512];
			
			for (int i = 0; i < 512; i++) {
				data[i] = (byte)255;
			}
			
			DMXFrame frame = new DMXFrame ();
			frame.setData(data);
//			frame.setChannelValue(101, 255);
			int i = 0;
			while (i < 3) {
				output.sendFrame(frame);
				Thread.sleep(1000);
				System.out.println("Sending ...");
				i++;
			}
		
			System.out.println("Finished ...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			output.closePort();
		}
	}

}
