package dip.cbuu.handler;

import java.awt.image.BufferedImage;

import dip.cbuu.common.IHandler;
import dip.cbuu.common.IListenerEvent;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.frame.InputFrame;
import dip.cbuu.processes.ArithmaticMeanProcess;
import dip.cbuu.processes.FileProcess;
import dip.cbuu.processes.MaxProcess;

public class MaxHandler extends IHandler{

	public MaxHandler(MyFrame frame) {
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
				bufferedImage = MaxProcess.process(bufferedImage,getSize());
				myImage.update(bufferedImage);
				frame.showImage(myImage.getBufferedImage());
			}
		});
	}

}
