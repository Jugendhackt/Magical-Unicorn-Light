package org.jugendhackt.magicalunicornlight.IO.dmx;

import org.jugendhackt.magicalunicornlight.frames.DMXFrame;
import org.jugendhackt.magicalunicornlight.frames.IFrame;

/**
 * @author eric
 * Buffered Writer to SerialPort
 */
public class BufferedSerialDMXOutput extends SerialDMXOutput{
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
	 * Constructor
	 * @param inter - DMX Interface to prepare the data for
	 * @param portName - Name of the Serial port
	 */
	public BufferedSerialDMXOutput(SerialDMXInterfaces inter, String portName) {
		super(inter, portName);
		bufferedData = DMXFrame.EMPTY;
		sendTime = 50; // ms
	}

	/**
	 * @param inter - DMX Interface to prepare the data for
	 * @param portName - Name of the Serial port
	 * @param sendTime - Time between sending data in ms. (Default: 50 ms)
	 */
	public BufferedSerialDMXOutput(SerialDMXInterfaces inter, String portName, int sendTime) {
		super(inter, portName);
		bufferedData = DMXFrame.EMPTY;
		this.sendTime = sendTime; // ms
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
