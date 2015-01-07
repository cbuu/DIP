package dip.cbuu.common;

import javax.swing.JFrame;

public abstract class IHandler {
	protected MyFrame frame = null;
	
	protected int size = 0;
	
	public IHandler(MyFrame frame) {
		this.frame = frame;
	}
	public abstract void run();
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
