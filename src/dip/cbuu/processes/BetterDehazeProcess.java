package dip.cbuu.processes;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Comparator;

import dip.cbuu.common.MyImage;
import dip.cbuu.processes.DehazeProcess.Pixel;
import dip.cbuu.processes.DehazeProcess.pixelComparator;

public class BetterDehazeProcess {
	private static int width = 0;
	private static int height = 0;

	private static final int DEFAULT_SIZE = 127;
	private static final int MAX_A = 180;
	private static final double w = 1.0;

	public static BufferedImage process(BufferedImage darkChannelImage) {
		width = darkChannelImage.getWidth();
		height = darkChannelImage.getHeight();
		BufferedImage orgImage = FileProcess.getInstance().getOrgImage()
				.getBufferedImage();

		int length = width * height;
		int select = (int) (length * 0.001);
		int d = 7;
		double[] grayData = new double[length];
		int[] data = new int[length];
		Pixel[] pixels = new Pixel[length];
		double[] T = new double[length];

		int Ar = 0, Ag = 0, Ab = 0;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = orgImage.getRGB(i, j);
				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = (rgb) & 0xff;
				grayData[i * height + j] = (r * 0.3 + g * 0.59 + b * 0.11) / 255.0;
			}
		}

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
		
		Ar = Ar>MAX_A?MAX_A:Ar;
		Ag = Ag>MAX_A?MAX_A:Ag;
		Ab = Ab>MAX_A?MAX_A:Ab;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				double mr = 255, mg = 255, mb = 255;
				double r, g, b;
				double min = 255;
				for (int k = i - d; k <= i + d; k++) {
					for (int l = j - d; l <= j + d; l++) {
						if (k < 0 || k > height - 1 || l < 0 || l > width - 1) {

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

				double t = 1 - w * min;
				t = t < 0.1 ? 0.1 : t;
				T[i * width + j] = t / 255.0;
			}
		}

		double[] guideData = getGuideData(grayData, T, DEFAULT_SIZE);

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int rgb = orgImage.getRGB(j, i);
				int n = i * width + j;
				double r, g, b;
				r = (double) ((rgb >> 16) & 0xff);
				g = (double) ((rgb >> 8) & 0xff);
				b = (double) ((rgb) & 0xff);

				r = ((r - Ar) / guideData[n]) + Ar;
				g = ((g - Ag) / guideData[n]) + Ag;
				b = ((b - Ab) / guideData[n]) + Ab;

				r = r > 0 ? r : 0;
				g = g > 0 ? g : 0;
				b = b > 0 ? b : 0;

				r = r < 255 ? r : 255;
				g = g < 255 ? g : 255;
				b = b < 255 ? b : 255;

				data[n] = 0xff000000 | ((int) (r) << 16)
						| ((int) (g) << 8) | (int) (b);
			}
		}

		BufferedImage newBufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		newBufferedImage.setRGB(0, 0, width, height, data, 0, width);

		return newBufferedImage;
	}

	private static double[] getGuideData(double[] I, double[] p, int size) {
		int d = size / 2;
		int length = height * width;
		double[] guideData = new double[length];

		double[] meanI = new double[length];
		double[] meanP = new double[length];

		double[] corrI = new double[length];
		double[] corrIp = new double[length];

		double[] varI = new double[length];
		double[] covIp = new double[length];

		double[] a = new double[length];
		double[] b = new double[length];

		double[] meanA = new double[length];
		double[] meanB = new double[length];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				double fi = 0;
				double fp = 0;
				double fii = 0;
				double fip = 0;

				double sum = 0;
				for (int k = i - d; k <= i + d; k++) {
					for (int l = j - d; l <= j + d; l++) {
						if (k < 0 || k > height - 1 || l < 0 || l > width - 1) {
							// nothing
						} else {
							sum++;
							int n = k * width + l;
							fi += I[n];
							fp += p[n];
							fii += I[n] * I[n];
							fip += I[n] * p[n];
						}
					}
				}
				int n = i * width + j;
				double factor = 1.0 / sum;
				meanI[n] = fi * factor;
				meanP[n] = fp * factor;
				corrI[n] = fii * factor;
				corrIp[n] = fip * factor;
			}
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int n = i * width + j;
				varI[n] = corrI[n] - meanI[n] * meanI[n];
				covIp[n] = corrIp[n] - meanI[n] * meanP[n];
			}
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int n = i * width + j;
				a[n] = covIp[n] / (varI[n] + 0.001);
				b[n] = meanP[n] - a[n] * meanI[n];
			}
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				double fa = 0;
				double fb = 0;
				double sum = 0;
				for (int k = i - d; k <= i + d; k++) {
					for (int l = j - d; l <= j + d; l++) {
						if (k < 0 || k > height - 1 || l < 0 || l > width - 1) {
							// nothing
						} else {
							sum++;
							int n = k * width + l;
							fa += a[n];
							fb += b[n];
						}
					}
				}

				int n = i * width + j;
				double factor = 1.0 / sum;
				meanA[n] = fa * factor;
				meanB[n] = fb * factor;
			}
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int n = i * width + j;
				double temp = meanA[n] * I[n] + meanB[n];
				temp *= 255;
				temp = temp < 0 ? 0 : temp;
				guideData[n] = temp > 255 ? 255 : temp;
			}
		}

		return guideData;
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
