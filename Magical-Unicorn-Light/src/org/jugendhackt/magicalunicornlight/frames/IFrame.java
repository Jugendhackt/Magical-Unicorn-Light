package org.jugendhackt.magicalunicornlight.frames;

public interface IFrame {
	
	public int getOffset ();
	public void setOffset (int offset);
	
	public byte[] getData ();
	public void setData (byte[] data);
	
	public byte getChannelValue (int i);
	public void clear();

	public void setChannelValue(int channelNo, int value);
	public void mixChannelValue(int channelNo, int value);

	public void setChannelValueDouble(int channelNo, double value);
	public void mixChannelValueDouble(int channelNo, double value);
	
	public void setChannelValueByte(int channelNo, byte value);
	public void mixChannelValueByte(int channelNo, byte value);
}
