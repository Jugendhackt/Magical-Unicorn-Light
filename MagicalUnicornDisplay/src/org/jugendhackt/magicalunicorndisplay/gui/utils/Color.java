package org.jugendhackt.magicalunicorndisplay.gui.utils;


public class Color {
	public float r,g,b,a;
	
	public Color(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public Color(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 1.0f;
	}
	
	public Color(byte r, byte g, byte b, byte a) {
		this ((r&0xff)/255.0f, (g&0xff)/255.0f, (b&0xff)/255f, (a&0xff)/255f);
	}
	
	public Color(byte r, byte g, byte b) {
		this ((r&0xff)/255.0f, (g&0xff)/255.0f, (b&0xff)/255f, 1.0f);
	}
	
	public void dim (float value) {
		r = r/value;
		g = g/value;
		b = b/value;
	}
	
	public byte getR () {
		return (byte)(r * 255);
	}

	public byte getG () {
		return (byte)(g * 255);
	}

	public byte getB () {
		return (byte)(b * 255);
	}
	
	public byte getA () {
		return (byte)(a * 255);
	}
	
	public String getHex () {
		return Integer.toHexString(getR()) + Integer.toHexString(getG()) + Integer.toHexString(getB()) + Integer.toHexString(getA());
	}
	
	@Override
	public String toString () {
		return Float.toString(r) + "|" + Float.toString(g) + "|" + Float.toString(b) + "|" + Float.toString(a);
	}

	public void cutColor () {
		if (r < 0.0f) {
			r = 0.0f;
		} else if (r > 1.0f) {
			r = 1.0f;
		}
		
		if (g < 0.0f) {
			g = 0.0f;
		} else if (g > 1.0f) {
			g = 1.0f;
		}
		
		if (b < 0.0f) {
			b = 0.0f;
		} else if (b > 1.0f) {
			b = 1.0f;
		}
		
		if (a < 0.0f) {
			a = 0.0f;
		} else if (a > 1.0f) {
			a = 1.0f;
		} 
	}

	public void normaliseColor () {
		float max = 1.0f;
		float min = 0.0f;
		
		if (r < min) {
			min = r;
		} else if (g < min) {
			min = g;
		} if (b < min) {
			min = b;
		}
		
		if (r > max) {
			max = r;
		} else if (g > max) {
			max = g;
		} if (b > max) {
			max = b;
		}
		
		r = min+r/min+max;
		g = min+g/min+max;
		b = min+b/min+max;
		
		if (a > 1.0f) {
			a = 1.0f;
		} else if (a < 0f) {
			a = 0f;
		}
	}

}
