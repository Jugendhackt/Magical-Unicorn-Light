package org.jugendhackt.magicalunicornlight.frames;

/**
 * @author eric
 * Class representing an single MateLight Frame
 */
public class MateFrame implements IFrame {
	/**
	 *  offset of Start of Mate data from 0 if the frame is incomplete. Default: 0
	 */
	private int offset;
	/**
	 * The actual DMX data. Length should be 1923
	 */
	private byte[] data;
	
	/**
	 * Represents an Empty (Black) Mate Frame
	 */
	public static final MateFrame EMPTY = new MateFrame (new byte[1923]);
	
	
	/**
	 * Default Constructor
	 */
	public MateFrame () {
		offset = 0;
		data = new byte[1923];
	}
	
	/**
	 * Constructor
	 * 
	 * @param data - byte[] containing Mate Data
	 */
	public MateFrame (byte[] data) {
		offset = 0;
		this.data = data;
	}
	
	/**
	 * Constructor
	 * 
	 * @param data - byte[] containing DMX Data
	 * @param offset - int specifiing offset of data to 0 (for frames with incomplete data)
	 */
	public MateFrame (byte[] data, int offset) {
		this.offset = offset;
		this.data = data;
	}
	
	/**
	 * Constructor
	 * Clones the given MateFrame
	 * 
	 * @param frame - The frame to clone
	 */
	public MateFrame (MateFrame frame) {
		if (frame != null) {
			this.offset = frame.offset;
			this.data = frame.data;
		} else {
			offset = 0;
			data = new byte[1923];
		}
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.frames.IFrame#getOffset()
	 */
	@Override
	public int getOffset () {
		return offset;
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.frames.IFrame#setOffset(int)
	 */
	@Override
	public void setOffset (int offset) {
		this.offset = offset;
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.frames.IFrame#getData()
	 */
	@Override
	public byte[] getData () {
		return data;
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.frames.IFrame#setData(byte[])
	 */
	@Override
	public void setData (byte[] data) {
		this.data = data;
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.frames.IFrame#getChannelValue(int)
	 */
	@Override
	public byte getChannelValue (int i) {
		if ((offset + i < data.length) && (offset + i>=0)) {
			return data[offset + i];
		}
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.frames.IFrame#clear()
	 */
	@Override
	public void clear(){
		for(int i=0;i<data.length;i++){
			data[i]=0;
		}
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.frames.IFrame#setChannelValue(int, int)
	 */
	@Override
	public void setChannelValue(int channelNo, int value) {
		if (value < 0) {
			value = 0;
		} else if (value > 255) {
			value = 255;
		}
		data[channelNo] = (byte) value;
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.frames.IFrame#mixChannelValue(int, int)
	 */
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

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.frames.IFrame#setChannelValueDouble(int, double)
	 */
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

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.frames.IFrame#mixChannelValueDouble(int, double)
	 */
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
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.frames.IFrame#setChannelValueByte(int, byte)
	 */
	@Override
	public void setChannelValueByte(int channelNo, byte value) {
		data[channelNo] = value;
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.frames.IFrame#mixChannelValueByte(int, byte)
	 */
	@Override
	public void mixChannelValueByte(int channelNo, byte value) {
		if (value > data[channelNo]) {
			data[channelNo] = value;
		}
	}

	/**
	 * @param x - X Coordinate of the Pixel
	 * @param y - Y Coordinate of the Pixel
	 * @param value - Integer between 0 and 255 representing the Red component
	 */
	public void setRed (int x, int y, int value) {
		setChannelValue(getChannelNoFromCoords(x, y), value);
	}
	
	/**
	 * @param x - X Coordinate of the Pixel
	 * @param y - Y Coordinate of the Pixel
	 * @param value - Integer between 0 and 255 representing the Green component
	 */
	public void setGreen (int x, int y, int value) {
		setChannelValue(getChannelNoFromCoords(x, y)+1, value);
	}

	/**
	 * @param x - X Coordinate of the Pixel
	 * @param y - Y Coordinate of the Pixel
	 * @param value - Integer between 0 and 255 representing the Blue component
	 */
	public void setBlue (int x, int y, int value) {
		setChannelValue(getChannelNoFromCoords(x, y)+2, value);
	}
	
	//TODO: Check
	
	/**
	 * @param x - X Coordinate of the Pixel
	 * @param y - Y Coordinate of the Pixel
	 * @return returns the BaseAddress of the Pixel
	 */
	protected int getChannelNoFromCoords (int x, int y) {
		return y*40+x;
	}
}
