package org.jugendhackt.magicalunicornlight.IO;

import org.jugendhackt.magicalunicornlight.frames.IFrame;


public interface ISender {
	
	public void openPort ();
	public void closePort ();
	
	public void sendFrame (IFrame data);
	public boolean isOpen ();
}
