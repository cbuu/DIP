package dip.cbuu.common;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

public class MyImage {
	private BufferedImage bufferedImage = null;
	
	public MyImage(BufferedImage bufferedImage) {
		// TODO Auto-generated constructor stub
		this.bufferedImage = bufferedImage;
	}
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	
	public static int[][] getData(BufferedImage bufferedImage) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		
		int[][] data = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = bufferedImage.getRGB(i, j);
				int r = (rgb>>16)&0xff;
				int g = (rgb>>8)&0xff;
				int b = (rgb)&0xff;
				data[i][j] = (int) (r*0.3+g*0.59+b*0.11);
			}
		}
		return data;
	}
	
	public int getWidth() {
		return bufferedImage.getWidth();
	}
	
	public int getHeight() {
		return bufferedImage.getHeight();
	}
	
	public void update(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	
}
