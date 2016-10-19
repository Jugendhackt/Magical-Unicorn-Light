package org.jugendhackt.magicalunicornlight.audio.visualiser;

public interface IVisualiser {
	
	/**
	 * Called when Visualiser is added to the AudioProcesspr
	 */
	public void initialise ();
	
	/**
	 * Called when Visualiser is REmoved from AudioProcessor
	 */
	public void destroy ();
	
	/**
	 * Render the Result. Called By AudioProcessor when it finished Processing
	 * 
	 * @param r	- Red Component of the Color given by AudioProcessor
	 * @param g - Green Component of the Color given by AudioProcessor
	 * @param b - Blue Component of the Color given by AudioProcessor
	 */
	public void render (double r, double g, double b);
}
