package dip.cbuu.noise;

import java.util.Random;

import dip.cbuu.common.INoise;

public class GaussianNoise extends INoise{
	
	public static final double variance = 40.0;
	public static final double mean = 0.0;

	public GaussianNoise(int w, int h) {
		super(w, h);
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				
				data[i][j] = (int) (variance*random.nextGaussian()+mean);
			}
		}
	}
	
	
}
