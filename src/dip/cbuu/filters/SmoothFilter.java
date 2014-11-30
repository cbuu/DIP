package dip.cbuu.filters;

import dip.cbuu.common.MyFilter;

public class SmoothFilter extends MyFilter{

	public SmoothFilter(int size) {
		// TODO Auto-generated constructor stub
		super(size);
		double sum = size*size;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mask[i][j] = (double) (1.0/sum);
			}
		}
	}
}
