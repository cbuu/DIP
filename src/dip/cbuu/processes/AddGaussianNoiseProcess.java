package dip.cbuu.processes;

import java.awt.image.BufferedImage;

import dip.cbuu.common.INoise;
import dip.cbuu.common.MyImage;
import dip.cbuu.util.DebugLog;
import dip.cbuu.util.NoiseGenerator;

public class AddGaussianNoiseProcess {
	public static BufferedImage process(BufferedImage bufferedImage){
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		int[][] old = MyImage.getData(bufferedImage);
		int[] data = new int[width * height];

		INoise noise = NoiseGenerator.generateNoise(INoise.GAUSSIAN_NOISE, width, height);
		int [][] noiseData = noise.getData();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				int p = (int) (old[j][i] + noiseData[j][i]);

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
