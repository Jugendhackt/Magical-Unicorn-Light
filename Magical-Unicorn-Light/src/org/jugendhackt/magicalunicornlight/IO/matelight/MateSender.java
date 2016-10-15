package org.jugendhackt.magicalunicornlight.IO.matelight;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.jugendhackt.magicalunicornlight.IO.ISender;
import org.jugendhackt.magicalunicornlight.IO.net.UDPConnector;
import org.jugendhackt.magicalunicornlight.frames.IFrame;

public class MateSender implements ISender{
	
	/**
	 * uDP client
	 */
	UDPConnector client;
	/**
	 * IP Address of Target
	 */
	String address;
	/**
	 * Targets port
	 */
	int port;
	
	/**
	 * Constructor.
	 * Default Address; 127.0.0.1
	 * Default Port: 1337
	 */
	public MateSender () {
		address = "127.0.0.1";
		port = 1337;
	}
	
	/**
	 * Constructor
	 * @param port - Target port (default: 1337)
	 */
	public MateSender (int port) {
		address = "127.0.0.1";
		this.port = port;
	}

	/**
	 * Constructor
	 * @param address - The address to send to (default: 127.0.0.1)
	 */
	public MateSender (String address) {
		this.address = address;
		this.port = 1337;
	}
	
	/**
	 * Constructor
	 * @param address - The address to send to (default: 127.0.0.1)
	 * @param port - Target port (default: 1337)
	 */
	public MateSender (String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	/**
	 * @param address - The address to send to (default: 127.0.0.1)
	 */
	public void setAddress (String address) {
		this.address = address;
	}
	
	/**
	 * @return Target Address
	 */
	public String getAddress () {
		return address;
	}
	
	/**
	 * @param port - Target port (default: 1337)
	 */
	public void setPort (int port) {
		this.port = port;
	}
	
	/**
	 * @return Target port
	 */
	public int getPort () {
		return port;
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.IO.ISender#openPort()
	 */
	@Override
	public void openPort() {
		client = new UDPConnector(-1, 1923);
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.IO.ISender#closePort()
	 */
	@Override
	public void closePort() {
		client = null;
		
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.IO.ISender#sendFrame(org.jugendhackt.magicalunicornlight.frames.IFrame)
	 */
	@Override
	public void sendFrame(IFrame data) {
		DatagramPacket packet = new DatagramPacket(data.getData(), 1923);
		try {
			packet.setAddress(InetAddress.getByName(address));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		packet.setPort(port);
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.IO.ISender#isOpen()
	 */
	@Override
	public boolean isOpen() {
		return client != null;
	}
}
