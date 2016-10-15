package org.jugendhackt.magicalunicornlight.IO.dmx;

import org.jugendhackt.magicalunicornlight.frames.DMXFrame;

public class BufferedSerialDMXOutput extends SerialDMXOutput{
	protected volatile DMXFrame bufferedData;
	
	private volatile Thread senderThread;
	private int sendTime;
	
	public BufferedSerialDMXOutput(SerialDMXInterfaces inter, String portName) {
		super(inter, portName);
		bufferedData = DMXFrame.EMPTY;
		sendTime = 50; // ms
	}

	public BufferedSerialDMXOutput(SerialDMXInterfaces inter, String portName, int sendTime) {
		super(inter, portName);
		bufferedData = DMXFrame.EMPTY;
		this.sendTime = sendTime; // ms
	}
	
	@Override
	public void openPort () {
		super.openPort();
		startPlayerThread();
	}
	
	@Override
	public void closePort() {
		super.closePort();
		stopPlayerThread();
	}
	
	@Override
	public void sendFrame (DMXFrame data){
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
	
	private void send (DMXFrame data) {
		super.sendFrame(data);
	}
	
	Runnable processPlayer = new Runnable() {
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
