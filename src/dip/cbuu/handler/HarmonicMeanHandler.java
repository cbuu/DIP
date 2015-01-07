package dip.cbuu.handler;

import java.awt.image.BufferedImage;

import dip.cbuu.common.IHandler;
import dip.cbuu.common.IListenerEvent;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.filters.SmoothFilter;
import dip.cbuu.frame.InputFrame;
import dip.cbuu.processes.FileProcess;
import dip.cbuu.processes.HarmonicMeanProcess;
import dip.cbuu.processes.MyProcess;

public class HarmonicMeanHandler extends IHandler {

	public HarmonicMeanHandler(MyFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		new InputFrame(this, new IListenerEvent() {

			@Override
			public void play() {
				MyImage myImage = FileProcess.getInstance().getImage();
				BufferedImage bufferedImage = myImage.getBufferedImage();
				bufferedImage = HarmonicMeanProcess.process(bufferedImage,
						getSize());
				myImage.update(bufferedImage);
				frame.showImage(myImage.getBufferedImage());
			}
		});

	}

}
