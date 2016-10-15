package org.jugendhackt.magicalunicornlight.frames;

public class MagicFrame implements IFrame{

	/**
	 *  offset of Start of DMX data from 0 if the frame is incomplete. Default: 0
	 */
	private int offset;
	/**
	 * The actual DMX data. Length should be 256
	 */
	private byte[] data;
	
	/**
	 * Represents an Empty (Black) DMX Frame
	 */
	public static final MagicFrame EMPTY = new MagicFrame (new byte[] {0,0,0});
	
	/**
	 *  Default Constructor
	 */
	public MagicFrame () {
		offset = 0;
		data = new byte[512];
	}
	
	/**
	 * Constructor
	 * 
	 * @param data - byte[] containing DMX Data
	 */
	public MagicFrame (byte[] data) {
		offset = 0;
		this.data = data;
	}
	
	/**
	 * Constructor
	 * 
	 * @param data - byte[] containing DMX Data
	 * @param offset - int specifiing offset of data to 0 (for frames with incomplete data)
	 */
	public MagicFrame (byte[] data, int offset) {
		this.offset = offset;
		this.data = data;
	}
	
	/**
	 * Constructor
	 * Clones the given DMXFrame
	 * 
	 * @param frame - The frame to clone
	 */
	public MagicFrame (MagicFrame frame) {
		if (frame != null) {
			this.offset = frame.offset;
			this.data = frame.data;
		} else {
			offset = 0;
			data = new byte[512];
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
	 * Sets the Color to send
	 * @param r - Red component. Double between 0 and 1
	 * @param g - Green component. Double between 0 and 1
	 * @param b - Blue component. Double between 0 and 1
	 */
	public void setColor (double r, double g, double b) {
		setChannelValueDouble (0, r);
		setChannelValueDouble (1, g);
		setChannelValueDouble (2, b);
	}

}
