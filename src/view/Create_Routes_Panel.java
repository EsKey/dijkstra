package view;

import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import lib.Edge;

public class Create_Routes_Panel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DynamicGui main;

	public Create_Routes_Panel(JFrame where){
		main=(DynamicGui)where;
		Object[][] data = new Object[main.getGraph().getEdges().size()+1][4];
		Iterator<Edge> edges_iterator = main.getGraph().getEdges().iterator();
		
		Object[] header_data ={"ID","A","B","Distance"};
		data[0] = header_data;
		
		int counter = 1;
		
		while(edges_iterator.hasNext()){
			Edge edge = edges_iterator.next();
			Object[] node_data = {edge.getId(),edge.getA().getName(),edge.getB().getName(),edge.getDistance()};
			data[counter] = node_data;
			counter++;
		}

		String[] columnNames = {"ID","A","B","Distance"};
		
		JTable table = new JTable(data, columnNames);

		this.add(table);
	}
}
