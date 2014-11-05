package dip.cbuu.common;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dip.cbuu.processes.FileProcess;

public class MyFrame extends JFrame implements ActionListener{
	/**
	 *    By CBUU(DengLi)  SID:12330065
	 */
	private static final long serialVersionUID = -5388518203909633659L;
	
	protected final int FRAMEWIDTH = 600;
	protected final int FRAMEHEIGHT = 600;
	
    protected JLabel container = null;
	
	public MyFrame() {
		this.setSize(FRAMEWIDTH,FRAMEHEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(this.getOwner());
		//this.setVisible(true);
	}
	
	protected void initFrame() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	protected void showImage(BufferedImage bufferedImage){
		if(bufferedImage!=null){
			if(container!=null){
				remove(container);
			}
			container = new JLabel();
			this.add(container);
			container.setIcon(new ImageIcon(bufferedImage));
			this.pack();
		}
	}
	
}
