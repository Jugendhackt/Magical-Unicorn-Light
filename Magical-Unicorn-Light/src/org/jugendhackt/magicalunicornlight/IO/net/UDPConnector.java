package org.jugendhackt.magicalunicornlight.IO.net;

import java.io.*;
import java.net.*;

public class UDPConnector {
	/**
	 * Port to send from
	 */
	private int port = -1;

	/**
	 * Length of Packets
	 */
	private int packetSize = 512;
	/**
	 * UDP Socket
	 */
	private DatagramSocket serverSocket;

	/**
	 * Constructor
	 */
	public UDPConnector() {
		init();
	}

	/**
	 * @param port - Port to send from. -1 = next free port
	 */
	public UDPConnector(int port) {
		this.port = port;
		init();
	}

	/**
	 * @param port - Port to send from
	 * @param packetSize - Length of Packets
	 */
	public UDPConnector(int port, int packetSize) {
		this.port = port;
		this.packetSize = packetSize;
		init();
	}

	private void init () {
		try {
			if (port == -1) {

				serverSocket = new DatagramSocket ();

			} else {
				serverSocket = new DatagramSocket (port);
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return returns port to send from
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @return length of packets
	 */
	public int getPacketSize() {
		return packetSize;
	}

	/**
	 * @return IP address of this computer
	 */
	public InetAddress getIP () {
		try {
			return InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Send datagramm packet
	 * @param data - packet to send
	 */
	public void send (DatagramPacket data) {
		try {
			serverSocket.send(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

