package org.jugendhackt.magicalunicornlight.audio.visualiser;

public interface IVisualiser {
	
	public void initialise ();
	public void destroy ();
	
	public void render (double r, double g, double b);
}
