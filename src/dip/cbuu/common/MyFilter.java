package dip.cbuu.common;

public class MyFilter {

	protected float[][] mask;
	protected int size;
	
	public MyFilter() {
		// TODO Auto-generated constructor stub
	}
	
	public MyFilter(int size) {
		// TODO Auto-generated constructor stub
		mask = new float[size][size];
		this.size = size;
	}
	
	public float[][] getMask() {
		return mask;
	}
	
	public int getSize() {
		return size;
	}
}
