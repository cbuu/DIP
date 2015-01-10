package dip.cbuu.handler;

import java.awt.image.BufferedImage;

import dip.cbuu.common.IHandler;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.processes.BetterDehazeProcess;
import dip.cbuu.processes.FileProcess;
import dip.cbuu.processes.GetDarkChannelProcess;

public class BetterDehazeHandler extends IHandler{

	public BetterDehazeHandler(MyFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		MyImage myImage = FileProcess.getInstance().getImage();
		BufferedImage bufferedImage = myImage.getBufferedImage();
		
		bufferedImage = BetterDehazeProcess.process(bufferedImage);
		
		
		myImage.update(bufferedImage);
		frame.showImage(myImage.getBufferedImage());
	}

}
