package dip.cbuu.common;

public class INoise {
	public static final int GAUSSIAN_NOISE = 0;
	public static final int SALT_NOISE = 1;
	public static final int SALT_PEPPER_NOISE = 2;
	public static final int PEPPER_NOISE = 3;
	
	protected int[][] data;
	
	public INoise(int w,int h) {
		data = new int[w][h];
	}
	
	public int[][] getData() {
		return data;
	}
}
