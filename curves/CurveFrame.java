package curves;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.FramesController;

/**
 * A class  to represent the variations of some function  in some interval.
 * Allows some control on the accuracy of this representation
 */

/**
 * @author casteran
 */
public class CurveFrame extends MenuFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** the graphic part of the display */
	CurveTracer tracer;

	/** information bar */
	CurveInfos infos;

	/** various commands */
	CurveControls controls;

	/**
	 * Builds a top-level window from the variations of a function
	 * 
	 * @see FunctionVariations
	 */
	public CurveFrame(FunctionVariations fvar, FramesController controller) {
		super(controller);
		tracer = new CurveTracer(fvar);
		infos = new CurveInfos(fvar);
		controls = new CurveControls(fvar, this);

		JPanel mainPane = new JPanel(new BorderLayout());

		mainPane.add(tracer, BorderLayout.CENTER);
		mainPane.add(infos, BorderLayout.SOUTH);
		mainPane.add(controls, BorderLayout.EAST);

		tracer.addMouseListener(new MouseAdapter() {
			CurveFrame cf = CurveFrame.this;

			public void mouseEntered(MouseEvent e) {
				cf.infos.xmouse.setText("x = " + cf.tracer.realX(e.getX()));
				cf.infos.ymouse.setText("y = " + cf.tracer.realY(e.getY()));
				cf.infos.repaint();
			}

			public void mouseExited(MouseEvent e) {
				cf.infos.xmouse.setText("");
				cf.infos.ymouse.setText("");
				cf.infos.repaint();
			}
		});
		tracer.addMouseMotionListener(new MouseMotionAdapter() {
			CurveFrame cf = CurveFrame.this;

			public void mouseMoved(MouseEvent e) {
				cf.infos.xmouse.setText("x = " + cf.tracer.realX(e.getX()));
				cf.infos.ymouse.setText("y = " + cf.tracer.realY(e.getY()));
				//cf.infos.repaint();
			}

			public void mouseDragged(MouseEvent e) {
				cf.infos.xmouse.setText("x = " + cf.tracer.realX(e.getX()));
				cf.infos.ymouse.setText("y = " + cf.tracer.realY(e.getY()));
				cf.infos.repaint();
			}
		});

		fvar.tabulate(controls.currentPrecision()); // MAJ pr�cisions
		setContentPane(mainPane);
		pack();
		infos.update();
		setVisible(true);
	}
}
