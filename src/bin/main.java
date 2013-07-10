package bin;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import view.DynamicGui;
import lib.*;

public class main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws PropertyVetoException 
	 */
		
	public static void main(String[] args) throws IOException, PropertyVetoException{
		
		Node[] json_nodes = Node.getNodesFromJson("config/nodes.json");
		
		Edge[] json_edges = Edge.getEdgesFromJson("config/edges.json");
		
		Graph graph = new Graph(json_nodes,json_edges);	

		DynamicGui gui = new DynamicGui(graph);
		gui.init();
//		LinkedList <Node>path = DijkstraAlgorithm.calcPath(graph.getNode(3), graph.getNode(1), graph);
//		
//		Iterator <Node>pathiterator = path.iterator();
//		while(pathiterator.hasNext()){
//			System.out.println(pathiterator.next().getName());
//		}
	}

}
