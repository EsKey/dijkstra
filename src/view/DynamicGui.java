/**
 * 
 */
package view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lib.Graph;

@SuppressWarnings("serial")
public class DynamicGui extends JFrame{
	
	static JComboBox start;
	static JComboBox ziel;
	static Object[] nodes;
	static Graph graph;
	static JDesktopPane desktop = new JDesktopPane();
	static DrawPanel map_panel = new DrawPanel();
	static DrawPanel temp;
	static JInternalFrame show_map;
	static JButton route = new JButton("Route");
	static DynamicGui main;
	
	
	public DynamicGui(Graph graph){
		DynamicGui.graph=graph;
		main = this;
	}
	
	public Graph getGraph(){
		return DynamicGui.graph;
	}	
	
	@SuppressWarnings("static-access")
	public void init() throws IOException, PropertyVetoException{
		String[] felder = {"Data","Lists"};
		String[][] subs = {{"0","Beenden"},{"1","Nodes"},{"1","Routes"}};
		this.setTitle("Dijkstra - created by Soiron, Strauch, Kiepert - all rights reserved (R)");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(new Menu(felder,subs, desktop, this));
		this.show_map = new JInternalFrame("Map",true,false,true,true);
		show_map.setSize(this.getSize());
		show_map.add(create_map_panel());
		show_map.setVisible(true);
		show_map.addMouseListener(new MouseAction());
		desktop.add(show_map);
		desktop.setVisible(true);
		this.add(desktop);
		this.setVisible(true);
	}
	
	public JPanel create_map_panel() throws IOException{
		DynamicGui.route.setName("Route");
		DynamicGui.nodes = new Object[this.getGraph().getNodes().size()+1];
		DynamicGui.nodes[0]="";
		for (int i=1;i<this.getGraph().getNodes().size()+1;i++){
			nodes[i]=this.getGraph().getNodes().get(i-1).getName();
		}
		DynamicGui.start = new JComboBox(DynamicGui.nodes);
		DynamicGui.start.setName("start");
		DynamicGui.start.setSelectedIndex(0);
		DynamicGui.start.addItemListener(new ComboChange());
		DynamicGui.ziel = new JComboBox(DynamicGui.nodes);
		DynamicGui.ziel.setSelectedIndex(0);
		DynamicGui.route.addActionListener(new ButtonAction());
		File bild    = new File("../DijkstraSeb/map_img/karte.jpg");
		Image map = ImageIO.read(bild);
		File noRoute = new File("../DijkstraSeb/map_img/noRoute.jpg");
		Image not = ImageIO.read(noRoute);
		File pos = new File("../DijkstraSeb/map_img/position.gif");
		Image p = ImageIO.read(pos);
		map_panel.setImage(map, not, p);
		map_panel.add(new JLabel("From: "));
		map_panel.add(DynamicGui.start);
		map_panel.add(new JLabel(" to Destination: "));
		map_panel.add(DynamicGui.ziel);
		map_panel.add(route);
		return map_panel;
	}	
}

class ComboChange implements ItemListener{
	public void itemStateChanged(ItemEvent e){
		JComboBox combo = (JComboBox)e.getSource();
		if (combo.getName().equals("start")){
			DynamicGui.ziel.removeAllItems();
			DynamicGui.ziel.addItem("");
			for (int i=1;i<DynamicGui.graph.getNodes().size()+1;i++){
				if (!combo.getSelectedItem().equals(DynamicGui.graph.getNodes().get(i-1).getName()))
					DynamicGui.ziel.addItem(DynamicGui.graph.getNodes().get(i-1).getName());
			}
		}
	}
}

class ButtonAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton quelle = (JButton)e.getSource();
		if (quelle.getName().equals("Route")){
			DynamicGui.show_map.repaint();
		}
	}
}

class MouseAction implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount()==2){
			DynamicGui.start.setSelectedIndex(0);
			DynamicGui.ziel.setSelectedIndex(0);
			DynamicGui.show_map.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}	
}

