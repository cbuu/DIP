package dip.cbuu.filters;

import dip.cbuu.common.MyFilter;

public class SobelFilter extends MyFilter{

	public final static int X = 0;
	public final static int Y = 1;
	
	public SobelFilter(int xy) {
		super(3);
		switch (xy) {
		case X:
			this.mask[0][0] = -1;this.mask[0][1] = -2 ;this.mask[0][2] = -1;
			this.mask[1][0] = 0;this.mask[1][1] = 0;this.mask[1][2] = 0;
			this.mask[2][0] = 1;this.mask[2][1] = 2 ;this.mask[2][2] = 1;
			break;
		case Y:
			this.mask[0][0] = -1;this.mask[0][1] = 0 ;this.mask[0][2] = 1;
			this.mask[1][0] = -2;this.mask[1][1] = 0;this.mask[1][2] = 2;
			this.mask[2][0] = -1;this.mask[2][1] = 0 ;this.mask[2][2] = 1;
			break;
		}
	}
}
