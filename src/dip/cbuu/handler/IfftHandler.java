package dip.cbuu.handler;

import java.awt.image.BufferedImage;

import dip.cbuu.common.Complex;
import dip.cbuu.common.IHandler;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.processes.FileProcess;
import dip.cbuu.processes.MyProcess;

public class IfftHandler extends IHandler{

	public IfftHandler(MyFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		MyImage myImage = FileProcess.getInstance().getImage();
		int x = myImage.getWidth();
		int y = myImage.getHeight();
		Complex[][] complexs = FileProcess.getInstance().getFourierComplexs();
		Complex[][] fourierData = MyProcess.getInstance().fourierProcess.fft2(complexs, true);
		BufferedImage orgImage = MyProcess.getInstance().fourierProcess.getSimilarOrgImage(fourierData, x, y);
		myImage.update(orgImage);
		frame.showImage(myImage.getBufferedImage());
		FileProcess.getInstance().setImage(myImage);
		FileProcess.getInstance().setFourierComplexs(fourierData);
		
	}

}
