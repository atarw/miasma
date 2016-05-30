import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class EditPanel extends JPanel implements ActionListener, ItemListener {
  private GUIState lookManager;
  
  private JToggleButton [] toggleButtons = new JToggleButton [4];
  private JButton [] buttons = new JButton [3];
  private boolean [] selected = new boolean [toggleButtons.length];
  
  public static final int ADD_CITY = 0, ADD_EDGE = 1, REMOVE_SELECTED_OBJECTS = 2, SET_INFECTION_POINTS = 3;
  
  public void paintComponent (Graphics g) {
    super.paintComponent (g);
  }
  
  public void actionPerformed (ActionEvent ae) {
    
  }
  
  public void itemStateChanged (ItemEvent ie) {
    if (ie.getStateChange () == ItemEvent.SELECTED) {
      for (int i = 0; i < toggleButtons.length; i++) {
        if (toggleButtons [i].isSelected ()) {
          if (!ie.getItem ().equals (toggleButtons [i])) {
            toggleButtons [i].setSelected (false);
            selected [i] = false;
          }
          else {
            selected [i] = true;
          }
        }
      }
    }
  }
  
  public EditPanel () {
    lookManager = GUIState.getInstance ();
    
    setMinimumSize (new Dimension (1025, 70));
    setPreferredSize (new Dimension (1100, 70));
    setLayout (new BorderLayout ());
    setBorder (lookManager.getGUITheme ().getDefaultBorder ());
    
    MaterialUIMovement animate = new MaterialUIMovement (lookManager.getGUITheme ().getActiveTextbox (), 5, 1000 / 30);
    String [] text = {"ADD CITY", "ADD EDGE", "REMOVE SELECTED OBJECTS", "SET INFECTION POINTS"};
    
    JPanel options1 = new JPanel ();
    
    for (int i = 0; i < toggleButtons.length; i++) {
      toggleButtons [i] = new MaterialToggleButton (text [i], lookManager.getGUITheme ().getInactiveBorderlessButtonBackground (), lookManager.getGUITheme ().getBorderlessButtonText (), lookManager.getGUITheme ().getActiveBorderlessButtonBackground ());
      toggleButtons [i].addItemListener (this);
      options1.add (toggleButtons [i]);
    }
    
    String [] text2 = {"CLEAR", "RANDOM GRAPH", "START DRILL"};
    Color [][] colors = {{lookManager.getGUITheme ().getInactiveBorderlessButtonBackground (), new Color (255, 100, 100), lookManager.getGUITheme ().getActiveBorderlessButtonBackground ()},
      //{new Color (0, 200, 118), lookManager.getGUITheme ().getBorderedButtonText (), new Color (95, 210, 144)},
      {lookManager.getGUITheme ().getInactiveBorderlessButtonBackground (), new Color (0, 200, 118), lookManager.getGUITheme ().getActiveBorderlessButtonBackground ()},
      {lookManager.getGUITheme ().getInactiveBorderedButtonBackground (), lookManager.getGUITheme ().getBorderedButtonText (), lookManager.getGUITheme ().getActiveBorderedButtonBackground ()}};
    
    JPanel options2 = new JPanel ();
    
    for (int i = 0; i < buttons.length; i++) {
      buttons [i] = new MaterialButton (text2 [i], colors [i][0], colors [i][1], colors [i][2]);
      buttons [i].addActionListener (this);
      
      if (i != 0) {
        options2.add (buttons [i]);
      }
      else {
        options2.add (buttons [i]);
      }
    }
    
    add (options1, BorderLayout.WEST);
    add (options2, BorderLayout.EAST);
    
    setDoubleBuffered (true);
    setVisible (true);
  }
}