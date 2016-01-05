package util;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Zoom extends Observable {
	
	private double bornes[]= new double[2];
	
	protected JPanel zoom;
	protected JPanel b1;
	protected JPanel b2;
	
	
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
		b1 = new JPanel();
		b2 = new JPanel();
		
		xMinJText = new JTextField(Double.toString(bornes[0]),5);
		xMaxJText = new JTextField(Double.toString(bornes[1]),5);
		xMaxJLabel = new JLabel("x max");
		xMinJLabel = new JLabel("x min");
		intervalButton = new JButton("Validate");

		b1.add(xMinJLabel);
		b1.add(xMinJText);
		b2.add(xMaxJLabel);
		b2.add(xMaxJText);
		zoom.add(b1);
		zoom.add(b2);
		zoom.add(intervalButton);
		zoom.setLayout(new GridLayout(3, 0));
		zoom.setBorder(new EmptyBorder(15,15, 15, 15) );
		
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
	
	public void setNewBornes(double xmin, double xmax){
		bornes[0]=xmin;
		bornes[1]=xmax;
	}
	
	public void setBackground(Color col){
		zoom.setBackground(col);
		b1.setBackground(col);
		b2.setBackground(col);
	}
	
}
