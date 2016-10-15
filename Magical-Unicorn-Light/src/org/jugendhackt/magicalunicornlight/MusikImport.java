package org.jugendhackt.magicalunicornlight;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class MusikImport {
	
	static void musikImport() {

		FileChooser chooser = new FileChooser();
		String filePath = chooser.popupFileLocation();
		try {

			AudioInputStream input = AudioSystem.getAudioInputStream(new File(filePath));

			System.out.println(input.getFormat().toString());
			SourceDataLine line = AudioSystem.getSourceDataLine(input.getFormat());
			line.open(input.getFormat());
			line.start();
			byte[] buffer = new byte[1024];
			Complex[] komplex = new Complex[512];

			int count;
			while((count = input.read(buffer, 0, 1024)) != -1) {
				line.write(buffer, 0, count);


				for(int i = 0;i < 512;i++) {
					int ii = i * 2;
					int e = buffer[i] & 0xFF;
					int f = buffer[ii];
					int gesamt = e + (256 * f);
					//System.out.println("" + (e + (256 * f)));
					komplex[i] = new Complex((double) gesamt,0);
				}
				Complex[] ergebnis = FFT.fft(komplex);
				System.out.println(ergebnis[0] + "/" + ergebnis[1] + "/" + ergebnis[2] + "/" + ergebnis[3] + "/" + ergebnis[4] + "/");

			}
			line.drain();
			line.stop();
			line.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		musikImport();
	}
}