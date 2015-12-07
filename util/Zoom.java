package util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Zoom extends Observable {
	
	private double bornes[]= new double[2];
	
	protected JPanel zoom;
	
	private JButton intervalButton;
	
	private JTextField xMinJText;
	private JTextField xMaxJText;

	private JLabel xMinJLabel;
	private JLabel xMaxJLabel;
	
	public Zoom(double min, double max){
		
		//Vérification des bornes
		if (min < max){
			bornes[0]=min; bornes[1] = max;
		}	
		//Mise à défaut [0 ; 10] si bornes incorrectes
		else{
			bornes[0]=0.; bornes[1] = 10.;
		}
		
		zoom = new JPanel();
		
		xMinJText = new JTextField(Double.toString(bornes[0]),5);
		xMaxJText = new JTextField(Double.toString(bornes[1]),5);
		xMaxJLabel = new JLabel("x max");
		xMinJLabel = new JLabel("x min");
		intervalButton = new JButton("Valider");

		zoom.add(xMinJLabel);
		zoom.add(xMinJText);
		zoom.add(xMaxJLabel);
		zoom.add(xMaxJText);
		zoom.add(intervalButton);
		
		intervalButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				bornes[0] = Double.parseDouble(xMinJText.getText());
				bornes[1] = Double.parseDouble(xMaxJText.getText());
				setChanged();
				notifyObservers(bornes);
			}
		});
	}
	
	public JPanel getPanel(){
		return zoom;
	}
	
}
