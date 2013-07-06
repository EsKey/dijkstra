package view;

import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import javax.swing.*;

public class Menu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JDesktopPane main;
	static DynamicGui activGui;
	
	
	
	public Menu(String[] menues, String name){
		this.setName(name);
		for(String s : menues){
			this.add(new JMenu(s));
		}
	}
	
	public Menu(String[] menues, String[][] subMenues, JDesktopPane desktop, DynamicGui activ){
		main = (JDesktopPane) desktop;
		activGui = activ;
		JMenu[] menue = new JMenu[menues.length];
		JMenuItem[] subs = new JMenuItem[subMenues.length];
		for (int i=0;i<menue.length;i++){
			menue[i] = new JMenu(menues[i]);
			this.add(menue[i]);
		}
		for (int i=0;i<subMenues.length;i++){
			int mIndex = Integer.valueOf(subMenues[i][0]);
			subs[i] = new JMenuItem(subMenues[i][1]);
			subs[i].setName(subMenues[i][1]);
			subs[i].addActionListener(new Aktionen());
			menue[mIndex].add(subs[i]);
		}	
	}
}

class Aktionen extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JMenuItem sub = (JMenuItem)e.getSource();
		if (sub.getName().equals("Beenden")){
			System.exit(0);
		}
		if (sub.getName().equals("Nodes")){
			System.out.println("ich war hier");
			JInternalFrame show_nodes = new JInternalFrame("Nodes",true,true,false,true);
			show_nodes.setSize(200,200);
			show_nodes.add(new Create_Nodes_Panel(Menu.activGui));
			show_nodes.setVisible(true);
			Menu.main.add(show_nodes);
			try {
				show_nodes.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (sub.getName().equals("Routes")){
			System.out.println("ich war hier");
			JInternalFrame show_routes = new JInternalFrame("Nodes",true,true,false,true);
			show_routes.setSize(400,200);
			show_routes.add(new Create_Routes_Panel(Menu.activGui));
			show_routes.setVisible(true);
			Menu.main.add(show_routes);
			try {
				show_routes.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}	
}

