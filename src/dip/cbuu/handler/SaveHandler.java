package dip.cbuu.handler;

import java.awt.image.BufferedImage;

import dip.cbuu.common.IHandler;
import dip.cbuu.common.MyFrame;
import dip.cbuu.processes.FileProcess;

public class SaveHandler extends IHandler{

	public SaveHandler(MyFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		FileProcess.getInstance().save();
	}

}
