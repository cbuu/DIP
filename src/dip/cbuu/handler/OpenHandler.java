package dip.cbuu.handler;

import java.awt.image.BufferedImage;

import dip.cbuu.common.IHandler;
import dip.cbuu.common.MyFrame;
import dip.cbuu.processes.FileProcess;

public class OpenHandler extends IHandler{

	public OpenHandler(MyFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		FileProcess.getInstance().open(frame);
		BufferedImage bufferedImage = FileProcess.getInstance().getImage().getBufferedImage();
		if (bufferedImage!=null) {
			frame.showImage(bufferedImage);
		}
	}

}
