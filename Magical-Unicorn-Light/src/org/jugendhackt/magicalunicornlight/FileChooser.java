package org.jugendhackt.magicalunicornlight;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {
    
	/**
	 * Opens FileBrowser in Users Home Directory with Filter applied to open only WAV - files
	 * 
	 * @return Path of selected file as String, NULL and exits if none is selected
	 */
	public String popupFileLocation(){
    	 // JFileChooser-Objekt erstellen
        JFileChooser chooser = new JFileChooser();
        
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV Dateien","WAV","wav");
        chooser.setFileFilter(filter);
        
        // Dialog zum Oeffnen von Dateien anzeigen
        chooser.showOpenDialog(null);
        if (chooser.getSelectedFile() != null) {
            String file_path = chooser.getSelectedFile().getAbsolutePath();
            return file_path;
        } else {
        	//TODO: Return Null and catch in audio
        	System.exit(0);
        	return null;
        }
    }
}