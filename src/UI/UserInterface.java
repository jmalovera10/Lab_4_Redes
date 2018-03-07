package UI;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class UserInterface extends JFrame{

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	
	private ActionPanel actPanel;
	
	private FilePanel filePanel;
	
	public UserInterface() {
		setLayout(new BorderLayout());
		setTitle("TCP Client");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		actPanel = new ActionPanel();
		this.add(actPanel, BorderLayout.SOUTH);
		
		filePanel = new FilePanel();
		this.add(filePanel, BorderLayout.EAST);
		
		setLocationRelativeTo(null);
		pack();
	}
	
	public static void main(String[] args) {
		UserInterface i = new UserInterface();
		i.setVisible(true);
	}
	
}
