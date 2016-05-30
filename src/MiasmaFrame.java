import javax.swing.*;
import javax.swing.plaf.basic.*;
import java.awt.event.*;
import java.awt.*;

public class MiasmaFrame extends JFrame implements ActionListener {
  
  private GUIState lookManager;
  
  public void actionPerformed (ActionEvent ae) {
    if (ae.getActionCommand ().equals ("Quit")) {
      quitProgram ();
    }
  }
  
  private JSplitPane configureComponents () {
    JSplitPane component = new JSplitPane (JSplitPane.VERTICAL_SPLIT, true, lookManager.getGridPanel (), lookManager.getEditPanel ());//graph grid + editor panel
    
    component.setUI (new BasicSplitPaneUI () {
      public BasicSplitPaneDivider createDefaultDivider () {
        return new BasicSplitPaneDivider (this) {
          public void paint (Graphics g) {
            super.paint (g);
            
            g.setColor (lookManager.getGUITheme ().getCard ());
            g.fillRect (0, 0, getSize ().width, getSize ().height);
          }};}});
    
    component.setResizeWeight (1);
    
    return component;
  }
  
  private JMenuBar configureMenuBar () {
    JMenuBar bar = new JMenuBar ();
    bar.setPreferredSize (new Dimension ((int) getSize ().getWidth (), 40));
    JMenu [] menus = {new JMenu ("File"), new JMenu ("Edit"), new JMenu ("Help")};
    
    JMenuItem [][] items = {
      {new JMenuItem ("Start Drill"), new JMenuItem ("Quit")},
      {new JMenuItem ("Add Node"), new JMenuItem ("Remove Node"), new JMenuItem ("Add Edge"), new JMenuItem ("Remove Node"), new JMenuItem ("Reset Graph"), new JMenuItem ("Random Graph")},
      {new JMenuItem ("Help"), new JMenuItem ("About")}};
    
    MaterialUIMovement animate = new MaterialUIMovement (lookManager.getGUITheme ().getMenuSelectionBackground (), 5, 1000 / 30);
    
    for (int i = 0; i < menus.length; i++) {
      for (int x = 0; x < items[i].length; x++) {
        items [i][x].addActionListener (this);
        items [i][x].setPreferredSize (new Dimension (100, 30));
        animate.add (items [i][x]);
        menus [i].add (items [i][x]);
      }
      animate.add (menus [i]);
      bar.add (menus [i]);
    }
    
    return bar;
  }
  
  private void configureWindow () {
    setSize (1100, 700);
    setPreferredSize (new Dimension (1100, 700)); 
    setMinimumSize (new Dimension (1025, 700));
    setLayout (new BorderLayout ());
    
    System.setProperty ("sun.awt.noerasebackground", "true");
    System.setProperty ("sun.java2d.noddraw", "true");
    System.setProperty ("sun.java2d.opengl", "true");
    System.setProperty ("sun.java2d.noddraw", "true");
    
    setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
    setLocationRelativeTo (null);
    
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        quitProgram ();
      }});
    
    JMenuBar bar = configureMenuBar ();
    JComponent component = configureComponents ();
    
    JPanel content = new JPanel (new BorderLayout ());
    content.setBorder (BorderFactory.createEmptyBorder ());
    content.add (bar, BorderLayout.PAGE_START);
    content.add (component, BorderLayout.CENTER);
    
    add (content, BorderLayout.CENTER);
    
    pack ();
    setVisible (true);
  }
  
  public void quitProgram () {
    dispose ();
    System.exit (0);
  }
  
  public MiasmaFrame () {
    super ("Miasma - By Atharva Washimkar");
    
    lookManager = GUIState.getInstance ();
    lookManager.configureUI ();
    configureWindow ();
  }
}