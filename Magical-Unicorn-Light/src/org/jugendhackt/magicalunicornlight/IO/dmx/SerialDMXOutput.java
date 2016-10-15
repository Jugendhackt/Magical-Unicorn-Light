package org.jugendhackt.magicalunicornlight.IO.dmx;

import java.io.IOException;
import java.io.OutputStream;

import org.jugendhackt.magicalunicornlight.frames.DMXFrame;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class SerialDMXOutput {
	protected String portName;
	protected SerialDMXInterfaces inter;
	
	protected SerialPort port=null;
	protected OutputStream dmx_out=null;
	
	protected boolean isOpen;
	
	public SerialDMXOutput (SerialDMXInterfaces inter, String portName) {
		this.inter = inter;
		this.portName = portName;
	}
	
	public void openPort () {
		try{
			CommPortIdentifier portID = CommPortIdentifier.getPortIdentifier("/dev/ttyS81");
			port = (SerialPort)portID.open("", 2000);
			int baudRate = 230400; //bps
			try {
			  // Set serial port to 57600bps-8N1..my favourite
				port.setSerialPortParams(
			    baudRate,
			    SerialPort.DATABITS_8,
			    SerialPort.STOPBITS_2,
			    SerialPort.PARITY_NONE);
			} catch (UnsupportedCommOperationException ex) {
				ex.printStackTrace();
			}
			dmx_out=port.getOutputStream();
		}catch(Exception ex){
			port=null;
			dmx_out=null;
			ex.printStackTrace();
			return;
		}
	}
	
	public void closePort () {
		port.close();
	}
	
	byte[] prepareFrameData (DMXFrame frame) {
		byte[] frameData = overrideData(frame.getData(), DMXFrame.EMPTY.getData());
		byte[] data = new byte[frame.getData().length + frame.getOffset() + inter.getHeadderSize() + inter.getFooterSize()];
		
		System.arraycopy(inter.getHeadder(), 0, data, 0, inter.getHeadderSize());
		System.arraycopy(frameData, 0, data, inter.getHeadderSize() + frame.getOffset(), frame.getData().length);
		System.arraycopy(inter.getFooter(), 0, data, inter.getHeadderSize() + frame.getOffset() +  frame.getData().length, 
				inter.getFooterSize());
		
		return data;
	}
	
	byte[] overrideData (byte[] in, byte[] data) {
		for (int i = 0; i < data.length; i++) {
			if (i >= in.length) {
				break;
			}
			if (data[i] != 0) {
				in[i] = data[i];
			}
		}
		
		return in;
	}
	
	public void sendFrame (DMXFrame data){
		if(dmx_out==null){
			System.out.println("IOError when sending DMX data: dmx_out is not open");
			return;
		}
		try{
			dmx_out.write(prepareFrameData(data));
			port.sendBreak(0);
		}catch(IOException ex){
			System.out.println("IOError when sending DMX data: "+ex.getLocalizedMessage());
		}
	}

	public boolean isOpen () {
		return isOpen;
	}
}
