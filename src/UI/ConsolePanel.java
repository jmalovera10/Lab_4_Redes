package UI;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsolePanel extends JPanel{
	
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea consoleText;
	
	private String msg;
	
	public ConsolePanel() {
		this.setBorder(BorderFactory.createTitledBorder("Console"));
		this.setLayout(new BorderLayout());
		
		msg = ">> ";
		
		consoleText = new JTextArea(msg);
		consoleText.setEditable(false);
		Font font = consoleText.getFont();
		float size = font.getSize() + 3.0f;
		consoleText.setFont( font.deriveFont(size) );
		consoleText.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(consoleText);
		this.add(scroll, BorderLayout.CENTER);
	}
	
	public void updateConsole(String msg) {
		this.msg += msg+"\n>> ";
		consoleText.setText(this.msg);
	}
}
