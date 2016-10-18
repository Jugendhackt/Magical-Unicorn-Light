package org.jugendhackt.magicalunicornlight.IO.dmx;

import org.jugendhackt.magicalunicornlight.frames.DMXFrame;
import org.jugendhackt.magicalunicornlight.frames.IFrame;

//FIXME: Add Static Buffer, so that there are no send conflicts !!!

public class BufferedDMXSender extends DMXSender{
	/**
	 * Data buffer for repeated sending by senderThread
	 */
	protected volatile IFrame bufferedData;
	
	/**
	 * Thread sending buffered data in specified intervall
	 */
	private volatile Thread senderThread;
	/**
	 * specifies ms between sending data
	 */
	private int sendTime;
	
	/**
	 * Constructor.
	 * Default Address; 127.0.0.1
	 * Default Port: 1337
	 */
	public BufferedDMXSender () {
		super ();
		bufferedData = DMXFrame.EMPTY;
		this.sendTime = 50; // ms
	}
	
	/**
	 * Constructor
	 * @param port - Target port (default: 1337)
	 */
	public BufferedDMXSender (int port) {
		super (port);
		bufferedData = DMXFrame.EMPTY;
		this.sendTime = 50; // ms
	}

	/**
	 * Constructor
	 * @param address - The address to send to (default: 127.0.0.1)
	 */
	public BufferedDMXSender (String address) {
		super (address);
		bufferedData = DMXFrame.EMPTY;
		this.sendTime = 50; // ms
	}
	
	/**
	 * Constructor
	 * @param address - The address to send to (default: 127.0.0.1)
	 * @param port - Target port (default: 1337)
	 */
	public BufferedDMXSender (String address, int port) {
		super (address, port);
		bufferedData = DMXFrame.EMPTY;
		this.sendTime = 50; // ms	
	}
	
	public int getSendTime() {
		return sendTime;
	}

	public void setSendTime(int sendTime) {
		this.sendTime = sendTime;
	}

	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.IO.dmx.SerialDMXOutput#openPort()
	 */
	@Override
	public void openPort () {
		super.openPort();
		startPlayerThread();
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.IO.dmx.SerialDMXOutput#closePort()
	 */
	@Override
	public void closePort() {
		super.closePort();
		stopPlayerThread();
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.IO.dmx.SerialDMXOutput#sendFrame(org.jugendhackt.magicalunicornlight.frames.DMXFrame)
	 */
	@Override
	public void sendFrame (IFrame data){
		this.bufferedData = data;
	}
	
	private void startPlayerThread(){
		if(senderThread==null){
			senderThread=new Thread(processPlayer);
			senderThread.start();
		}
	}
	
	private void stopPlayerThread(){
		if(senderThread!=null){
			senderThread.interrupt();
		}
	}
	
	private void send (IFrame data) {
		super.sendFrame(data);
	}
	
	private Runnable processPlayer = new Runnable() {
		@Override
		public void run() {
			System.out.println("Worker Thread startet");
			try {
				while (true) {
					if (Thread.interrupted()) {
						System.out.println("Thread Stopped by Interrupt");
						break;
					}
					send (bufferedData);
					Thread.sleep(sendTime);
//					break;
				}
			} catch (InterruptedException e) {
				// Ignore
				e.printStackTrace();
			} finally {
				senderThread = null;
				System.out.println("Worker Thread stopped");
			}
		}
	};
}
