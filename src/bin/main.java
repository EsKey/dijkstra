package bin;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import view.Gui;
import view.DynamicGui;

import lib.*;

public class main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Node london = new Node("London");
		Node berlin = new Node("Berlin");
		Node wien = new Node("Wien");
		Node rom = new Node("Rom");
		Node paris = new Node(4, "Paris");
		
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(london);
		nodes.add(berlin);
		nodes.add(wien);
		nodes.add(rom);
		nodes.add(paris);
		
		List<Edge> edges = new ArrayList<Edge>();
		edges.add(new Edge(london,berlin,100));
		edges.add(new Edge(berlin,wien,200));
		edges.add(new Edge(rom,paris,500));
		
		Graph graph = new Graph(nodes,edges);
		
		Iterator<Edge> edges_iterator = graph.getEdges().iterator();
		
		while(edges_iterator.hasNext()){
			Edge edge = edges_iterator.next();
			System.out.println("From "+edge.getA().getName()+" to "+edge.getB().getName()+ " distance "+edge.getDistance());
		}
		
		//Gui gui = new Gui(graph);
		DynamicGui gui = new DynamicGui(graph);
		gui.init();
		
	}

}
