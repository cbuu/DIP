package dip.cbuu.processes;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Comparator;

import dip.cbuu.util.DebugLog;

public class DehazeProcess {
	private static int width = 0;
	private static int height = 0;

	public static BufferedImage process(BufferedImage darkChannelImage) {
		width = darkChannelImage.getWidth();
		height = darkChannelImage.getHeight();
		int length = width * height;
		int select = (int) (length * 0.001);
		int d = 7;
		int[] data = new int[length];
		Pixel[] pixels = new Pixel[length];
		int Ar = 0, Ag = 0, Ab = 0;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Pixel p = new Pixel();
				p.gray = darkChannelImage.getRGB(i, j) & 0xff;
				p.x = i;
				p.y = j;
				pixels[i * height + j] = p;
			}
		}
		int max = 0;
		Arrays.sort(pixels, new pixelComparator());
		BufferedImage orgImage = FileProcess.getInstance().getOrgImage()
				.getBufferedImage();
		for (int i = 0; i < select; i++) {
			int rgb = orgImage.getRGB(pixels[i].x, pixels[i].y);
			int r, g, b;
			r = (rgb >> 16) & 0xff;
			g = (rgb >> 8) & 0xff;
			b = (rgb) & 0xff;
			int h = (int) ((r + g + b) / 3.0);
			if (h > max) {
				max = 0;
				Ar = r;
				Ag = g;
				Ab = b;
			}
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				double mr = 255, mg = 255, mb = 255;
				double r, g, b;
				double min = 255;
				for (int k = i - d; k <= i + d; k++) {
					for (int l = j - d; l <= j + d; l++) {
						if (k < 0 || k > height- 1 || l < 0 || l > width - 1) {

						} else {
							int rgb = orgImage.getRGB(l, k);
							r = (double) ((rgb >> 16) & 0xff);
							g = (double) ((rgb >> 8) & 0xff);
							b = (double) ((rgb) & 0xff);
							r /= Ar;
							g /= Ag;
							b /= Ab;

							mr = r < mr ? r : mr;
							mg = g < mg ? g : mg;
							mb = b < mb ? b : mb;
						}
					}
				}

				min = mr < mg ? mr : mg;
				min = min < mb ? min : mb;

				double t = 1 - 0.95 * min;
				t = t < 0.1 ? 0.1 : t;

				int rgb = orgImage.getRGB(j,i);
				r = (double) ((rgb >> 16) & 0xff);
				g = (double) ((rgb >> 8) & 0xff);
				b = (double) ((rgb) & 0xff);
				
				r = ((r - Ar) / t) + Ar;
                g = ((g - Ag) / t) + Ag;
                b = ((b - Ab) / t) + Ab;
				
                r = r > 0 ? r : 0;
                g = g > 0 ? g : 0;
                b = b > 0 ? b : 0;
                
                r+=30;
                b+=30;
                g+=30;

                r = r < 255 ? r : 255;
                g = g < 255 ? g : 255;
                b = b < 255 ? b : 255;
                
                data[i * width + j] = 0xff000000 | ((int)(r) << 16) | ((int)(g) << 8)
						| (int)(b);
			}
		}
		

		BufferedImage newBufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		newBufferedImage.setRGB(0, 0, width, height, data, 0, width);

		return newBufferedImage;
	}

	static class Pixel {
		int gray;
		int x;
		int y;
	}

	static class pixelComparator implements Comparator<Pixel> {

		@Override
		public int compare(Pixel o1, Pixel o2) {
			return o2.gray - o1.gray;
		}
	}
}
