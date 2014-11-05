package dip.cbuu.filters;

import dip.cbuu.common.MyFilter;

public class SharpenFilter extends MyFilter{

	private boolean overlying = false;

	public SharpenFilter(boolean overlying) {
		super(3);
		this.mask[0][0] = -1;this.mask[0][1] = -1 ;this.mask[0][2] = -1;
		this.mask[1][0] = -1;this.mask[1][1] = 8;this.mask[1][2] = -1;
		this.mask[2][0] = -1;this.mask[2][1] = -1 ;this.mask[2][2] = -1;
		this.overlying = overlying;
	}
	
	public boolean isOverlying() {
		return overlying;
	}
}
