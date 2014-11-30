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

import dip.cbuu.common.Complex;
import dip.cbuu.common.MyFilter;
import dip.cbuu.common.MyFrame;
import dip.cbuu.common.MyImage;
import dip.cbuu.filters.SharpenFilter;
import dip.cbuu.filters.SmoothFilter;
import dip.cbuu.filters.SobelFilter;
import dip.cbuu.processes.FileProcess;
import dip.cbuu.processes.FourierProcess;
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
    			private JMenu fourierMenu;
    					private JMenuItem fourierItem;
    					private JMenuItem inverseFourierItem;
    					private JMenuItem spectrumItem;
    					private JMenuItem freq_11_filterItem;
    					private JMenuItem freq_laplacianItem;
		
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
        
        fourierMenu = new JMenu("Fourier");
        menubar.add(fourierMenu);
        
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
        
        fourierItem = new JMenuItem("Fourier");
        fourierItem.addActionListener(this);
        fourierMenu.add(fourierItem);
        
        inverseFourierItem = new JMenuItem("InverseFourier");
        inverseFourierItem.addActionListener(this);
        fourierMenu.add(inverseFourierItem);
        
        spectrumItem = new JMenuItem("Spectum");
        spectrumItem.addActionListener(this);
        fourierMenu.add(spectrumItem);
        
        freq_11_filterItem = new JMenuItem("AvgFilter_11");
        freq_11_filterItem.addActionListener(this);
        fourierMenu.add(freq_11_filterItem);
        
        freq_laplacianItem = new JMenuItem("Laplacian");
        freq_laplacianItem.addActionListener(this);
        fourierMenu.add(freq_laplacianItem);
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
		}else if (item==fourierItem) {
			MyImage myImage = FileProcess.getInstance().getImage();
			int x = myImage.getWidth();
			int y = myImage.getHeight();
			Complex[][] complexs = FourierProcess.getComplexArray(myImage.getBufferedImage());
			Complex[][] fourierData = MyProcess.getInstance().fourierProcess.dft2(complexs, false);
			FileProcess.getInstance().setFourierComplexs(fourierData);
		} else if (item==inverseFourierItem) {
			MyImage myImage = FileProcess.getInstance().getImage();
			int x = myImage.getWidth();
			int y = myImage.getHeight();
			Complex[][] complexs = FileProcess.getInstance().getFourierComplexs();
			Complex[][] fourierData = MyProcess.getInstance().fourierProcess.dft2(complexs, true);
			BufferedImage orgImage = MyProcess.getInstance().fourierProcess.getSimilarOrgImage(fourierData, x, y);
			myImage.update(orgImage);
			showImage(myImage.getBufferedImage());
			FileProcess.getInstance().setImage(myImage);
			FileProcess.getInstance().setFourierComplexs(fourierData);
		}else if (item == spectrumItem) {
			MyImage myImage = FileProcess.getInstance().getImage();
			int x = myImage.getWidth();
			int y = myImage.getHeight();
			Complex[][] complexs = FileProcess.getInstance().getFourierComplexs();
			BufferedImage spectrumImage = MyProcess.getInstance().fourierProcess.getSpectrum(complexs, x, y);
			myImage.update(spectrumImage);
			showImage(myImage.getBufferedImage());
			FileProcess.getInstance().setImage(myImage);
		}else if (item==freq_11_filterItem) {
			MyImage myImage = FileProcess.getInstance().getImage();
			int x = myImage.getWidth();
			int y = myImage.getHeight();
			Complex[][] complexs = FourierProcess.getComplexArray(myImage.getBufferedImage());
			Complex[][] fourierData = MyProcess.getInstance().fourierProcess.dft2(complexs, false);
			MyFilter filter = new SmoothFilter(11);
			Complex[][] freq_filter = MyProcess.getInstance().fourierProcess.getFilter(filter, x, y);
			Complex[][] newComplexs = MyProcess.getInstance().fourierProcess.dot(fourierData, freq_filter, x, y);
			newComplexs = MyProcess.getInstance().fourierProcess.dft2(newComplexs, true);
			BufferedImage orgImage = MyProcess.getInstance().fourierProcess.getSimilarOrgImage(newComplexs, x, y);
			myImage.update(orgImage);
			showImage(myImage.getBufferedImage());
			FileProcess.getInstance().setImage(myImage);
			FileProcess.getInstance().setFourierComplexs(newComplexs);
		}else if (item==freq_laplacianItem) {
			MyImage myImage = FileProcess.getInstance().getImage();
			int x = myImage.getWidth();
			int y = myImage.getHeight();
			Complex[][] complexs = FourierProcess.getComplexArray(myImage.getBufferedImage());
			Complex[][] fourierData = MyProcess.getInstance().fourierProcess.dft2(complexs, false);
			MyFilter filter = new SharpenFilter(false);
			Complex[][] freq_filter = MyProcess.getInstance().fourierProcess.getFilter(filter, x, y);
			Complex[][] newComplexs = MyProcess.getInstance().fourierProcess.dot(fourierData, freq_filter, x, y);
			newComplexs = MyProcess.getInstance().fourierProcess.dft2(newComplexs, true);
			BufferedImage orgImage = MyProcess.getInstance().fourierProcess.getSimilarOrgImage(newComplexs, x, y);
			myImage.update(orgImage);
			showImage(myImage.getBufferedImage());
			FileProcess.getInstance().setImage(myImage);
			FileProcess.getInstance().setFourierComplexs(newComplexs);
		}
		
	}
	
	public static void main(String[] args) {
		JFrame mainFrame = new MainFrame();
	}
}
