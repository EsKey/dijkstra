/**
 * 
 */
package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

/**
 * @author v095376
 *
 */
@SuppressWarnings("serial")
public class DynamicGui extends JFrame{
	
	@SuppressWarnings("rawtypes")
	static JComboBox start;
	@SuppressWarnings("rawtypes")
	static JComboBox ziel;
	static Object[] nodes;
	static Graph graph;
	static JDesktopPane desktop = new JDesktopPane();
	
	
	public DynamicGui(Graph graph){
		DynamicGui.graph=graph;
	}
	
	public Graph getGraph(){
		return DynamicGui.graph;
	}	
	
	public void init() throws IOException{
		String[] felder = {"Data","Lists"};
		String[][] subs = {{"0","Beenden"},{"1","Nodes"},{"1","Routes"}};
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(new Menu(felder,subs, desktop, this));
		JInternalFrame show_map = new JInternalFrame("Map",false,false,true,true);
		show_map.setSize(this.getSize());
		show_map.add(create_map_panel());
		show_map.setVisible(true);
		desktop.add(show_map);
		desktop.setVisible(true);
		this.add(desktop);
		this.setVisible(true);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JPanel create_map_panel() throws IOException{
		JButton route = new JButton("Berechnen");
		this.nodes = new Object[this.getGraph().getNodes().size()+1];
		this.nodes[0]="";
		for (int i=1;i<this.getGraph().getNodes().size()+1;i++){
			nodes[i]=this.getGraph().getNodes().get(i-1).getName();
		}
		DrawPanel map_panel = new DrawPanel();
		this.start = new JComboBox(this.nodes);
		this.start.setName("start");
		this.start.setSelectedIndex(0);
		this.start.addItemListener(new ComboChange());
		this.ziel = new JComboBox(this.nodes);
		this.ziel.setSelectedIndex(0);
		File bild = new File("../Dijkstra/map_img/karte.jpg");
		Image map = ImageIO.read(bild);
		map_panel.setImage(map);
		map_panel.add(new JLabel("From: "));
		map_panel.add(this.start);
		map_panel.add(new JLabel(" to Destination: "));
		map_panel.add(this.ziel);
		map_panel.add(route);
		return map_panel;
	}	
}

class DrawPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image map;
	public void setImage(Image m){
		map = m;
	}
	@Override
	protected void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(map, 5, 5, this);
	}
}

class ComboChange implements ItemListener{
	@SuppressWarnings({ "unchecked", "rawtypes" })
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