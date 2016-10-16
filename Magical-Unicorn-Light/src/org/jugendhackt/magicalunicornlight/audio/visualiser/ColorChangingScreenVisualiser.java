package org.jugendhackt.magicalunicornlight.audio.visualiser;

import java.util.Random;

import org.jugendhackt.magicalunicornlight.frames.MagicFrame;

public class ColorChangingScreenVisualiser extends SimpleScreenVisualiser{

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
		MagicFrame frame = new MagicFrame ();
		frame.setColor(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
		sender.sendFrame(frame);
	}
	
}
