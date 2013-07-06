package view;

import java.util.Iterator;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import lib.Node;

public class Create_Nodes_Panel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DynamicGui main;
	
	public Create_Nodes_Panel(JFrame where){
		main = (DynamicGui) where;
		Object[][] data = new Object[main.getGraph().getNodes().size()+1][3];
		Iterator<Node> nodes_iterator = main.getGraph().getNodes().iterator();
		
		Object[] header_data ={"ID","Name"};
		data[0] = header_data;
		
		int counter = 1;
		while(nodes_iterator.hasNext()){
			Node node = nodes_iterator.next();
			Object[] node_data = {node.getId(),node.getName()};
			data[counter] = node_data;
			counter++;
		}
		
		String[] columnNames = {"ID","Name"};
		
		JTable table = new JTable(data, columnNames);

		this.add(table);
	}
}
