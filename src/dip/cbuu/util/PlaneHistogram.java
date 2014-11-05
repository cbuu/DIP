package dip.cbuu.util;

import java.awt.Color;  
import java.awt.Font;  
import java.awt.FontMetrics;  
import java.awt.Graphics;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import java.util.Random;
  
import javax.imageio.ImageIO;  

public class PlaneHistogram {  
    private final int histogramWidth = 1;
    private final int histogramPitch = 1;
    
    private int getMax(int[] v){
    	int max = 0;
    	for (int i : v) {
			max=i>max?i:max; 
		}
    	return max;
    }
    
    public BufferedImage paintPlaneHistogram(int[] v) {  
    	
    	  int max = getMax(v);
    	  int height = 400;
    	  int width = 600;
        double scale = (double)(height-50)/(double)(max);
        BufferedImage bufferImage = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_RGB);  
        Graphics g = bufferImage.getGraphics();  
        
        g.setColor(Color.BLACK);  
        g.fillRect(0, 0, width, height); 
  
        g.setColor(Color.WHITE);
        
        int d = 120;
        g.drawString("0", 5, height - 15);
        g.drawString("0.25", 5, height - 15 - d);
        g.drawString("0.5", 5, height - 15 - d*2 );
        g.drawString("1", 5, height - 15- d * 3);
  
        g.drawLine(15, 15, 15, height - 15); 
        g.drawLine(15, height - 15, width-15, height - 15);
          
        g.setColor(Color.GREEN);
        int j = 0;  
        for (int i = 0; i < v.length; ++i) {  
            int x = 16 + i  * (histogramPitch + histogramWidth);
            int y = height - 16 - (int) (v[i]*scale);
            
            g.drawLine(x,y, x, height - 16);
        }  
  
        return bufferImage;  
    }
} 