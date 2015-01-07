package dip.cbuu.frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import dip.cbuu.common.IHandler;
import dip.cbuu.common.IListenerEvent;

public class InputFrame extends JFrame implements ActionListener{
	private JTextField content = null;
	
	private JButton yesButton = null;
	
	private IHandler handler = null;
	
	private IListenerEvent event = null;
	
	public InputFrame(IHandler handler,IListenerEvent event) {
		this.handler = handler;
		this.event = event;
		
		this.setLayout(new GridLayout(2, 1));
		this.setSize(75,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(this.getOwner());
		this.setVisible(true);
		
		content = new JTextField("size");
		
		yesButton = new JButton("yes");
		yesButton.addActionListener(this);
		
		this.add(content);
		this.add(yesButton);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj==yesButton) {
			
			int size = Integer.parseInt(content.getText().toString());
			handler.setSize(size);
			event.play();
			this.dispose();
		}
	}
}
