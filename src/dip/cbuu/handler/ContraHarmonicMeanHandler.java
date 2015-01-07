package dip.cbuu.handler;

import java.awt.image.BufferedImage;

import dip.cbuu.common.IHandler;
import dip.cbuu.common.IListenerEvent;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.frame.InputFrame;
import dip.cbuu.processes.ContraHarmonicMeanProcess;
import dip.cbuu.processes.FileProcess;

public class ContraHarmonicMeanHandler extends IHandler{

	public static final double Q = -1.5;
	
	public ContraHarmonicMeanHandler(MyFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		new InputFrame(this,new IListenerEvent() {
			
			@Override
			public void play() {
				MyImage myImage = FileProcess.getInstance().getImage();
				BufferedImage bufferedImage = myImage.getBufferedImage();
				bufferedImage = ContraHarmonicMeanProcess.process(bufferedImage,
						getSize(),Q);
				myImage.update(bufferedImage);
				frame.showImage(myImage.getBufferedImage());
			}
		});
	}

	

}
