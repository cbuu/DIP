package dip.cbuu.processes;

import java.awt.image.BufferedImage;

import dip.cbuu.common.MyImage;

public class GeometricMeanProcess {
	public static BufferedImage process(BufferedImage bufferedImage, int size){
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		int[][] old = MyImage.getData(bufferedImage);
		int d = size / 2;
		int[] data = new int[width * height];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				double a = 1;

				for (int k = i - d; k <= i + d; k++) {
					for (int l = j - d; l <= j + d; l++) {
						int val = 0;
						if (k < 0 || k > height - 1 || l < 0 || l > width - 1) {
							val = 0;
						} else {
							val = old[l][k];
						}
						a *= val;
					}
				}

				int p = (int) (StrictMath.pow(a, 1.0/(size*size)));

				p = p > 255 ? 255 : p;
				p = p < 0 ? 0 : p;
				data[i * width + j] = (int) (0xff000000 | (p << 16) | (p << 8) | (p));
			}
		}

		BufferedImage newBufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		newBufferedImage.setRGB(0, 0, width, height, data, 0, width);

		return newBufferedImage;
	}
}
