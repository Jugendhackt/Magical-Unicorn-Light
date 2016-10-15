package org.jugendhackt.magicalunicornlight.IO.magicunicorndisplay;

import org.jugendhackt.magicalunicornlight.IO.BaseNetworkSender;

public class MagicSender extends BaseNetworkSender{
	/**
	 * Constructor.
	 * Default Address; 127.0.0.1
	 * Default Port: 1337
	 */
	public MagicSender () {
		super ();
		super.setPacketSize(512);
	}
	
	/**
	 * Constructor
	 * @param port - Target port (default: 1337)
	 */
	public MagicSender (int port) {
		super (port);
		super.setPacketSize(512);
	}

	/**
	 * Constructor
	 * @param address - The address to send to (default: 127.0.0.1)
	 */
	public MagicSender (String address) {
		super (address);
		super.setPacketSize(512);
	}
	
	/**
	 * Constructor
	 * @param address - The address to send to (default: 127.0.0.1)
	 * @param port - Target port (default: 1337)
	 */
	public MagicSender (String address, int port) {
		super (address, port);
		super.setPacketSize(512);
	}
}
