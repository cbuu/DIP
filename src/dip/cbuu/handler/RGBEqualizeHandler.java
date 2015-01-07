package dip.cbuu.handler;

import java.awt.image.BufferedImage;

import dip.cbuu.common.IHandler;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.processes.EqualizeRGBProcess;
import dip.cbuu.processes.FileProcess;
import dip.cbuu.processes.RGBEqualizeProcess;

public class RGBEqualizeHandler extends IHandler{

	public RGBEqualizeHandler(MyFrame frame) {
		super(frame);
	}

	@Override
	public void run() {
		MyImage myImage = FileProcess.getInstance().getImage();
		BufferedImage bufferedImage = myImage.getBufferedImage();
		bufferedImage = RGBEqualizeProcess.process(bufferedImage);
		myImage.update(bufferedImage);
		frame.showImage(myImage.getBufferedImage());
	}

}
