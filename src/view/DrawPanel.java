package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
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
	private static DijkstraAlgorithm da;
	private Image map;
	private Image not;
	private Image pos;
	private Image start;
	private Image zwischen;
	private Image ziel;
	
	
	@SuppressWarnings("static-access")
	public void setImage(Image m, Image not, Image pos) throws IOException{
//		this.start = ImageIO.read(pfeile[0]);
//		this.zwischen = ImageIO.read(pfeile[1]);
//		this.ziel = ImageIO.read(pfeile[2]);
		this.da = new DijkstraAlgorithm(DynamicGui.graph);
		this.map = m;
		this.not = not;
		this.pos = pos;
		this.xAxe = (Toolkit.getDefaultToolkit().getScreenSize().width-m.getWidth(this))/2;
	}
	
	@SuppressWarnings("static-access")
	@Override
	protected void paintComponent (Graphics g){
		System.out.println("jab:" + DynamicGui.start.getSelectedItem().toString() + " und " + DynamicGui.ziel.getSelectedItem().toString());
		super.paintComponent(g);
		g.drawImage(map, this.xAxe, 55, this);
		Node temp=null;
		int sum=0;
		int i=0;
		int x = 0, y=0;
		if (!DynamicGui.start.getSelectedItem().toString().equals("") &&
				!DynamicGui.ziel.getSelectedItem().toString().equals("")){
			this.path = DijkstraAlgorithm.calcPath
					(DynamicGui.graph.getNode(getNodeId(DynamicGui.start.getSelectedItem().toString())),
					 DynamicGui.graph.getNode(getNodeId(DynamicGui.ziel.getSelectedItem().toString())),
					 DynamicGui.graph);
			try{
				
				for (Node node : this.path){
					x = node.getPosition_x();
					y = node.getPosition_y();
					g.setColor(Color.MAGENTA);
					if (i>0) {
						g.drawLine(x+13, y+43, temp.getPosition_x()+13, temp.getPosition_y()+43);
						sum+=da.getDistance(temp, node);
					}
					g.drawImage(pos, x, y, this);
					temp=node;
					i++;
				}
			}catch (NullPointerException e){
				g.drawImage(not,
						(int)((Toolkit.getDefaultToolkit().getScreenSize().getWidth()-not.getWidth(this))/2),
						(int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight()-not.getHeight(this))/2), this);
			}
		}
		DynamicGui.distance.setText(String.valueOf(sum) + " km");
	}
	
	public int getNodeId(String node){
		int id=0;
		for(int i=1;i<=DynamicGui.graph.getNodes().size();i++){
			System.out.println(i + ":" );
			if (DynamicGui.graph.getNode(i).getName().equals(node)){
				id = DynamicGui.graph.getNode(i).getId();
				System.out.println(id);
			}	
		}
		return id;
	}
	
}
