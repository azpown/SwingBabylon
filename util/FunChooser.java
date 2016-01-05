package util;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FunChooser extends Observable{
	
	protected JPanel fc;
	private final JLabel fcName;
	private JButton browseB;
	private JLabel fcText;
	String fileContent;
	
	public FunChooser(){
		fc = new JPanel();
		fcText = new JLabel("Load from file");
		fcName = new JLabel("No file choosen");
		browseB = new JButton ("Open");
		fc.setLayout(new GridLayout(3,0,5,5));
		fc.add(fcText);
		fc.add(browseB);
		fc.add(fcName);

		fc.setBorder(new EmptyBorder(15,15, 15, 15) );
		
		browseB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter
			    		("TEXT FILES", "txt", "text");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(fc);
			    if(returnVal == JFileChooser.APPROVE_OPTION)
			    {
			      try{
			    	fileContent = readFile(chooser.getSelectedFile().getName());
			    	System.out.println(fileContent);
			      } catch (IOException IOE){
			    	  System.err.println("Caught IOException: "
	                           +  IOE.getMessage());
			      } finally {
			          if (fileContent != null) {
							setChanged();
							//notifyObservers(fileContent);
			          } 
			          else {
			            System.out.println("File not read correctly.");
			            fileContent = "ERROR";
						setChanged();
						//notifyObservers(fileContent);
						fileContent = null;
			          }
			      }
			      
			    }
			}
		});
	}
	
	public JPanel getPanel(){
		return fc;
	}
	
	public String readFile(String filePath)
			 throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String content;
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		       line = br.readLine();
		    }
		    
		    content = sb.toString();
		} finally {
		    br.close();
		}
		return content;
	}
	
	public void setBackground(Color col){
		fc.setBackground(col);
	}
	
}
