package org.jugendhackt.magicalunicorndisplay.net;

import java.net.DatagramPacket;

public interface DataRecievedInterface {
	public void onPacketReceived (DatagramPacket packet, UDPConnector server);
}
