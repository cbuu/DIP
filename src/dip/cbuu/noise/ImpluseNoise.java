package dip.cbuu.noise;

import java.util.Random;

import dip.cbuu.common.INoise;

public class ImpluseNoise extends INoise {
	public static final double P_SALT = 0.8;
	public static final double P_PEPPER = 0.2;

	public ImpluseNoise(int w, int h, boolean isSalt, boolean isPepper) {
		super(w, h);
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				double r = random.nextDouble();
				data[i][j] = -1;
				if (isPepper && isSalt) {
					if (r > P_SALT) {
						data[i][j] = 255;
					}
					if (r < P_PEPPER) {
						data[i][j] = 0;
					}
				}else if (isSalt) {
					if (r > P_SALT) {
						data[i][j] = 255;
					}
				} else if (isPepper) {
					if (r < P_PEPPER) {
						data[i][j] = 0;
					}
				}
			}
		}
	}
}
