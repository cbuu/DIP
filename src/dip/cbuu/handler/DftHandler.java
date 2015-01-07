package dip.cbuu.handler;

import dip.cbuu.common.Complex;
import dip.cbuu.common.IHandler;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.processes.FileProcess;
import dip.cbuu.processes.FourierProcess;
import dip.cbuu.processes.MyProcess;

public class DftHandler extends IHandler{

	public DftHandler(MyFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		MyImage myImage = FileProcess.getInstance().getImage();
		Complex[][] complexs = FourierProcess.getComplexArray(myImage.getBufferedImage());
		Complex[][] fourierData = MyProcess.getInstance().fourierProcess.dft2(complexs, false);
		FileProcess.getInstance().setFourierComplexs(fourierData);
	}

}
