package dip.cbuu.handler;

import java.awt.image.BufferedImage;

import dip.cbuu.common.IHandler;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.processes.AddGaussianNoiseProcess;
import dip.cbuu.processes.AddImpluseNoiseProcess;
import dip.cbuu.processes.FileProcess;

public class AddImpluseHandler extends IHandler{

	public AddImpluseHandler(MyFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		MyImage myImage = FileProcess.getInstance().getImage();
		BufferedImage bufferedImage = myImage.getBufferedImage();
		bufferedImage = AddImpluseNoiseProcess.process(bufferedImage);
		myImage.update(bufferedImage);
		frame.showImage(myImage.getBufferedImage());
	}
	
}
