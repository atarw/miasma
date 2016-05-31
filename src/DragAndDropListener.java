import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class DragAndDropListener implements MouseListener, MouseMotionListener {
  private GUIState lookManager;
  //private JPopupMenu curr;
  
  private int pressedLocation = -1;
  private int x = 0, y = 0;
  
  public int getPressedLocation () {
    return pressedLocation;
  }
  
  public int getX () {
    return x;
  }
  
  public int getY () {
    return y;
  }
  
  public void mousePressed (MouseEvent me) {
    if (lookManager.getGridPanel ().isInsideBoard (me.getX (), me.getY ())) {
      //pressedLocation = lookManager.getGridPanel ().getBoardLocation (me.getX (), me.getY ());
      lookManager.getGridPanel ().setDrag (false);
      
      //if (stateManager.hasPiece (pressedLocation) && stateManager.getPieceAt (pressedLocation).getColor () == stateManager.getSide ()) {
      //   lookManager.getBoardPanel ().displayLegalMoves (stateManager.getEntryAt (pressedLocation));
      //}
    }
    
    lookManager.repaint ();
  }
  
  public void mouseReleased (MouseEvent me) {}
  
  public void mouseDragged (MouseEvent me) {}
  
  public void mouseClicked (MouseEvent me) {}
  
  public void mouseExited (MouseEvent me) {}
  
  public void mouseEntered (MouseEvent me) {}
  
  public void mouseMoved (MouseEvent me) {
    lookManager.getGridPanel ().setComponentPopupMenu (new NodeMenu (lookManager.getGridPanel ().getGridLocation (me.getX (), me.getY ())));
    System.out.println (me.getX () + " " + me.getY ());
  }
  
  public DragAndDropListener () {
    lookManager = GUIState.getInstance ();
  }
}