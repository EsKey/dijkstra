package lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.google.gson.Gson;

public class Edge {

	private static int idCounter = 0;
	private Node a, b;
	private int a_id, b_id;
	private int distance = 0;
	private int id;
	private Graph graph;
	
	public Edge(int a_id, int b_id, int distance) {
		this.id = idCounter++;
		this.distance = distance;
		this.a_id = a_id;
		this.b_id = a_id;
	}
	
	public Edge(Node a, Node b, int distance) {
		this.id = idCounter++;
		this.a = a;
		this.b = b;
		this.distance = distance;
		this.a_id = a.getId();
		this.b_id = b.getId();
	}
	
	public Edge(int id, Node a, Node b, int distance) {
		this.id = id;
		this.a = a;
		this.b = b;
		this.distance = distance;
		this.a_id = a.getId();
		this.b_id = b.getId();
	}
	
	public Node getA() {
		if(this.a == null){
			this.a = this.graph.getNode(this.a_id);
		}
		return this.a;
	}
	
	public Node getB() {
		if(this.b == null){
			this.b = this.graph.getNode(this.b_id);
		}
		return this.b;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setGraph(Graph graph){
		this.graph = graph;
	}
	
	public static Edge[] getEdgesFromJson(String conf_file_path){
		File edges_file = new File(conf_file_path);
		String edges_json = "";
        try {
            Scanner scanner = new Scanner(edges_file);
            while (scanner.hasNextLine()) {
            	edges_json += scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        Gson gson = new Gson();
		
		Edge[] json_edges = gson.fromJson(edges_json, Edge[].class);
		return json_edges;
	}

}