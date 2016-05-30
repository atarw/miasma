import javax.swing.*;

import java.awt.*;
import java.awt.image.*;

public class QuestionPanel extends JPanel {
  private GUIState lookManager;
  
  public void paintComponent (Graphics g) {
    super.paintComponent (g);
  }
  
  public QuestionPanel () {
    lookManager = GUIState.getInstance ();
    
    setMinimumSize (new Dimension (250, 100));
    setBorder (lookManager.getGUITheme ().getDefaultBorder ());
    
    setDoubleBuffered (true);
    setVisible (true);
  }
}