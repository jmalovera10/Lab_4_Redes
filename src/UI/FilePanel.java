package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class FilePanel extends JPanel implements ActionListener{
	
	private static final String LIST_FILES = "List Files";
	
	private static final String GET_FILE = "Get File";
	
	private JList<String> files;
	
	private JButton btnShowFiles;
	
	private JButton btnGetFile;
	
	public FilePanel() {
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
		btnShowFiles.addActionListener(this);
		btnShowFiles.setActionCommand(LIST_FILES);
		pnlActions.add(btnShowFiles);
		
		btnShowFiles = new JButton(GET_FILE);
		btnShowFiles.addActionListener(this);
		btnShowFiles.setActionCommand(GET_FILE);
		pnlActions.add(btnShowFiles);
		
		this.add(pnlActions, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
