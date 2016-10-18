package org.jugendhackt.magicalunicornlight.audio.visualiser;

import org.jugendhackt.magicalunicornlight.IO.magicunicorndisplay.MagicSender;
import org.jugendhackt.magicalunicornlight.frames.MagicFrame;

public class SimpleScreenVisualiser implements IVisualiser{

	MagicSender sender;
	
	public String address;
	
	@Override
	public void render(double r, double g, double b) {
		MagicFrame frame = new MagicFrame ();
		frame.setColor(r, g, b);
		sender.sendFrame(frame);
	}

	@Override
	public void initialise() {
		sender = new MagicSender();
		sender.openPort();
		sender.setPort(1337);
		sender.setAddress(address);
	}

	@Override
	public void destroy() {
		sender.closePort();
	}
	
}
