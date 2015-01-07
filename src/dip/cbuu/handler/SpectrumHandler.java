package dip.cbuu.handler;

import java.awt.image.BufferedImage;

import dip.cbuu.common.Complex;
import dip.cbuu.common.IHandler;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.processes.FileProcess;
import dip.cbuu.processes.MyProcess;

public class SpectrumHandler extends IHandler{

	public SpectrumHandler(MyFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		MyImage myImage = FileProcess.getInstance().getImage();
		Complex[][] complexs = FileProcess.getInstance().getFourierComplexs();
		BufferedImage spectrumImage = MyProcess.getInstance().fourierProcess.getSpectrum(complexs);
		myImage.update(spectrumImage);
		frame.showImage(myImage.getBufferedImage());
		FileProcess.getInstance().setImage(myImage);
	}

}
