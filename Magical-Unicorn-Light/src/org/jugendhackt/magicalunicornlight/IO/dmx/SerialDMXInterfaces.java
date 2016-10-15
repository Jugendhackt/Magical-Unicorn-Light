package org.jugendhackt.magicalunicornlight.IO.dmx;

public enum SerialDMXInterfaces {
	EUROLITE_DMX512_PRO (new byte[] {0x7e,6,1,2,0}, new byte[] {(byte)(0xe7)}),
	DMX4ALL_NANODMX (new byte[]{1,0}, new byte[]{}),
	UNKNOWN;
	
	final byte[] headder;
	final byte[] footer;
	
	private SerialDMXInterfaces () {
		headder = new byte[] {};
		footer = new byte[] {};
	}
	
	private SerialDMXInterfaces (byte[] head, byte[] foot) {
		this.headder = head;
		this.footer = foot;
	}
	
	public byte[] getHeadder () {
		return headder;
	}
	
	public byte[] getFooter () {
		return footer;
	}
	
	public int getHeadderSize () {
		return headder.length;
	}
	
	public int getFooterSize () {
		return footer.length;
	}
}
