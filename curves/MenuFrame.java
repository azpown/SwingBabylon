package curves;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import util.FramesController;

public class MenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JEditorPane pane;

	private FramesController controller;

	 protected MenuFrame(FramesController controller) {

		this.controller = controller;

		pane = new JEditorPane();
		pane.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(pane);

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