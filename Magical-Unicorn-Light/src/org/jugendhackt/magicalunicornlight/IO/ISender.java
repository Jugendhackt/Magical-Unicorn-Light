package org.jugendhackt.magicalunicornlight.IO;

import org.jugendhackt.magicalunicornlight.frames.IFrame;


public interface ISender {
	
	/**
	 * Opens port. prints Exception if it fails
	 */
	public void openPort ();
	/**
	 * Closes port.
	 */
	public void closePort ();
	
	/**
	 * Writes frame to the port
	 * @param data - Data to write to the port
	 */
	public void sendFrame (IFrame data);
	/**
	 * @return true if the port is currently open
	 */
	public boolean isOpen ();
}
