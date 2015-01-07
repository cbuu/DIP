package dip.cbuu.util;

import dip.cbuu.common.INoise;
import dip.cbuu.noise.GaussianNoise;
import dip.cbuu.noise.ImpluseNoise;

public class NoiseGenerator {

	public static INoise generateNoise(int type, int w, int h) {
		INoise noise = null;
		switch (type) {
		case INoise.GAUSSIAN_NOISE:
			noise = new GaussianNoise(w, h);
			break;
		case INoise.SALT_NOISE:
			noise = new ImpluseNoise(w, h, true, false);
			break;
		case INoise.SALT_PEPPER_NOISE:
			noise = new ImpluseNoise(w, h, true, true);
			break;
		case INoise.PEPPER_NOISE:
			noise = new ImpluseNoise(w, h, false, true);
			break;
		default:
			break;
		}
		return noise;
	}
}
