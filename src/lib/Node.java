package lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.google.gson.Gson;

public class Node {
	
	private static int idCounter = 0;

	private String name;
	private int id;
	private int position_x = 0;
	private int position_y = 0;
	
	public Node(String name) {
		this.id = idCounter++;
		this.name = name;
	}
	
	public static Node[] getNodesFromJson(String conf_file_path){
		File nodes_file = new File(conf_file_path);
		String nodes_json = "";
        try {
            Scanner scanner = new Scanner(nodes_file);
            while (scanner.hasNextLine()) {
            	nodes_json += scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
		Gson gson = new Gson();
		
		Node[] json_nodes = gson.fromJson(nodes_json, Node[].class);
		return json_nodes;
	}
	
	public Node(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}

	public int getPosition_y() {
		return position_y;
	}

	public void setPosition_y(int position_y) {
		this.position_y = position_y;
	}

	public int getPosition_x() {
		return position_x;
	}

	public void setPosition_x(int position_x) {
		this.position_x = position_x;
	}
}
