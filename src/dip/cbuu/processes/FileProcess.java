package dip.cbuu.processes;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import dip.cbuu.common.Complex;
import dip.cbuu.common.MyImage;

public class FileProcess {
	
	private static FileProcess instance = null;
	
	private MyImage orgImage = null;
	
	private MyImage image = null;
	
	private Complex[][] fourierComplexs = null;
	
	public static FileProcess getInstance() {
		if (instance==null) {
			instance = new FileProcess();
		}
		return instance;
	}
	
	private FileProcess() {
		
	}
	
	public void open(Component component){
		
		String path = null;
		
		JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File("./res"));
    	int result = fileChooser.showOpenDialog(component);
    	if(result == JFileChooser.APPROVE_OPTION){
    		path = fileChooser.getSelectedFile().getPath();
    	}
    	
    	BufferedImage bufferedImage = null;
    	if(path!=null){
    		try {
    			bufferedImage = ImageIO.read(new File(path));
    			image =  new MyImage(bufferedImage);
    			orgImage = new MyImage(bufferedImage);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
	}
	
	public void save() {    
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./res"));
        
    	  int result = fileChooser.showSaveDialog(null);
    	  String savePath = null;
    	  if(result == JFileChooser.APPROVE_OPTION){
    		  savePath= fileChooser.getSelectedFile().getPath();
    	  }

        if (savePath!=null) {
        	 try { 
                 ImageIO.write(image.getBufferedImage(),"png",new File(savePath));
             } catch (IOException e) {
                 // TODO Auto-generated catch block 
                 e.printStackTrace(); 
             }
		}
	}
	
	
	public MyImage getImage() {
		return image;
	}
	
	public void setImage(MyImage image) {
		this.image = image;
	}

	public Complex[][] getFourierComplexs() {
		return fourierComplexs;
	}

	public void setFourierComplexs(Complex[][] fourierComplexs) {
		this.fourierComplexs = fourierComplexs;
	}

	public MyImage getOrgImage() {
		return orgImage;
	}

	public void setOrgImage(MyImage orgImage) {
		this.orgImage = orgImage;
	}


	
	
}
