package org.jugendhackt.magicalunicornlight.audio.input;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import org.jugendhackt.magicalunicornlight.FileChooser;
import org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor;

public class MusikImport implements IAudioReader {
	
	ArrayList <IAudioProcessor> callbacks;
	
	public MusikImport () {
		callbacks = new ArrayList<>();
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.input.IAudioReader#addCallback(org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor)
	 */
	@Override
	public void addCallback (IAudioProcessor proccessor) {
		this.callbacks.add(proccessor);
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.input.IAudioReader#removeCallback(org.jugendhackt.magicalunicornlight.audio.processor.IAudioProcessor)
	 */
	@Override
	public void removeCallback (IAudioProcessor proccessor) {
		this.callbacks.remove(proccessor);
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.input.IAudioReader#clearCallbacks()
	 */
	@Override
	public void clearCallbacks () {
		this.callbacks.clear();
	}
	
	/* (non-Javadoc)
	 * @see org.jugendhackt.magicalunicornlight.audio.input.IAudioReader#musikImport()
	 */
	@Override
	public void musikImport() {

		FileChooser chooser = new FileChooser();
		String filePath = chooser.popupFileLocation();
		try {

			AudioInputStream input = AudioSystem.getAudioInputStream(new File(filePath));

			System.out.println(input.getFormat().toString());
			SourceDataLine line = AudioSystem.getSourceDataLine(input.getFormat());
			line.open(input.getFormat());
			line.start();
			byte[] buffer = new byte[1024];
			int[] samples = new int[512];

			int count;
			while((count = input.read(buffer, 0, 1024)) != -1) {
				line.write(buffer, 0, count);


				for(int i = 0;i < 512;i++) {
					int ii = i * 2;
					int e = buffer[i] & 0xFF;
					int f = buffer[ii];
					samples[i] = (int)(e + (256 * f));
				}
				
				for (IAudioProcessor proc : callbacks) {
					proc.setBitDepth(input.getFormat().getSampleSizeInBits());
					proc.process(samples);
				}
				
			}
			line.drain();
			line.stop();
			line.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}