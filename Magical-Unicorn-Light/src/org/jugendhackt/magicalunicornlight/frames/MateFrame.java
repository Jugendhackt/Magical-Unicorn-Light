package org.jugendhackt.magicalunicornlight.frames;

public class MateFrame implements IFrame {
	private int offset;
	private byte[] data;
	
//	private static byte tilt = (byte) 0;
	
	public static final MateFrame EMPTY = new MateFrame (new byte[1923]);
	
	// Empty Frame for another Location
//	public static final DMXFrame EMPTY = new DMXFrame (new byte[] {
//			0,0,0,(byte)255,0,0,0,0,(byte)255,0,0,0,0,(byte)255,0,0,
//			0,0,(byte)255,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,tilt,0,0,0,0,0,32,(byte)255,0,0,
//			0,0,0,tilt,0,0,0,0,0,32,(byte)255,0,0,0,0,0,
//			tilt,0,0,0,0,0,32,(byte)255,0,0,0,0,0,tilt,0,0,
//			0,0,0,32,(byte)255,0,0,0,0,0,tilt,0,0,0,0,0,
//			32,(byte)255,0,0,0,0,0,tilt,0,0,0,0,0,32,(byte)255,0,
//			0,0,0,0,tilt,0,0,0,0,0,32,(byte)255,0,0,0,0,
//			0,tilt,0,0,0,0,0,32,(byte)255,0,0,0,0,0,tilt,0,
//			0,0,0,0,32,(byte)255,0,0,0,0,0,tilt,0,0,0,0,
//			0,32,(byte)255,0,0,0,0,0,tilt,0,0,0,0,0,32,(byte)255,
//			0,0,0,0,0,tilt,0,0,0,0,0,32,(byte)255,0,0,0,
//			0,0,tilt,0,0,0,0,0,32,(byte)255,0,0,0,0,0,tilt,
//			0,0,0,0,0,32,(byte)255,0,0,0,0,0,tilt,0,0,0,
//			0,0,32,(byte)255,0,0,0,0,0,tilt,0,0,0,0,0,32,
//			(byte)255,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//		});
	
	public MateFrame () {
		offset = 0;
		data = new byte[1923];
	}
	
	public MateFrame (byte[] data) {
		offset = 0;
		this.data = data;
	}
	
	public MateFrame (byte[] data, int offset) {
		this.offset = offset;
		this.data = data;
	}
	
	public MateFrame (MateFrame frame) {
		if (frame != null) {
			this.offset = frame.offset;
			this.data = frame.data;
		} else {
			offset = 0;
			data = new byte[1923];
		}
	}
	
	@Override
	public int getOffset () {
		return offset;
	}
	
	@Override
	public void setOffset (int offset) {
		this.offset = offset;
	}
	
	@Override
	public byte[] getData () {
		return data;
	}
	
	@Override
	public void setData (byte[] data) {
		this.data = data;
	}
	
	@Override
	public byte getChannelValue (int i) {
		if ((offset + i < data.length) && (offset + i>=0)) {
			return data[offset + i];
		}
		return 0;
	}
	
	@Override
	public void clear(){
		for(int i=0;i<data.length;i++){
			data[i]=0;
		}
	}

	@Override
	public void setChannelValue(int channelNo, int value) {
		if (value < 0) {
			value = 0;
		} else if (value > 255) {
			value = 255;
		}
		data[channelNo] = (byte) value;
	}

	@Override
	public void mixChannelValue(int channelNo, int value) {
		if (value < 0) {
			value = 0;
		} else if (value > 255) {
			value = 255;
		}
		if (value > data[channelNo]) {
			data[channelNo] = (byte) value;
		}
	}

	@Override
	public void setChannelValueDouble(int channelNo, double value) {
		if (value < 0) {
			value = 0;
		} else if (value > 1) {
			value = 1;
		}
		value = value * 255;
		data[channelNo] = (byte) value;
	}

	@Override
	public void mixChannelValueDouble(int channelNo, double value) {
		if (value < 0) {
			value = 0;
		} else if (value > 1) {
			value = 1;
		}
		value = value * 255;
		byte bytevalue = (byte) value;
		if (bytevalue > data[channelNo]) {
			data[channelNo] = bytevalue;
		}
	}
	
	@Override
	public void setChannelValueByte(int channelNo, byte value) {
		data[channelNo] = value;
	}

	@Override
	public void mixChannelValueByte(int channelNo, byte value) {
		if (value > data[channelNo]) {
			data[channelNo] = value;
		}
	}

	public void setRed (int x, int y, int value) {
		setChannelValue(getChannelNoFromCoords(x, y), value);
	}
	
	public void setGreen (int x, int y, int value) {
		setChannelValue(getChannelNoFromCoords(x, y)+1, value);
	}

	public void setBlue (int x, int y, int value) {
		setChannelValue(getChannelNoFromCoords(x, y)+2, value);
	}
	
	//TODO: Check
	protected int getChannelNoFromCoords (int x, int y) {
		return y*40+x;
	}
}
