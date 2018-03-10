package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class FilePanel extends JPanel{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	

	public static final String LIST_FILES = "List Files";
	
	public static final String GET_FILE = "Get File";
	
	private JList<String> files;
	
	private JButton btnShowFiles;
	
	private JButton btnGetFile;
	
	private UserInterface controller;
	
	public FilePanel(UserInterface controller) {
		
		this.controller = controller;
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Files"));
		
		files = new JList();
		files.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		files.setLayoutOrientation(JList.VERTICAL);
		files.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(files);
		listScroller.setPreferredSize(new Dimension(250, 80));
		this.add(listScroller, BorderLayout.CENTER);
		
		JPanel pnlActions = new JPanel();
		pnlActions.setLayout(new GridLayout(1, 2));
		
		btnShowFiles = new JButton(LIST_FILES);
		btnShowFiles.addActionListener(this.controller);
		btnShowFiles.setActionCommand(LIST_FILES);
		pnlActions.add(btnShowFiles);
		
		btnGetFile = new JButton(GET_FILE);
		btnGetFile.addActionListener(this.controller);
		btnGetFile.setActionCommand(GET_FILE);
		pnlActions.add(btnGetFile);
		
		this.add(pnlActions, BorderLayout.SOUTH);
	}

}
