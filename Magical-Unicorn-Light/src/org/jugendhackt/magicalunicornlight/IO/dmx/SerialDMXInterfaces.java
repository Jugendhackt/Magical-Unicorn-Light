package org.jugendhackt.magicalunicornlight.IO.dmx;

/**
 * @author eric
 * Enum storing device presets
 */
public enum SerialDMXInterfaces {
	EUROLITE_DMX512_PRO (new byte[] {0x7e,6,1,2,0}, new byte[] {(byte)(0xe7)}),
	DMX4ALL_NANODMX (new byte[]{1,0}, new byte[]{}),
	UNKNOWN;
	
	/**
	 * bytes for packet headder
	 */
	final byte[] headder;
	/**
	 * bytes for packet footer
	 */
	final byte[] footer;
	
	/**
	 * Default Constructor, no headder or footer
	 */
	private SerialDMXInterfaces () {
		headder = new byte[] {};
		footer = new byte[] {};
	}
	
	/**
	 * Constructor
	 * 
	 * @param byte[] head - Headder bytes
	 * @param byte[] foot - Footer bytes
	 */
	private SerialDMXInterfaces (byte[] head, byte[] foot) {
		this.headder = head;
		this.footer = foot;
	}
	
	/**
	 * @return headder bytes
	 */
	public byte[] getHeadder () {
		return headder;
	}
	
	/**
	 * @return footer bytes
	 */
	public byte[] getFooter () {
		return footer;
	}
	
	/**
	 * @return headder length
	 */
	public int getHeadderSize () {
		return headder.length;
	}
	
	/**
	 * @return footer length
	 */
	public int getFooterSize () {
		return footer.length;
	}
}
