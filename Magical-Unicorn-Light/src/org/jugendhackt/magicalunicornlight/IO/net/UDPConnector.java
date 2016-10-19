package org.jugendhackt.magicalunicornlight.IO.net;

import java.io.*;
import java.net.*;

public class UDPConnector {
	
	private int port = -1;
	
	private int packetSize = 512;
	private DatagramSocket serverSocket;
	
	private byte[] receiveData;
	
	private volatile boolean isRunning;
	private Thread serverThread;
	
	/**
	 * Default Constructor
	 * 
	 * Default Packet Size: 512 Bytes
	 * 
	 */
	public UDPConnector() {
	}
	
	/**
	 * Constructor
	 * 
	 * Default Packet Size: 512 Bytes
	 * 
	 * @param port - Port to listen on
	 */
	public UDPConnector(int port) {
		this.port = port;
	}

	/**
	 * Constructor
	 * 
	 * @param port - Port to listen on
	 * @param packetSize - Packet size to expect (Default: 512)
	 */
	public UDPConnector(int port, int packetSize) {
		this.port = port;
		this.packetSize = packetSize;
	}

	/**
	 * @return Port the server is listening on
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port - Sets Port to listen on
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return Current PacketSize
	 */
	public int getPacketSize() {
		return packetSize;
	}

	/**
	 * Sets the current packetSize
	 * 
	 * @param packetSize - PacketSize in bytes
	 */
	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}

	/**
	 * @return Current IP Address of this Server
	 */
	public InetAddress getIP () {
		try {
			return InetAddress.getByName("127.0.0.1");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Starts the Server
	 */
	public void start () {
		try {
			if (port == -1) {
				serverSocket = new DatagramSocket ();
			} else {
				serverSocket = new DatagramSocket (port);
			}
			receiveData = new byte[packetSize];
			isRunning = true;
			serverThread = new Thread(new Runnable() {
				public void run() {
					serverLoop ();
				}
			});
			serverThread.start();
		} catch (SocketException e) {
			e.printStackTrace();
			isRunning = false;
		} finally {
//			this.stop();
		}
		
	}
	
	/**
	 * Stops the Server
	 */
	public void stop () {
		isRunning = false;
		if (serverThread != null && serverThread.isAlive()) {
			serverThread.interrupt();;
		}
		System.out.println("Server Stopped");
	}
	
	/**
	 * Restarts the Server
	 */
	public void restart () {
		this.stop();
		this.start();
	}
	
	private void serverLoop () {
		while (true) {
			if (!isRunning) {
				return;
			}
			
			System.out.println("Server running");
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
				serverSocket.receive(receivePacket);
				System.out.println("Data recieved from " + receivePacket.getAddress().toString());
				onPacketRecieved (receivePacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	
	/**
	 * Called when Data is recieved
	 * 
	 * @param packet - Recieved data
	 */
	public void onPacketRecieved (DatagramPacket packet) {

	}

	/**
	 * Sends DatagramPacket over serverSocket
	 * 
	 * @param response - Data to send
	 */
	public void respond (DatagramPacket response) {
		try {
			serverSocket.send(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

