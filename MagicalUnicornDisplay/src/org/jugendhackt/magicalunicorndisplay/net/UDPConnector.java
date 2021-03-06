package org.jugendhackt.magicalunicorndisplay.net;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class UDPConnector {
	
	private int port = -1;
	
	private int packetSize = 512;
	private DatagramSocket serverSocket;
	
	private byte[] receiveData;
	
	private volatile boolean isRunning;
	private Thread serverThread;
	
	ArrayList<DataRecievedInterface> dataRecievedInterfaces;
	
	public UDPConnector() {
		dataRecievedInterfaces = new ArrayList<>();
	}
	
	public UDPConnector(int port) {
		this.port = port;
		dataRecievedInterfaces = new ArrayList<>();
	}

	public UDPConnector(int port, int packetSize) {
		this.port = port;
		this.packetSize = packetSize;
		dataRecievedInterfaces = new ArrayList<>();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPacketSize() {
		return packetSize;
	}

	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}

	public InetAddress getIP () {
		try {
			return InetAddress.getByName("127.0.0.1");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}

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
	
	public void stop () {
		isRunning = false;
		if (serverThread != null && serverThread.isAlive()) {
			serverThread.interrupt();;
		}
		System.out.println("Server Stopped");
	}
	
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
				onPacketReceived (receivePacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addRecievedCallback (DataRecievedInterface cb) {
		dataRecievedInterfaces.add(cb);
	}
	
	public void removeRecievedCallback (DataRecievedInterface cb) {
		dataRecievedInterfaces.remove(cb);
	}
	
	public void onPacketReceived (DatagramPacket packet) {
		
		for (DataRecievedInterface inter : dataRecievedInterfaces) {
			inter.onPacketReceived(packet, this);
		}
	}

	public void respond (DatagramPacket response) {
		try {
			serverSocket.send(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
