package org.jugendhackt.magicalunicornlight.IO.matelight;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.jugendhackt.magicalunicornlight.IO.ISender;
import org.jugendhackt.magicalunicornlight.IO.net.UDPConnector;
import org.jugendhackt.magicalunicornlight.frames.IFrame;

public class MateSender implements ISender{
	
	UDPConnector client;
	String address;
	int port;
	
	public MateSender () {
		port = 1337;
	}
	
	public MateSender (int port) {
		this.port = port;
	}

	public void setAddress (String address) {
		this.address = address;
	}
	
	public String getAddress () {
		return address;
	}
	
	public void setPort (int port) {
		this.port = port;
	}
	
	public int getPort () {
		return port;
	}
	
	@Override
	public void openPort() {
		client = new UDPConnector(port, 1923);
	}

	@Override
	public void closePort() {
		client = null;
		
	}

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

	@Override
	public boolean isOpen() {
		return client != null;
	}
}
