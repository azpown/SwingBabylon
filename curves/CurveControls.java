package curves;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import util.*;
import java.awt.event.ItemListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class CurveControls extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private Zoom curveZoom;
	
	private FunChooser curveChooser;
	
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
		
		curveChooser = new FunChooser();
		curveChooser.addObserver(this);
		add(curveChooser.getPanel());
		this.setLayout(new GridLayout(3, 0,5,5));
		this.setBackground(Color.decode("#c0392b"));
		this.setBorder(new EmptyBorder(10,12, 10, 12) );

		JPanel precision = new JPanel();
		JLabel title = new JLabel("Accuracy");
		cb = new JComboBox<Integer>(nStepsChoices);

		precision.add(title);
		precision.add(cb);
		add(precision);
		
		curveZoom.setBackground(Color.decode("#e74c3c"));
		curveChooser.setBackground(Color.decode("#e74c3c"));
		precision.setBackground(Color.decode("#e74c3c"));

		
		//Listener du widget de Précision [CP]
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
			JOptionPane.showMessageDialog(my_f, "xmin doit être inférieur à xmax", "Erreur de saisie.",
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
