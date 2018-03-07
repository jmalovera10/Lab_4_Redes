package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ActionPanel extends JPanel implements ActionListener{

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	private static final String CONNECT = "CONNECT";
	
	private static final String DISCONNECT = "DISCONNECT";
	
	private JButton btnConnect;
	
	private JButton btnDisconnect;
	
	public ActionPanel() {
		
		this.setLayout(new GridLayout(1, 2));
		this.setBorder(BorderFactory.createTitledBorder("Connectivity Options"));
		
		btnConnect = new JButton(CONNECT);
		btnConnect.addActionListener(this);
		btnConnect.setActionCommand(CONNECT);
		this.add(btnConnect);
		
		btnDisconnect = new JButton(DISCONNECT);
		btnDisconnect.addActionListener(this);
		btnDisconnect.setActionCommand(DISCONNECT);
		this.add(btnDisconnect);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals(CONNECT)) {
			
		}else if(action.equals(DISCONNECT)) {
			
		}
		
	}
}
