package org.jugendhackt.magicalunicorndisplay;

import org.jugendhackt.magicalunicorndisplay.gui.MagicWindow;

public class Main {
	public static void main(String[] args) {
		if (args.length == 0) {
			openWin(0);
		} else {
			openWin(new Integer (args[0]));
		}
		
	}
	
	private static void openWin (int mon) {
		MagicWindow mwin = new MagicWindow (mon); // Create Window
		mwin.run();
	}
}
