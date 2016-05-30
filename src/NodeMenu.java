import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class NodeMenu extends JPopupMenu implements ActionListener {
  
  private ProgramState stateManager;
  private GUIState lookManager;
  private int node;
  
  public void actionPerformed (ActionEvent ae) {
    if (ae.getActionCommand ().equals ("Add Node")) {
      stateManager.getGraph ().addNode (node);
      lookManager.getGridPanel ().setComponentPopupMenu (new NodeMenu (node));
    }
    else if (ae.getActionCommand ().equals ("Remove Node")) {
      stateManager.getGraph ().removeNode (node);
    }
    else if (ae.getActionCommand ().equals ("Toggle Infection Point")) {
      if (!stateManager.getGraph ().isInfectionPoint (node)) {
        stateManager.getGraph ().addInfectionPoint (node);
      }
      else {
        stateManager.getGraph ().removeInfectionPoint (node);
      }
    }
    else {
      String text = ((JMenuItem)(((JComponent)(ae.getSource ())).getParent ())).getText ();//most disgusting thing i've ever written in my life
      
      if (text.equals ("Add Edge To")) {
        stateManager.getGraph ().addEdge (node, Integer.parseInt (ae.getActionCommand ()));
      }
      else if (text.equals ("Remove Edge To")) {
        stateManager.getGraph ().removeEdge (node, Integer.parseInt (ae.getActionCommand ()));
      }
    }
    
    System.out.println (ae.getActionCommand ());
    System.out.println (stateManager.getGraph ().map);
  }
  
  public NodeMenu (int node) {
    super ("Options");
    
    stateManager = ProgramState.getInstance ();
    lookManager = GUIState.getInstance ();
    this.node = node;
    
    JMenuItem [] items = {new JMenuItem ("Add Node"), new JMenuItem ("Remove Node"), new JMenuItem ("Toggle Infection Point"), new JMenu ("Add Edge To"), new JMenu ("Remove Edge To")};
    MaterialUIMovement animate = new MaterialUIMovement (lookManager.getGUITheme ().getMenuSelectionBackground (), 5, 1000 / 30);
    
    JMenuItem tmp;
    
    if (stateManager.getGraph ().isNode (node)) {
      for (int i : stateManager.getGraph ().getNeighbours (node)) {
        tmp = new JMenuItem (Integer.toString (i));
        tmp.setPreferredSize (new Dimension (100, 30));
        tmp.addActionListener (this);
        
        items [4].add (tmp);
        animate.add (tmp);
      }
      
      for (int i : stateManager.getGraph ().map.keySet ()) {
        if (!stateManager.getGraph ().getNeighbours (node).contains (i) && i != node) {
          tmp = new JMenuItem (Integer.toString (i));
          tmp.setPreferredSize (new Dimension (100, 30));
          tmp.addActionListener (this);
          
          items [3].add (tmp);
          animate.add (tmp);
        }
      }
    }
    
    for (int i = 0; i < items.length; i++) {
      if (!stateManager.getGraph ().isNode (node) && i != 0 || stateManager.getGraph ().isNode (node) && (i == 0 || i == 4 && stateManager.getGraph ().getConnectivity (node) <= 0)) {
        items [i].setEnabled (false);
      }
      
      items [i].setPreferredSize (new Dimension (150, 30));
      items [i].addActionListener (this);
      
      add (items [i]);
      animate.add (items [i]);
    }
  }
}