package dip.cbuu.common;

public class Complex {
	public double r, i;
	
	public Complex() {
		this.r = 0;
		this.i = 0;
	}

	public Complex(double r, double i) {
		this.r = r;
		this.i = i;
	}
	
	public double abs(){
		return Math.sqrt(r*r+i*i);
	}
}
