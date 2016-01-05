package util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FunChooser extends Observable{
	
	protected JPanel fc;
	private final JLabel fcText;
	private JButton browseB;
	String fileContent;
	
	public FunChooser(){
		fc = new JPanel();
		fcText = new JLabel("Load from file");
		browseB = new JButton ("Open");
		
		fc.add(fcText);
		fc.add(browseB);
		
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
}
