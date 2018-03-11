package UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clients.TCP_Client;

public class UserInterface extends JFrame implements ActionListener{

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	
	private ActionPanel actPanel;
	
	private FilePanel filePanel;
	
	private ConsolePanel consolePanel;
	
	private TCP_Client client;
	
	public UserInterface() {
		
		client = new TCP_Client();
		
		setLayout(new BorderLayout());
		setTitle("TCP Client");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		actPanel = new ActionPanel(this);
		this.add(actPanel, BorderLayout.SOUTH);
		
		filePanel = new FilePanel(this);
		this.add(filePanel, BorderLayout.EAST);
		
		consolePanel = new ConsolePanel();
		this.add(consolePanel, BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
		pack();
	}
	
	public static void main(String[] args) {
		UserInterface i = new UserInterface();
		i.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals(ActionPanel.CONNECT)) {
			if(client.isConnected()) {
				consolePanel.updateConsole("Client is already connected!");
			}
			else {
				JTextField host = new JTextField();
				JTextField port = new JTextField();
				final JComponent[] inputs = new JComponent[] {
				        new JLabel("Host"),
				        host,
				        new JLabel("Port"),
				        port
				};
				int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
				    boolean connected = client.connect(host.getText(),port.getText());
					if(connected) {
						consolePanel.updateConsole("Client connected succesfully!");
						filePanel.enableActions();
					}
					else consolePanel.updateConsole("Connection failed, try again later");
				} 
			}
		}
		else if(command.equals(ActionPanel.DISCONNECT)) {
			if(client.isConnected()) {
				boolean connected = client.disconnect();
				if(!connected)consolePanel.updateConsole("Client disconnected succesfully!");
				else consolePanel.updateConsole("Something went wrong, disconnection was forced");
				filePanel.disableActions();
			}
			else {
				consolePanel.updateConsole("Client is already disconnected!");
			}
		}
		else if(command.equals(FilePanel.GET_FILE)) {
			
		}
		else if(command.equals(FilePanel.LIST_FILES)) {
			try {
				consolePanel.updateConsole("TO SERVER: HELO");
				String[] files = (String[]) client.getAvailableFiles().toArray();
				for (String string : files) {
					consolePanel.updateConsole("FILE: "+string);
				}
				filePanel.updateFiles(files);
			}
			catch(Exception ex) {
				ex.printStackTrace();
				consolePanel.updateConsole("IO Error, please try again later");
			}
		}
	}
	
}
