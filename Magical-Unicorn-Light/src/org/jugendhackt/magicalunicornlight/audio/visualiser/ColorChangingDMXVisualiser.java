package org.jugendhackt.magicalunicornlight.audio.visualiser;

import java.util.Random;

import org.jugendhackt.magicalunicornlight.frames.DMXFrame;

public class ColorChangingDMXVisualiser extends SimpleDMXVisualiser {
	Random rand;
	
	@Override
	public void initialise() {
		super.initialise();
		rand = new Random();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void render(double r, double g, double b) {
		DMXFrame frame = new DMXFrame ();
		
		frame.setChannelValueDouble(baseChannel+0, rand.nextDouble());
		frame.setChannelValueDouble(baseChannel+1, rand.nextDouble());
		frame.setChannelValueDouble(baseChannel+2, rand.nextDouble());
		frame.setChannelValueDouble(baseChannel+3, rand.nextDouble());
		
		sender.sendFrame(frame);
	}
}
