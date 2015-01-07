package dip.cbuu.handler;

import java.awt.image.BufferedImage;

import dip.cbuu.common.IHandler;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.processes.ArithmaticMeanProcess;
import dip.cbuu.processes.EqualizeRGBProcess;
import dip.cbuu.processes.FileProcess;
import dip.cbuu.util.DebugLog;

public class EqualizeRGBHandler extends IHandler{

	public EqualizeRGBHandler(MyFrame frame) {
		super(frame);
	}

	@Override
	public void run() {
		MyImage myImage = FileProcess.getInstance().getImage();
		BufferedImage bufferedImage = myImage.getBufferedImage();
		bufferedImage = EqualizeRGBProcess.process(bufferedImage);
		myImage.update(bufferedImage);
		frame.showImage(myImage.getBufferedImage());
	}

}
