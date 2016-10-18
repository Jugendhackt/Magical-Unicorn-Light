package org.jugendhackt.magicalunicornlight.audio.visualiser;

import org.jugendhackt.magicalunicornlight.IO.dmx.BufferedDMXSender;
import org.jugendhackt.magicalunicornlight.IO.dmx.DMXSender;
import org.jugendhackt.magicalunicornlight.frames.DMXFrame;

public class SimpleDMXVisualiser implements IVisualiser {
	DMXSender sender;
	
	public int baseChannel;
	
	@Override
	public void render(double r, double g, double b) {
		DMXFrame frame = new DMXFrame ();
		
		frame.setChannelValueDouble(baseChannel+0, r);
		frame.setChannelValueDouble(baseChannel+1, g);
		frame.setChannelValueDouble(baseChannel+2, b);
		
		sender.sendFrame(frame);
	}

	@Override
	public void initialise() {
		sender = new BufferedDMXSender();
		sender.openPort();
		sender.setPort(5555);
		sender.setAddress("127.0.0.1");
	}

	@Override
	public void destroy() {
		sender.closePort();
	}
}
