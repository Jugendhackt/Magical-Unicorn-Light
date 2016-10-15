package org.jugendhackt.magicalunicornlight.frames;

/**
 * @author eric
 * Interface for all different frame types
 */
public interface IFrame {
	
	/**
	 * @return Integer specifing offset of Data to 0
	 */
	public int getOffset ();
	/**
	 * Set offset to 0
	 * @param offset
	 */
	public void setOffset (int offset);
	
	/**
	 * Returns the data array
	 * @return byte[] data
	 */
	public byte[] getData ();
	
	/**
	 * Override the data Array
	 * @param byte[] data
	 */
	public void setData (byte[] data);
	
	/**
	 * @param Channel No.
	 * @return Value of channel i
	 */
	public byte getChannelValue (int i);
	/**
	 * sets the whole frame to zero
	 */
	public void clear();

	/**
	 * Sets the value of the channel
	 * 
	 * @param channelNo - Number of the target channel
	 * @param value - Integer between 0 and 255
	 */
	public void setChannelValue(int channelNo, int value);
	/**
	 * Sets the value of the channel if larger than the current value
	 * 
	 * @param channelNo - Number of the target channel
	 * @param value - Integer between 0 and 255
	 */
	public void mixChannelValue(int channelNo, int value);

	/**
	 * Sets the value of the channel
	 * 
	 * @param channelNo - Number of the target channel
	 * @param value - double between 0 and 1
	 */
	public void setChannelValueDouble(int channelNo, double value);
	/**
	 * Sets the value of the channel if larger than the current value
	 * 
	 * @param channelNo - Number of the target channel
	 * @param value - Double between 0 and 1
	 */
	public void mixChannelValueDouble(int channelNo, double value);
	
	/**
	 * Sets the value of the channel
	 * 
	 * @param channelNo - Number of the target channel
	 * @param value - Byte between 0x00 and 0xff
	 */
	public void setChannelValueByte(int channelNo, byte value);
	/**
	 * Sets the value of the channel if larger than the current value
	 * 
	 * @param channelNo - Number of the target channel
	 * @param value -  Byte between 0x00 and 0xff
	 */
	public void mixChannelValueByte(int channelNo, byte value);
}
