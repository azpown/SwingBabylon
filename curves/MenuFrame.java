package curves;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import util.FramesController;

public class MenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final String LINK_OPEN_STRING = "Open ";
	public static final String LINK_DEFAULT_STRING = " ";

	public static final String FAILURE_URL = "file:Failure.html";

	private JEditorPane pane;
	private JLabel label;

	private FramesController controller;

	 protected MenuFrame(FramesController controller) {

		this.controller = controller;

		pane = new JEditorPane();
		pane.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(pane);

		label = new JLabel(LINK_DEFAULT_STRING);

		pane.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent evt) {
				// Mouse enters on the link
				if (evt.getEventType() == HyperlinkEvent.EventType.ENTERED)
					label.setText(LINK_OPEN_STRING + evt.getURL().toString());
				// Mouse exits the link
				if (evt.getEventType() == HyperlinkEvent.EventType.EXITED)
					label.setText(LINK_DEFAULT_STRING);
			}
		});

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu(CurveApplication.MENU_FILE);
		menuBar.add(menu);

		createMenuItem(menu, CurveApplication.MENU_ITEM_NEW, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				MenuFrame.this.controller.createFrame();
			}
		});

		createMenuItem(menu, CurveApplication.MENU_ITEM_CLOSE, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				MenuFrame.this.controller.deleteFrame(MenuFrame.this);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MenuFrame.this.controller.deleteFrame(MenuFrame.this);
			}
		});

		createMenuSeparator(menu);

		createMenuItem(menu, CurveApplication.MENU_ITEM_QUIT, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				MenuFrame.this.controller.quit();
			}
		});

		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(label, BorderLayout.SOUTH);
		setContentPane(contentPane);
	}

	private void createMenuItem(JMenu menu, String name, ActionListener action) {
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.addActionListener(action);
		menu.add(menuItem);
	}

	private void createMenuSeparator(JMenu menu) {
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.lightGray);
		menu.add(separator);
	}
}