package dip.cbuu.processes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;



public class PatchExtraction {
	private BufferedImage bi = null;
	private int width;
	private int height;
	
	private short[][][] patchs;

	public short[][][] getPatchs() {
		return patchs;
	}
	
	public void randomImport8Patchs() {
		
		int w = bi.getWidth();
		int h = bi.getHeight();

		int dw = w - width + 1;
		int dh = h - height + 1;
		
		for (int i = 0; i < 8; i++) {
			Random random = new Random(i);
			int r = random.nextInt(dw*dh);
			int[] data = new int[width*height];
			for (int j = 0; j < height; j++) {
				for (int k = 0; k < width; k++) {
					int g = patchs[r][j][k]&0xff;

					data[j*width+k] =(int) (0xff000000|(g<<16)|(g<<8)|(g));
				}
			}

			Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, data, 0, width));
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
			Graphics g = bufferedImage.getGraphics();
			g.drawImage(image, 0, 0, null);
			try {
				ImageIO.write(bufferedImage, "png", new File("./res/patch_"+(i+1)+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public short[][][] view_as_window(BufferedImage bi, int width, int height) {
		this.width = width;
		this.height = height;
		this.bi = bi;
		
		int w = bi.getWidth();
		int h = bi.getHeight();

		int dw = w - width + 1;
		int dh = h - height + 1;
		
		patchs = new short[dw*dh][height][width];

		for (int i = 0; i < dh; i++) {
			for (int j = 0; j < dw; j++) {
				for (int k = 0; k < height; k++) {
					for (int l = 0; l < width; l++) {
						int rgb = bi.getRGB(j + l, i + k);
						int r = (rgb >> 16) & 0xff;
						int g = (rgb >> 8) & 0xff;
						int b = (rgb) & 0xff;
						patchs[i*dw+j][k][l] = (short) (r * 0.3 + g * 0.59 + b * 0.11);
					}
				}
			}
		}
		//randomImport8Patchs();
		return patchs;
	}
}
