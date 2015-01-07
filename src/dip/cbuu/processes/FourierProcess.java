package dip.cbuu.processes;

import java.awt.Frame;
import java.awt.image.BufferedImage;

import dip.cbuu.common.Complex;
import dip.cbuu.common.MyFilter;
import dip.cbuu.common.MyImage;

public class FourierProcess {
	private double max_s;

	public FourierProcess() {
		max_s = 1.0 / Math.log(256);
	}

	public Complex[][] fft2(Complex[][] complexs, boolean isInverse) {
		boolean isPad = false;
		int x = complexs.length;
		int y = complexs[0].length;
		if (!isInverse) {
			int nx = x, ny = y;
			int ux = (int) ((Math.log((double) x) / Math.log(2.0)));
			int uy = (int) ((Math.log((double) y) / Math.log(2.0)));
			if (x > Math.pow(2, ux)) {
				nx = (int) Math.pow(2, ux + 1);
				isPad = true;
			}
			if (y > Math.pow(2, uy)) {
				isPad = true;
				ny = (int) Math.pow(2, uy + 1);
			}
			if (isPad) {
				Complex[][] oldComplexs = complexs;
				complexs = new Complex[nx][ny];
				for (int i = 0; i < nx; i++) {
					for (int j = 0; j < ny; j++) {
						Complex complex = new Complex(0, 0);
						if (i < x && j < y) {
							complex.r = oldComplexs[i][j].r;
							complex.i = oldComplexs[i][j].i;
						}
						complexs[i][j] = complex;
					}
				}
			}
			x = nx;
			y = ny;
		}
		

		for (int i = 0; i < x; i++) {
			if (!isInverse) {
				complexs[i] = fft1(complexs[i]);
			}else {
				complexs[i] = ifft1(complexs[i]);
			}

		}

		for (int j = 0; j < y; j++) {
			Complex[] cs = new Complex[x];
			for (int i = 0; i < x; i++) {
				cs[i] = complexs[i][j];
			}
			if (!isInverse) {
				cs = fft1(cs);
			}else {
				cs = ifft1(cs);
			}
			
			for (int i = 0; i < x; i++) {
				complexs[i][j] = cs[i];
			}
		}
		double a = 0;
		
		if (!isInverse) {
			a = 1.0 / (double) (x * y); 
		}
		
		if (!isInverse) {
			for (int i = 0; i < y/2; i++) {
				for (int j = 0; j < x; j++) {
					Complex t = new Complex(complexs[j][i].r*a, complexs[j][i].i*a);
					complexs[j][i] = new Complex(complexs[j][i+y/2].r*a, complexs[j][i+y/2].i*a);
					complexs[j][i+y/2] = t;
				}
			}
			
			for (int i = 0; i < y; i++) {
				for (int j = 0; j < x/2; j++) {
					Complex t = new Complex(complexs[j][i].r, complexs[j][i].i);
					complexs[j][i] = new Complex(complexs[j+x/2][i].r, complexs[j+x/2][i].i);
					complexs[j+x/2][i] = t;
				}
			}
		}
		

		return complexs;
	}
	
	public Complex[] ifft1(Complex[] complexs){ 
		int N = complexs.length;
		double s = 1.0 / (double) N;
		Complex[] y = new Complex[N];
		for (int i = 0; i < N; i++) {
            y[i] = complexs[i].conjugate();
        }

        // compute forward FFT
        y = fft1(y);

        // take conjugate again
        for (int i = 0; i < N; i++) {
            y[i] = y[i].conjugate();
        }

        // divide by N
//        for (int i = 0; i < N; i++) {
//            y[i].r = y[i].r;
//            y[i].i = y[i].i;
//        }

        return y;
	}

	private Complex[] fft1(Complex[] data) {
		int M = data.length;
		double s = 1.0 / (double) M; // 乘法代替除法
		double W = -2 * Math.PI * s;
		// System.out.println(x+"  "+y);
		if (M == 1) {
			return new Complex[] { data[0] };
		}

		Complex[] even = new Complex[M / 2];
		for (int i = 0; i < even.length; i++) {
			even[i] = data[i * 2];
		}

		Complex[] fftEven = fft1(even);

		Complex[] odd = new Complex[M / 2];
		for (int i = 0; i < odd.length; i++) {
			odd[i] = data[i * 2 + 1];
		}

		Complex[] fftOdd = fft1(odd);

		Complex[] complexs = new Complex[M];
		for (int i = 0; i < M / 2; i++) {
			Complex w = new Complex(Math.cos(W * i), Math.sin(W * i));
			Complex fo = new Complex(fftOdd[i].r * w.r - fftOdd[i].i * w.i,
					fftOdd[i].r * w.i + fftOdd[i].i * w.r);
			complexs[i] = new Complex((fftEven[i].r + fo.r),
					(fftEven[i].i + fo.i));
			complexs[i + M / 2] = new Complex((fftEven[i].r - fo.r),
					(fftEven[i].i - fo.i));
		}
		return complexs;
	}
	
	

//	static public Complex[][] getOrgScale(Complex[][] s) {
//		int x = s.length;
//		int y = s[0].length;
//		Complex[][] orgComplexs = new Complex[x][y];
//		
//		for (int i = 0; i < x; i++) {
//			for (int j = 0; j < y; j++) {
//				orgComplexs[i][j] = new Complex(s[i][j].r * a, s[i][j].i * a);
//			}
//		}
//		return orgComplexs;
//	}

	// 二维傅里叶变换
	public Complex[][] dft2(Complex[][] complexs, boolean isInverse) {

		int x = complexs.length;
		int y = complexs[0].length;

		for (int i = 0; i < x; i++) {
			complexs[i] = dft1(complexs[i], i, true, isInverse);
		}

		for (int j = 0; j < y; j++) {
			Complex[] cs = new Complex[x];
			for (int i = 0; i < x; i++) {
				cs[i] = complexs[i][j];
			}
			cs = dft1(cs, j, false, isInverse);
			for (int i = 0; i < x; i++) {
				complexs[i][j] = cs[i];
			}
		}

		return complexs;
	}

	// 一维傅里叶变换
	public Complex[] dft1(Complex[] data, int h, boolean isCentral,
			boolean isInverse) {
		int M = data.length;
		double s = 1.0 / (double) M; // 乘法代替除法
		Complex[] complexs = new Complex[M];
		double S = 2 * Math.PI * s;
		for (int i = 0; i < M; i++) {
			double sumR = 0;
			double sumI = 0;
			Complex complex = new Complex();
			for (int j = 0; j < M; j++) {
				double R = data[j].r;
				double I = data[j].i;
				if (isCentral && (!isInverse)) {
					if ((j + h) % 2 == 1) {
						R = -R;
						I = -I;
					}
				}
				double ec = Math.cos(S * i * j);
				double es = -Math.sin(S * i * j);
				if (isInverse) {
					es = -es;
				}
				sumR += R * ec - I * es;
				sumI += R * es + I * ec;
			}
			if (isInverse) {
				complex.r = sumR;
				complex.i = sumI;
			} else {
				complex.r = sumR * s;
				complex.i = sumI * s;
			}
			if (isInverse && (!isCentral)) {
				if ((i + h) % 2 == 1) {
					complex.r = -complex.r;
				}
			}
			complexs[i] = complex;
		}
		return complexs;
	}

	// 实矩阵转换到复数矩阵
	static public Complex[][] getComplexArray(BufferedImage bufferedImage) {
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		int[][] data = MyImage.getData(bufferedImage);

		Complex[][] complexs = new Complex[w][h];

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				complexs[i][j] = new Complex(data[i][j], 0);
			}
		}

		return complexs;
	}

	// Scale～
	private int scale(double val) {
		return (int) (val * max_s * 255);
	}

	// 由复数矩阵获得频谱图
	public BufferedImage getSpectrum(Complex[][] complexs) {
		int x = complexs.length;
		int y = complexs[0].length;
		BufferedImage bufferedImage = new BufferedImage(x, y,
				BufferedImage.TYPE_3BYTE_BGR);
		int[] data = new int[x * y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				double log = Math.log(complexs[i][j].abs() + 1);
				int f = scale(log);
				int rgb = 0xff000000 | (f << 16) | (f << 8) | f;
				data[j * x + i] = rgb;
			}
		}

		bufferedImage.setRGB(0, 0, x, y, data, 0, x);

		return bufferedImage;
	}

	// 反变换后复数矩阵取实部获得原图
	public BufferedImage getSimilarOrgImage(Complex[][] complexs, int x, int y) {
		BufferedImage bufferedImage = new BufferedImage(x, y,
				BufferedImage.TYPE_3BYTE_BGR);
		int[] data = new int[x * y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int f = (int) complexs[i][j].r;
				if (f < 0) {
					f = 0;
				} else if (f > 255) {
					f = 255;
				}
				int rgb = 0xff000000 | (f << 16) | (f << 8) | f;
				data[j * x + i] = rgb;
			}
		}
		bufferedImage.setRGB(0, 0, x, y, data, 0, x);
		return bufferedImage;
	}

	// 由时域滤波到频域滤波
	public Complex[][] getFilter(MyFilter filter, int x, int y) {

		int size = filter.getSize();

		double[][] mask = filter.getMask();
		Complex[][] complexs = new Complex[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				Complex complex = new Complex();
				if (i < size && j < size) {
					complex.r = mask[size - i - 1][size - j - 1];
				}
				complexs[i][j] = complex;
			}
		}

		int origin = size / 2;

		double sx = (double) (1.0 / (x));
		double sy = (double) (1.0 / (y));
		double Sx = 2 * Math.PI * sx * -origin;
		double Sy = 2 * Math.PI * sy * -origin;

		Complex[][] newComplexs = dft2(complexs, false);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				double R = newComplexs[i][j].r;
				double I = newComplexs[i][j].i;
				double Re = Math.cos(Sx * i + Sy * j);
				double Ie = -Math.sin(Sx * i + Sy * j);
				newComplexs[i][j].r = R * Re - I * Ie;
				newComplexs[i][j].i = R * Ie + I * Re;
			}
		}
		return newComplexs;
	}

	public Complex[][] dot(Complex[][] F, Complex[][] H, int x, int y) {
		Complex[][] complexs = new Complex[x][y];
		int S = x * y;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {

				double R = F[i][j].r;
				double I = F[i][j].i;
				double Re = H[i][j].r;
				double Ie = H[i][j].i;
				complexs[i][j] = new Complex((R * Re - I * Ie) * S, (R * Ie + I
						* Re)
						* S);
			}
		}
		return complexs;
	}

}
