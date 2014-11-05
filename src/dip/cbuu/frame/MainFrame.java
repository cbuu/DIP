package dip.cbuu.frame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.filters.SharpenFilter;
import dip.cbuu.filters.SmoothFilter;
import dip.cbuu.filters.SobelFilter;
import dip.cbuu.processes.FileProcess;
import dip.cbuu.processes.HistogramProcess;
import dip.cbuu.processes.MyProcess;

public class MainFrame extends MyFrame{
	
	/**
	 *        By CBUU (DengLi)     SID : 12330065
	 */
	private static final long serialVersionUID = 2005596449790727907L;
	
	private JMenuBar menubar;
    		private JMenu menu;
    					private JMenuItem openItem;
    					private JMenuItem saveItem;
    			private JMenu processMenu;
    					private JMenuItem quantizationItem;
    					private JMenuItem scaleItem;
    					private JMenuItem gradientItem;
    					private JMenuItem plot_histItem;
    					private JMenuItem equalize_histItem;
    					private JMenuItem patch_extractionItem;
    					private JMenuItem smooth3Item;
    					private JMenuItem smooth7Item;
    					private JMenuItem smooth11Item;
    					private JMenuItem sharpenItem;
    					private JMenuItem sobelXItem;
    					private JMenuItem sobelYItem;
		
	public MainFrame() {
		super();
		setVisible(true);
		initFrame();
	}
	
	protected void initFrame(){
		  super.initFrame();
		  menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        menu = new JMenu("File");
        menubar.add(menu);
        
        processMenu = new JMenu("Process");
        menubar.add(processMenu);
        
        openItem = new JMenuItem("Open");
        openItem.addActionListener(this);
        menu.add(openItem);
        
        saveItem = new JMenuItem("Save");
        saveItem.addActionListener(this);
        menu.add(saveItem);
        
        quantizationItem = new JMenuItem("Quantize");
        processMenu.add(quantizationItem);
        
        scaleItem = new JMenuItem("Scalize");
        processMenu.add(scaleItem);
        
        gradientItem = new JMenuItem("Gradient");
        processMenu.add(gradientItem);
        
        plot_histItem = new JMenuItem("Plot_Hist");
        plot_histItem.addActionListener(this);
        processMenu.add(plot_histItem);
        
        equalize_histItem = new JMenuItem("Equalize_Hist");
        equalize_histItem.addActionListener(this);
        processMenu.add(equalize_histItem);
        
        patch_extractionItem = new JMenuItem("PatchExtraction");
        patch_extractionItem.addActionListener(this);
        processMenu.add(patch_extractionItem);
        
        smooth3Item = new JMenuItem("Smooth_3X3");
        smooth3Item.addActionListener(this);
        processMenu.add(smooth3Item);
        
        smooth7Item = new JMenuItem("Smooth_7X7");
        smooth7Item.addActionListener(this);
        processMenu.add(smooth7Item);
        
        smooth11Item = new JMenuItem("Smooth_11X11");
        smooth11Item.addActionListener(this);
        processMenu.add(smooth11Item);
        
        sharpenItem = new JMenuItem("Sharpen");
        sharpenItem.addActionListener(this);
        processMenu.add(sharpenItem);
        
        sobelXItem = new JMenuItem("Sobel-X");
        sobelXItem.addActionListener(this);
        processMenu.add(sobelXItem);
        
        sobelYItem = new JMenuItem("Sobel-Y");
        sobelYItem.addActionListener(this);
        processMenu.add(sobelYItem);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		super.actionPerformed(e);
		
		JMenuItem item = (JMenuItem)e.getSource();
		
		if(item==openItem){
			FileProcess.getInstance().open(this);
			BufferedImage bufferedImage = FileProcess.getInstance().getImage().getBufferedImage();
			if (bufferedImage!=null) {
				showImage(bufferedImage);
			}
		}else if (item==saveItem) {
			FileProcess.getInstance().save();
		}else if (item==plot_histItem) {
			MyProcess.getInstance().histogramProcess.plot_hist(FileProcess.getInstance().getImage().getBufferedImage());
		}else if (item==equalize_histItem) {
			MyImage myImage = FileProcess.getInstance().getImage();
			myImage.update(MyProcess.getInstance().histogramProcess.equalize_hist(myImage.getBufferedImage()));
			showImage(myImage.getBufferedImage());
		}else if (item==patch_extractionItem) {
			MyProcess.getInstance().patchExtraction.view_as_window(FileProcess.getInstance().getImage().getBufferedImage(), 96, 64);
		}else if (item==smooth3Item) {
			MyImage myImage = FileProcess.getInstance().getImage();
			myImage.update(MyProcess.getInstance().spatialFiltering.filter2d(myImage.getBufferedImage(), new SmoothFilter(3)));
			showImage(myImage.getBufferedImage());
		}else if (item==sharpenItem) {
			MyImage myImage = FileProcess.getInstance().getImage();
			myImage.update(MyProcess.getInstance().spatialFiltering.filter2d(myImage.getBufferedImage(), new SharpenFilter(true)));
			showImage(myImage.getBufferedImage());
		}else if (item==sobelXItem) {
			MyImage myImage = FileProcess.getInstance().getImage();
			myImage.update(MyProcess.getInstance().spatialFiltering.filter2d(myImage.getBufferedImage(), new SobelFilter(SobelFilter.X)));
			showImage(myImage.getBufferedImage());
		}else if (item==smooth7Item) {
			MyImage myImage = FileProcess.getInstance().getImage();
			myImage.update(MyProcess.getInstance().spatialFiltering.filter2d(myImage.getBufferedImage(), new SmoothFilter(7)));
			showImage(myImage.getBufferedImage());
		}else if (item==smooth11Item) {
			MyImage myImage = FileProcess.getInstance().getImage();
			myImage.update(MyProcess.getInstance().spatialFiltering.filter2d(myImage.getBufferedImage(), new SmoothFilter(11)));
			showImage(myImage.getBufferedImage());
		}else if (item==sobelYItem) {
			MyImage myImage = FileProcess.getInstance().getImage();
			myImage.update(MyProcess.getInstance().spatialFiltering.filter2d(myImage.getBufferedImage(), new SobelFilter(SobelFilter.Y)));
			showImage(myImage.getBufferedImage());
		}
		
	}
	
	public static void main(String[] args) {
		JFrame mainFrame = new MainFrame();
	}
}
