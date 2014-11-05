package dip.cbuu.processes;

import java.awt.Image;
import java.awt.image.BufferedImage;

import dip.cbuu.common.MyFilter;
import dip.cbuu.common.MyImage;
import dip.cbuu.filters.SharpenFilter;

public class SpatialFiltering {

	public SpatialFiltering() {
		// TODO Auto-generated constructor stub
	}

	public BufferedImage filter2d(BufferedImage bufferedImage, MyFilter filter) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		int[][] old = MyImage.getData(bufferedImage);
		float[][] mask = filter.getMask();
		int size = filter.getSize();
		int d = size / 2;

		int[] data = new int[width * height];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				double pixel = 0;
				
				for (int k = i - d; k <= i + d; k++) {
					for (int l = j - d; l <= j + d; l++) {
						int val = 0;
						if (k < 0 || k > height - 1 || l < 0 || l > width - 1) {
							val = 0;
						} else {
							// System.out.println(l+" **---** "+k);
							val = old[l][k];
						}
						pixel += val * mask[k - i + d][l - j + d];
					}
				}
				if(filter instanceof SharpenFilter){
					if (((SharpenFilter) filter).isOverlying()) {
						pixel+=old[j][i];	
					}
				}
				int p = (int) pixel;
				
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
