package dip.cbuu.processes;

import java.awt.image.BufferedImage;

public class RGBEqualizeProcess {
	public static BufferedImage process(BufferedImage bufferedImage) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();

		double mn = width * height;
		int[] RLevel = new int[256];
		int[] GLevel = new int[256];
		int[] BLevel = new int[256];

		double[] Level = new double[256];

		double[] Percent = new double[256];

		int[] S = new int[256];

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

		for (int i = 0; i < 256; i++) {
			Level[i] = ((double) (RLevel[i] + GLevel[i] + BLevel[i])) / 3.0;
		}

		Percent[0] = Level[0] / mn;
		for (int i = 1; i < Percent.length; i++) {
			Percent[i] = Percent[i - 1] + Level[i] / mn;
		}
		for (int i = 0; i < S.length; i++) {
			S[i] = (int) (255 * Percent[i] + 0.5);
		}

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = bufferedImage.getRGB(i, j);
				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = (rgb) & 0xff;

				r = S[r];
				g = S[g];
				b = S[b];
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
