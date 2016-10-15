package org.jugendhackt.magicalunicornlight.IO.net;

import java.io.*;
import java.net.*;

public class UDPConnector {
	private int port = -1;

	private int packetSize = 512;
	private DatagramSocket serverSocket;

	public UDPConnector() {
		init();
	}

	public UDPConnector(int port) {
		this.port = port;
		init();
	}

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

	public int getPort() {
		return port;
	}

	public int getPacketSize() {
		return packetSize;
	}

	public InetAddress getIP () {
		try {
			return InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void send (DatagramPacket response) {
		try {
			serverSocket.send(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

