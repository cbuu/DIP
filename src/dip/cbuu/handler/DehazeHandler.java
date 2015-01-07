package dip.cbuu.handler;

import java.awt.image.BufferedImage;

import dip.cbuu.common.IHandler;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.processes.DehazeProcess;
import dip.cbuu.processes.FileProcess;
import dip.cbuu.processes.GetDarkChannelProcess;
import dip.cbuu.util.DebugLog;

public class DehazeHandler extends IHandler{

	public DehazeHandler(MyFrame frame) {
		super(frame);
	}

	@Override
	public void run() {
		MyImage myImage = FileProcess.getInstance().getImage();
		BufferedImage bufferedImage = myImage.getBufferedImage();
		
		bufferedImage = DehazeProcess.process(bufferedImage);
		
		myImage.update(bufferedImage);
		frame.showImage(myImage.getBufferedImage());
	}

}
