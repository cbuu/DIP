package dip.cbuu.frame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import dip.cbuu.common.Complex;
import dip.cbuu.common.Constant;
import dip.cbuu.common.IHandler;
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
import dip.cbuu.util.DebugLog;
import dip.cbuu.util.HandlerFactory;

public class MainFrame extends MyFrame{
	
	/**
	 *        By CBUU (DengLi)     SID : 12330065
	 */
	private static final long serialVersionUID = 2005596449790727907L;
	
	private HashMap<Object,Constant> map = new HashMap<Object, Constant>();
	
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
    					private JMenuItem sharpenItem;
    					private JMenuItem sobelXItem;
    					private JMenuItem sobelYItem;
    					private JMenuItem arithmeticItem;
    					private JMenuItem harmonicItem;
    					private JMenuItem geometricItem;
    					private JMenuItem contraHarmonicItem;
    					private JMenuItem minItem;
    					private JMenuItem maxItem;
    					private JMenuItem medianItem;
    					private JMenuItem addGaussianNoiseItem;
    					private JMenuItem addImpluseNoiseItem;
    					private JMenuItem equalizeRGBItem;
    					private JMenuItem RGBEqualizeItem;
    			private JMenu fourierMenu;
    					private JMenuItem dftItem;
    					private JMenuItem idftItem;
    					private JMenuItem spectrumItem;
    					private JMenuItem freq_11_filterItem;
    					private JMenuItem freq_laplacianItem;
    					private JMenuItem fftItem;
    					private JMenuItem ifftItem;
    			private JMenu dehazeMenu;
    					private JMenuItem getDarkChannelItem;
    					private JMenuItem dehazeItem;
		
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
        //menubar.add(processMenu);
        
        fourierMenu = new JMenu("Fourier");
        //menubar.add(fourierMenu);
        
        openItem = new JMenuItem("Open");
        openItem.addActionListener(this);
        menu.add(openItem);
        register(openItem, Constant.OPEN);
        
        saveItem = new JMenuItem("Save"); 
        saveItem.addActionListener(this);
        menu.add(saveItem);
        register(saveItem, Constant.SAVE);
        
        
        arithmeticItem = new JMenuItem("Arithmetic_Mean");
        arithmeticItem.addActionListener(this);
        processMenu.add(arithmeticItem);
        register(arithmeticItem, Constant.ARITHMATIC_MEAN);
        
        geometricItem = new JMenuItem("Geometric_Mean");
        geometricItem.addActionListener(this);
        processMenu.add(geometricItem);
        register(geometricItem, Constant.GEOMETRIC_MEAN);
        
        harmonicItem = new JMenuItem("Harmonic_Mean");
        harmonicItem.addActionListener(this);
        processMenu.add(harmonicItem);
        register(harmonicItem, Constant.HARMONIC_MEAN);
        
        minItem = new JMenuItem("MinFilter");
        minItem.addActionListener(this);
        processMenu.add(minItem);
        register(minItem, Constant.MIN_MEAN);
        
        maxItem = new JMenuItem("MaxFilter");
        maxItem.addActionListener(this);
        processMenu.add(maxItem);
        register(maxItem, Constant.MAX_MEAN);
        
        medianItem = new JMenuItem("MedianFilter");
        medianItem.addActionListener(this);
        processMenu.add(medianItem);
        register(medianItem, Constant.MEDIAN_MEAN);
        
        contraHarmonicItem = new JMenuItem("ContraHarmonic_Mean");
        contraHarmonicItem.addActionListener(this);
        processMenu.add(contraHarmonicItem);
        register(contraHarmonicItem, Constant.CONTRAHARMONIC_MEAN);
        
        addGaussianNoiseItem = new JMenuItem("AddGaussianNoise");
        addGaussianNoiseItem.addActionListener(this);
        processMenu.add(addGaussianNoiseItem);
        register(addGaussianNoiseItem, Constant.ADD_GAUSSIAN_NOISE);
        
        addImpluseNoiseItem = new JMenuItem("AddSaltPepperNoise");
        addImpluseNoiseItem.addActionListener(this);
        processMenu.add(addImpluseNoiseItem);
        register(addImpluseNoiseItem, Constant.ADD_IMPLUSE_NOISE);
        
        equalizeRGBItem = new JMenuItem("Equalize_RGB");
        equalizeRGBItem.addActionListener(this);
        processMenu.add(equalizeRGBItem);
        register(equalizeRGBItem, Constant.EQUALIZE_RGB);
        
        RGBEqualizeItem = new JMenuItem("RGB_Equalize");
        RGBEqualizeItem.addActionListener(this);
        processMenu.add(RGBEqualizeItem);
        register(RGBEqualizeItem, Constant.RGB_EQUALIZE);
        
        
        quantizationItem = new JMenuItem("Quantize");
        //processMenu.add(quantizationItem);
        
        scaleItem = new JMenuItem("Scalize");
        //processMenu.add(scaleItem);
        
        gradientItem = new JMenuItem("Gradient");
        //processMenu.add(gradientItem);
        
        plot_histItem = new JMenuItem("Plot_Hist");
        plot_histItem.addActionListener(this);
        //processMenu.add(plot_histItem);
        
        equalize_histItem = new JMenuItem("Equalize_Hist");
        equalize_histItem.addActionListener(this);
        //processMenu.add(equalize_histItem);
        
        patch_extractionItem = new JMenuItem("PatchExtraction");
        patch_extractionItem.addActionListener(this);
        //processMenu.add(patch_extractionItem);
        
        sharpenItem = new JMenuItem("Sharpen");
        sharpenItem.addActionListener(this);
        //processMenu.add(sharpenItem);
        
        sobelXItem = new JMenuItem("Sobel-X");
        sobelXItem.addActionListener(this);
        //processMenu.add(sobelXItem);
        
        sobelYItem = new JMenuItem("Sobel-Y");
        sobelYItem.addActionListener(this);
        //processMenu.add(sobelYItem);
        
        dftItem = new JMenuItem("Dft");
        dftItem.addActionListener(this);
        fourierMenu.add(dftItem);
        register(dftItem, Constant.DFT);
        
        idftItem = new JMenuItem("Idft");
        idftItem.addActionListener(this);
        fourierMenu.add(idftItem);
        register(idftItem, Constant.IDFT);
        
        spectrumItem = new JMenuItem("Spectum");
        spectrumItem.addActionListener(this);
        fourierMenu.add(spectrumItem);
        register(spectrumItem, Constant.SPECTRUM);
        
        freq_11_filterItem = new JMenuItem("AvgFilter_11");
        freq_11_filterItem.addActionListener(this);
        fourierMenu.add(freq_11_filterItem);
        
        freq_laplacianItem = new JMenuItem("Laplacian");
        freq_laplacianItem.addActionListener(this);
        fourierMenu.add(freq_laplacianItem);
        
        fftItem = new JMenuItem("Fft");
        fftItem.addActionListener(this);
        fourierMenu.add(fftItem);
        register(fftItem, Constant.FFT);
        
        
        ifftItem = new JMenuItem("Ifft");
        ifftItem.addActionListener(this);
        fourierMenu.add(ifftItem);
        register(ifftItem, Constant.IFFT);
        
        
        dehazeMenu = new JMenu("Dehaze");
        menubar.add(dehazeMenu);
        
        getDarkChannelItem = new JMenuItem("GetDarkChannel");
        getDarkChannelItem.addActionListener(this);
        dehazeMenu.add(getDarkChannelItem);
        register(getDarkChannelItem, Constant.GET_DARKCHANNEL);
        
        dehazeItem = new JMenuItem("Dehaze");
        dehazeItem.addActionListener(this);
        dehazeMenu.add(dehazeItem);
        register(dehazeItem, Constant.DEHAZE);
        
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    Object item = e.getSource();
		Constant id = map.get(item);
		if (id!=null) {
			IHandler handler = HandlerFactory.createHandlerById(id, this);
			if (handler!=null) {
				handler.run();
			}
		}

//		if (item==plot_histItem) {
//			MyProcess.getInstance().histogramProcess.plot_hist(FileProcess.getInstance().getImage().getBufferedImage());
//		}else if (item==equalize_histItem) {
//			MyImage myImage = FileProcess.getInstance().getImage();
//			myImage.update(MyProcess.getInstance().histogramProcess.equalize_hist(myImage.getBufferedImage()));
//			showImage(myImage.getBufferedImage());
//		}else if (item==patch_extractionItem) {
//			MyProcess.getInstance().patchExtraction.view_as_window(FileProcess.getInstance().getImage().getBufferedImage(), 96, 64);
//		}else if (item==sharpenItem) {
//			MyImage myImage = FileProcess.getInstance().getImage();
//			myImage.update(MyProcess.getInstance().spatialFiltering.filter2d(myImage.getBufferedImage(), new SharpenFilter(true)));
//			showImage(myImage.getBufferedImage());
//		}else if (item==sobelXItem) {
//			MyImage myImage = FileProcess.getInstance().getImage();
//			myImage.update(MyProcess.getInstance().spatialFiltering.filter2d(myImage.getBufferedImage(), new SobelFilter(SobelFilter.X)));
//			showImage(myImage.getBufferedImage());
//		}else if (item==sobelYItem) {
//			MyImage myImage = FileProcess.getInstance().getImage();
//			myImage.update(MyProcess.getInstance().spatialFiltering.filter2d(myImage.getBufferedImage(), new SobelFilter(SobelFilter.Y)));
//			showImage(myImage.getBufferedImage());
//		}else if (item==freq_11_filterItem) {
//			MyImage myImage = FileProcess.getInstance().getImage();
//			int x = myImage.getWidth();
//			int y = myImage.getHeight();
//			Complex[][] complexs = FourierProcess.getComplexArray(myImage.getBufferedImage());
//			Complex[][] fourierData = MyProcess.getInstance().fourierProcess.dft2(complexs, false);
//			MyFilter filter = new SmoothFilter(11);
//			Complex[][] freq_filter = MyProcess.getInstance().fourierProcess.getFilter(filter, x, y);
//			Complex[][] newComplexs = MyProcess.getInstance().fourierProcess.dot(fourierData, freq_filter, x, y);
//			newComplexs = MyProcess.getInstance().fourierProcess.dft2(newComplexs, true);
//			BufferedImage orgImage = MyProcess.getInstance().fourierProcess.getSimilarOrgImage(newComplexs, x, y);
//			myImage.update(orgImage);
//			showImage(myImage.getBufferedImage());
//			FileProcess.getInstance().setImage(myImage);
//			FileProcess.getInstance().setFourierComplexs(newComplexs);
//		}else if (item==freq_laplacianItem) {
//			MyImage myImage = FileProcess.getInstance().getImage();
//			int x = myImage.getWidth();
//			int y = myImage.getHeight();
//			Complex[][] complexs = FourierProcess.getComplexArray(myImage.getBufferedImage());
//			Complex[][] fourierData = MyProcess.getInstance().fourierProcess.dft2(complexs, false);
//			MyFilter filter = new SharpenFilter(false);
//			Complex[][] freq_filter = MyProcess.getInstance().fourierProcess.getFilter(filter, x, y);
//			Complex[][] newComplexs = MyProcess.getInstance().fourierProcess.dot(fourierData, freq_filter, x, y);
//			newComplexs = MyProcess.getInstance().fourierProcess.dft2(newComplexs, true);
//			BufferedImage orgImage = MyProcess.getInstance().fourierProcess.getSimilarOrgImage(newComplexs, x, y);
//			myImage.update(orgImage);
//			showImage(myImage.getBufferedImage());
//			FileProcess.getInstance().setImage(myImage);
//			FileProcess.getInstance().setFourierComplexs(newComplexs);
//		}
		
	}
	
	public void register(Object obj,Constant id){
		if (obj!=null) {
			map.put(obj, id);
		}
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
