/**
 * 
 */
package view;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import lib.Graph;

/**
 * @author v095376
 *
 */
@SuppressWarnings("serial")
public class ModernGui extends JFrame{
	
	static JComboBox start;
	static JComboBox ziel;
	static Object[] nodes;
	static Graph graph;
	
	
	public ModernGui(Graph graph){
		ModernGui.graph=graph;
	}
	
	public Graph getGraph(){
		return ModernGui.graph;
	}
	
	public JFrame getModernGui(){
		return this;
	}
	
	
	
}
