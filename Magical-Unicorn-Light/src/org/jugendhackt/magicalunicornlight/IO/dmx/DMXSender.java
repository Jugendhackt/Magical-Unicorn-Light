package org.jugendhackt.magicalunicornlight.IO.dmx;

import org.jugendhackt.magicalunicornlight.IO.BaseNetworkSender;
import org.jugendhackt.magicalunicornlight.frames.DMXFrame;
import org.jugendhackt.magicalunicornlight.frames.IFrame;

public class DMXSender extends BaseNetworkSender{
	/**
	 * Constructor.
	 * Default Address; 127.0.0.1
	 * Default Port: 1337
	 */
	protected DMXSender () {
		super ();
		super.setPacketSize(512);
	}
	
	/**
	 * Constructor
	 * @param port - Target port (default: 1337)
	 */
	protected DMXSender (int port) {
		super (port);
		super.setPacketSize(512);
	}

	/**
	 * Constructor
	 * @param address - The address to send to (default: 127.0.0.1)
	 */
	protected DMXSender (String address) {
		super (address);
		super.setPacketSize(512);
	}
	
	/**
	 * Constructor
	 * @param address - The address to send to (default: 127.0.0.1)
	 * @param port - Target port (default: 1337)
	 */
	protected DMXSender (String address, int port) {
		super (address, port);
		super.setPacketSize(512);
	}
	
	@Override
	public void sendFrame(IFrame data) {
		super.sendFrame(prepareFrame(data));
	}
	
	private IFrame prepareFrame (IFrame data) {
		DMXFrame frame = new DMXFrame();
		
		byte[] raw = new byte[513];
		
		for (int i = 0; i < raw.length; i++) {
			raw[i] = 0;
		}
		
		for (int i = 0; i < raw.length - 1; i++) {
			if (i < data.getData().length + data.getOffset()) {
				raw[i+1] = data.getData()[data.getOffset() + i];
			}
		}
		
		raw[0] = 0;
		frame.setOffset(0);
		frame.setData(raw);
		
		return frame;
	}

}
