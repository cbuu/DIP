package dip.cbuu.processes;

import java.awt.image.BufferedImage;

public class EqualizeRGBProcess {
	public static BufferedImage process(BufferedImage bufferedImage) {

		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();

		double mn = width * height;
		int[] RLevel = new int[256];
		int[] GLevel = new int[256];
		int[] BLevel = new int[256];
		double[] RPercent = new double[256];
		double[] GPercent = new double[256];
		double[] BPercent = new double[256];
		
		int[] RS = new int[256];
		int[] GS = new int[256];
		int[] BS = new int[256];

		int[] data = new int[width * height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = bufferedImage.getRGB(i, j);
				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = (rgb) & 0xff;
				RLevel[r]++;
				GLevel[g]++;
				BLevel[b]++;
			}
		}
		
		RPercent[0] = (double) (RLevel[0]) / mn;
		for (int i = 1; i < RPercent.length; i++) {
			RPercent[i] = RPercent[i - 1] + (double) (RLevel[i]) / mn;
		}
		
		GPercent[0] = (double) (GLevel[0]) / mn;
		for (int i = 1; i < GPercent.length; i++) {
			GPercent[i] = GPercent[i - 1] + (double) (GLevel[i]) / mn;
		}
		
		BPercent[0] = (double) (BLevel[0]) / mn;
		for (int i = 1; i < BPercent.length; i++) {
			BPercent[i] = BPercent[i - 1] + (double) (BLevel[i]) / mn;
		}

		
		for (int i = 0; i < RS.length; i++) {
			RS[i] = (int) (255 * RPercent[i] + 0.5);
		}
		for (int i = 0; i < GS.length; i++) {
			GS[i] = (int) (255 * GPercent[i] + 0.5);
		}
		for (int i = 0; i < BS.length; i++) {
			BS[i] = (int) (255 * BPercent[i] + 0.5);
		}
		
		

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = bufferedImage.getRGB(i, j);
				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = (rgb) & 0xff;
				
				r = RS[r];
				g = GS[g];
				b = BS[b];
				rgb = 0xff000000 | (r << 16) | (g << 8) | b;
				data[j * width + i] = rgb;
			}
		}

		BufferedImage newBufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		newBufferedImage.setRGB(0, 0, width, height, data, 0, width);
		return newBufferedImage;
	}
}
