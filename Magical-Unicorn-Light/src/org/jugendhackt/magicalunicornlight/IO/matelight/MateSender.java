package org.jugendhackt.magicalunicornlight.IO.matelight;

import org.jugendhackt.magicalunicornlight.IO.BaseNetworkSender;
//1923

public class MateSender extends BaseNetworkSender{

	/**
	 * Constructor.
	 * Default Address; 127.0.0.1
	 * Default Port: 1337
	 */
	public MateSender() {
		super();
		super.setPacketSize(1923);
	}

	/**
	 * Constructor
	 * @param port - Target port (default: 1337)
	 */
	public MateSender(int port) {
		super(port);
		super.setPacketSize(1923);
	}

	/**
	 * Constructor
	 * @param address - The address to send to (default: 127.0.0.1)
	 * @param port - Target port (default: 1337)
	 */
	public MateSender(String address, int port) {
		super(address, port);
		super.setPacketSize(1923);
	}

	/**
	 * Constructor
	 * @param address - The address to send to (default: 127.0.0.1)
	 */
	public MateSender(String address) {
		super(address);
		super.setPacketSize(1923);
	}
	
	
}
