package dip.cbuu.handler;

import java.awt.image.BufferedImage;

import dip.cbuu.common.IHandler;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.processes.AddGaussianNoiseProcess;
import dip.cbuu.processes.ArithmaticMeanProcess;
import dip.cbuu.processes.FileProcess;

public class AddGaussianNoiseHandler extends IHandler{

	public AddGaussianNoiseHandler(MyFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		MyImage myImage = FileProcess.getInstance().getImage();
		BufferedImage bufferedImage = myImage.getBufferedImage();
		bufferedImage = AddGaussianNoiseProcess.process(bufferedImage);
		myImage.update(bufferedImage);
		frame.showImage(myImage.getBufferedImage());
	}

}
