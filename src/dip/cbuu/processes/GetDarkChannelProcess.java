package dip.cbuu.processes;

import java.awt.image.BufferedImage;
import java.security.spec.MGF1ParameterSpec;

import dip.cbuu.common.INoise;
import dip.cbuu.common.MyImage;
import dip.cbuu.util.DebugLog;
import dip.cbuu.util.NoiseGenerator;

public class GetDarkChannelProcess {

	// 窗口大小
	private static final int DEFAULT_SIZE = 11;

	// 获取暗通道处理
	public static BufferedImage process(BufferedImage bufferedImage) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		int[] data = new int[width * height];
		int d = DEFAULT_SIZE / 2;

		// 最小滤波
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int mr = 255, mg = 255, mb = 255;
				int r, g, b;
				int min = 255;
				for (int k = i - d; k <= i + d; k++) {
					for (int l = j - d; l <= j + d; l++) {
						if (k < 0 || k > height - 1 || l < 0 || l > width - 1) {
						} else {
							int rgb = bufferedImage.getRGB(l, k);
							r = (rgb >> 16) & 0xff;
							g = (rgb >> 8) & 0xff;
							b = (rgb) & 0xff;

							mr = r < mr ? r : mr;
							mg = g < mg ? g : mg;
							mb = b < mb ? b : mb;
						}
					}
				}
				min = mr < mg ? mr : mg;
				min = min < mb ? min : mb;
				data[i * width + j] = 0xff000000 | (min << 16) | (min << 8)
						| min;

			}
		}

		BufferedImage newBufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		newBufferedImage.setRGB(0, 0, width, height, data, 0, width);

		return newBufferedImage;
	}
}
