package curves;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import util.*;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.prism.paint.Color;

class CurveControls extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private Zoom curveZoom;
	
	protected final static Integer nStepsChoices[] = { 1, 2, 3, 4, 5, 10, 20, 40, 80, 160, 320, 640 };

	private JComboBox<Integer> cb;
	
	private CurveFrame my_f;
	private FunctionVariations my_var;

	CurveControls(final FunctionVariations var, final CurveFrame f) {
		super();
		
		my_f = f;
		my_var = var;
		curveZoom = new Zoom(var.getXmin(),var.getXmax());
		curveZoom.addObserver(this);
		add(curveZoom.getPanel());
		
		JPanel precision = new JPanel();
		JLabel title = new JLabel("Pr�cision");
		cb = new JComboBox<Integer>(nStepsChoices);

		precision.add(title);
		precision.add(cb);
		add(precision);
		
		//Listener du widget de Pr�cision [CP]
		cb.addItemListener((ItemListener) (new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					var.tabulate(CurveControls.this.currentPrecision());
					f.infos.update();
					f.repaint();
				}
			}

		}));
		cb.setSelectedIndex(nStepsChoices.length / 2);

	}

	int currentPrecision() {
		return nStepsChoices[cb.getSelectedIndex()];
	}
	
	public void update(Observable o, Object arg){
		double bornes[] = (double[])arg;
		if (bornes[0] > bornes[1]) {
			JOptionPane.showMessageDialog(my_f, "xmin doit �tre inf�rieur � xmax", "Erreur de saisie.",
					JOptionPane.WARNING_MESSAGE);
			curveZoom.setNewBornes(my_var.getXmin(),my_var.getXmax());
		}

		else {
			my_var.changeInterval(bornes[0], bornes[1]);
			my_var.tabulate(CurveControls.this.currentPrecision());
			my_f.infos.update();
			my_f.repaint();
			
		}
	}

}
