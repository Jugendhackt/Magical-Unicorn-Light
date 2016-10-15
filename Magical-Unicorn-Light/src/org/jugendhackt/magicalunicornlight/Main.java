package org.jugendhackt.magicalunicornlight;

import org.jugendhackt.magicalunicornlight.IO.dmx.BufferedSerialDMXOutput;
import org.jugendhackt.magicalunicornlight.IO.dmx.SerialDMXInterfaces;
import org.jugendhackt.magicalunicornlight.frames.DMXFrame;

public class Main {

	public static void main(String[] args) {
		testDMX ();
		
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
