package org.jugendhackt.magicalunicornlight.audio.visualiser;

import org.jugendhackt.magicalunicornlight.IO.magicunicorndisplay.MagicSender;
import org.jugendhackt.magicalunicornlight.frames.MagicFrame;

public class SimpleScreenVisualiser implements IVisualiser{

	protected MagicSender sender;
	
	/**
	 * IP Address of the target Screen
	 */
	public String address;
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.visualiser.IVisualiser#render(double, double, double)
	 */
	@Override
	public void render(double r, double g, double b) {
		MagicFrame frame = new MagicFrame ();
		frame.setColor(r, g, b);
		sender.sendFrame(frame);
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.visualiser.IVisualiser#initialise()
	 */
	@Override
	public void initialise() {
		sender = new MagicSender();
		sender.openPort();
		sender.setPort(1337);
		sender.setAddress(address);
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.visualiser.IVisualiser#destroy()
	 */
	@Override
	public void destroy() {
		sender.closePort();
	}
	
}
