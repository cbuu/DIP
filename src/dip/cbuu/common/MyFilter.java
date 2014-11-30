package dip.cbuu.common;

public class MyFilter {

	protected double[][] mask;
	protected int size;
	
	public MyFilter() {
		// TODO Auto-generated constructor stub
	}
	
	public MyFilter(int size) {
		// TODO Auto-generated constructor stub
		mask = new double[size][size];
		this.size = size;
	}
	
	public double[][] getMask() {
		return mask;
	}
	
	public int getSize() {
		return size;
	}
}
