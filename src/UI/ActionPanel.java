package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ActionPanel extends JPanel{

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	public static final String CONNECT = "CONNECT";
	
	public static final String DISCONNECT = "DISCONNECT";
	
	private JButton btnConnect;
	
	private JButton btnDisconnect;
	
	private UserInterface controller;
	
	public ActionPanel(UserInterface controller) {
		
		this.controller = controller;
		
		this.setLayout(new GridLayout(1, 2));
		this.setBorder(BorderFactory.createTitledBorder("Connectivity Options"));
		
		btnConnect = new JButton(CONNECT);
		btnConnect.addActionListener(this.controller);
		btnConnect.setActionCommand(CONNECT);
		this.add(btnConnect);
		
		btnDisconnect = new JButton(DISCONNECT);
		btnDisconnect.addActionListener(this.controller);
		btnDisconnect.setActionCommand(DISCONNECT);
		this.add(btnDisconnect);
	}

}
