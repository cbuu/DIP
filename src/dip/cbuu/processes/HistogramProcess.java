package dip.cbuu.processes;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import dip.cbuu.common.MyFrame;
import dip.cbuu.util.PlaneHistogram;

public class HistogramProcess extends MyFrame {
	private final int default_level = 256;

	private BufferedImage bi = null;

	public HistogramProcess() {
		super();
		setVisible(false);
	}

	protected void initFrame() {
		super.initFrame();
		setTitle("Histogram");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getOwner());

		JMenuBar jMenuBar = new JMenuBar();
		setJMenuBar(jMenuBar);

		JMenu menu = new JMenu("Options");
		jMenuBar.add(menu);

		JMenuItem saveItem = new JMenuItem("Save");
		menu.add(saveItem);

		saveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("./res"));
				int result = fileChooser.showSaveDialog(null);
				String savePath = null;
				if (result == JFileChooser.APPROVE_OPTION) {
					savePath = fileChooser.getSelectedFile().getPath();
				}

				try {
					ImageIO.write(bi, "png", new File(savePath));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		setVisible(true);
	}

	public void plot_hist(BufferedImage bufferedImage) {
		initFrame();
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();

		int[] level = new int[256];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = bufferedImage.getRGB(i, j);
				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = (rgb) & 0xff;
				int avg = (int) (r*0.3+g*0.59+b*0.11);
				level[avg]++;

			}
		}

		bi = new PlaneHistogram().paintPlaneHistogram(level);
		showImage(bi);
	}

	public BufferedImage equalize_hist(BufferedImage bufferedImage) {
		initFrame();
		Image newImage = null;

		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		float mn = width * height;
		int[] level = new int[256];
		float[] percent = new float[256];
		int[] data = new int[width * height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = bufferedImage.getRGB(i, j);
				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = (rgb) & 0xff;
				int avg =(int) (r*0.3+g*0.59+b*0.11);
				data[j * width + i] = avg;
				level[avg]++;
			}
		}

		
		
		percent[0] = (float) (level[0]) / mn;
		for (int i = 1; i < percent.length; i++) {
			percent[i] = percent[i - 1] + (float) (level[i]) / mn;
		}

		int[] s = new int[256];
		for (int i = 0; i < s.length; i++) {
			s[i] = (int) (255 * percent[i] + 0.5);
		}
		level = new int[256];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int f = s[data[j * width + i]];
				level[f]++;
				int rgb = 0xff000000 | (f << 16) | (f << 8) | f;
				data[j * width + i] = rgb;
			}
		}

		bi = new PlaneHistogram().paintPlaneHistogram(level);
		showImage(bi);
		
		BufferedImage newBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		newBufferedImage.setRGB(0, 0, width, height, data, 0, width);
		return newBufferedImage;
	}
}
