package org.jugendhackt.magicalunicornlight.audio.utils;

public class AudioMath {
	
	public static int getMinValue (int bits) {
		if (bits <= 8) {
			return Byte.MIN_VALUE;
		} else if (bits <= 16) {
			return Short.MIN_VALUE;
		} else {
			return Integer.MIN_VALUE;
		}
	}
	
	public static int getMaxValue (int bits) {
		if (bits <= 8) {
			return Byte.MAX_VALUE;
		} else if (bits <= 16) {
			return Short.MAX_VALUE;
		} else {
			return Integer.MAX_VALUE;
		}
	}
}
