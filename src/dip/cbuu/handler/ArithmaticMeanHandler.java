package dip.cbuu.handler;

import java.awt.Frame;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import dip.cbuu.common.IHandler;
import dip.cbuu.common.IListenerEvent;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.filters.SmoothFilter;
import dip.cbuu.frame.InputFrame;
import dip.cbuu.processes.ArithmaticMeanProcess;
import dip.cbuu.processes.FileProcess;
import dip.cbuu.processes.MyProcess;
import dip.cbuu.util.DebugLog;

public class ArithmaticMeanHandler extends IHandler{

	public ArithmaticMeanHandler(MyFrame frame) {
		super(frame);
	}

	@Override
	public void run() {
		new InputFrame(this,new IListenerEvent() {
			
			@Override
			public void play() {
				MyImage myImage = FileProcess.getInstance().getImage();
				BufferedImage bufferedImage = myImage.getBufferedImage();
				bufferedImage = ArithmaticMeanProcess.process(bufferedImage,getSize());
				myImage.update(bufferedImage);
				frame.showImage(myImage.getBufferedImage());
			}
		});	
	}

}
