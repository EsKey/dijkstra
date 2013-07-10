package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import lib.DijkstraAlgorithm;
import lib.Node;


public class DrawPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int xAxe;
	private static LinkedList<Node> path;
	
	private Image map;
	private Image not;
	@SuppressWarnings("static-access")
	public void setImage(Image m, Image not){
		this.map = m;
		this.not = not;
		this.xAxe = Toolkit.getDefaultToolkit().getScreenSize().width/4;
	}
	
	@SuppressWarnings("static-access")
	@Override
	protected void paintComponent (Graphics g){
		System.out.println("jab:" + DynamicGui.start.getSelectedItem().toString() + " und " + DynamicGui.ziel.getSelectedItem().toString());
		super.paintComponent(g);
		g.drawImage(map, this.xAxe, 5, this);
		if (!DynamicGui.start.getSelectedItem().toString().equals("") &&
				!DynamicGui.ziel.getSelectedItem().toString().equals("")){
			this.path = DijkstraAlgorithm.calcPath
					(DynamicGui.graph.getNode(getNodeId(DynamicGui.start.getSelectedItem().toString())),
					 DynamicGui.graph.getNode(getNodeId(DynamicGui.ziel.getSelectedItem().toString())),
					 DynamicGui.graph);
			try{
				for (Node node : this.path){
					int x = node.getPosition_x();
					int y = node.getPosition_y();
					g.drawRoundRect(x, y, 4, 4, 1, 1);
				}
			}catch (NullPointerException e){
				g.drawImage(not, this.xAxe,
						(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4), this);
			}
		}
	}
	
	public int getNodeId(String node){
		int id=0;
		for(int i=1;i<DynamicGui.graph.getNodes().size();i++){
			System.out.println(i + ":" );
			if (DynamicGui.graph.getNode(i).getName().equals(node)){
				id = DynamicGui.graph.getNode(i).getId();
				System.out.println(id);
			}	
		}
		return id;
	}
	
}
