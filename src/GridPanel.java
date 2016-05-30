import javax.swing.*;

import java.awt.*;
import java.awt.image.*;

public class GridPanel extends JPanel {
  private GUIState lookManager;
  private ProgramState stateManager;
  private DragAndDropListener listener;
  
  private boolean drag = false;
  
  private static final int GRID_SIZE_Y = 13;
  private static final int GRID_SIZE_X = GRID_SIZE_Y * 2;
  
  private int SIZE = getSize ().getHeight () > getSize ().getWidth () ? (int) (getSize ().getWidth () / (GRID_SIZE_X + 2)) : (int) (getSize ().getHeight () / (GRID_SIZE_Y + 2));
  private int SPACE = SIZE / 2;
  private int BOARD_LENGTH_Y = SPACE * 2 + SIZE * GRID_SIZE_Y, BOARD_LENGTH_X = SPACE * 2 + SIZE * GRID_SIZE_X;
  private int V_BOARD_GAP = (int) ((getSize ().getHeight () - BOARD_LENGTH_Y) / 2);
  private int H_BOARD_GAP = (int) ((getSize ().getWidth () - BOARD_LENGTH_X) / 2);
  
  public int getX () {
    return listener.getX ();
  }
  
  public int getY () {
    return listener.getY ();
  }
  
  public void paintComponent (Graphics g) {
    super.paintComponent (g);
    
    if (getSize ().getHeight () > getSize ().getWidth ()) {
      SIZE = (int) (getSize ().getWidth () / (GRID_SIZE_X + 2));
    }
    else {
      SIZE = (int) (getSize ().getHeight () / (GRID_SIZE_Y + 2));
    }
    
    SPACE = SIZE / 2;
    BOARD_LENGTH_X = SPACE * 2 + SIZE * GRID_SIZE_X;
    BOARD_LENGTH_Y = SPACE * 2 + SIZE * GRID_SIZE_Y;
    V_BOARD_GAP = (int) ((getSize ().getHeight () - BOARD_LENGTH_Y) / 2);
    H_BOARD_GAP = (int) ((getSize ().getWidth () - BOARD_LENGTH_X) / 2);
    
    drawGrid (g);
    
    if (drag) {
      
    }
  }
  
  public void drawGrid (Graphics g) {
    boolean color = false;
    
    g.setColor (lookManager.getGUITheme ().getActiveBorderlessButtonBackground ());
    g.fillRect (H_BOARD_GAP, V_BOARD_GAP, BOARD_LENGTH_X, BOARD_LENGTH_Y);//0, 0
    
    g.setColor (lookManager.getGUITheme ().getBorderlessButtonText ());
    g.setFont (new Font ("Arial", Font.PLAIN, SPACE / 2));
    
    for (int y = 0; y < GRID_SIZE_X; y++) {
      g.drawString ("" + ((char) ('A' + y)), H_BOARD_GAP + SIZE * y + SPACE + SIZE / 2, V_BOARD_GAP + (int)(SPACE * 1.7) + SIZE * GRID_SIZE_Y);
    }
    
    for (int y = 0; y < GRID_SIZE_Y; y++) {
      g.drawString ((GRID_SIZE_Y - y) + "", H_BOARD_GAP + SPACE / 3, V_BOARD_GAP + SPACE + SIZE / 2 + SIZE * y);
    }
    
    //int location = 0;
    
    for (int x = 0; x < GRID_SIZE_X; x++) {
      for (int y = 0; y < GRID_SIZE_Y; y++) {
        //location = stateManager.getLocation ((x), (GRID_SIZE - 1 - y));
        
        drawSquare (g, !color, H_BOARD_GAP + SPACE + x * SIZE, V_BOARD_GAP + SPACE + y * SIZE/*, location*/);
        color = !color;
        
        // if (Board.isValidLocation (location) && stateManager.getPieceAt (location) != null && (location != listener.getPressedLocation () || location == listener.getPressedLocation () && !drag)) {
        //  drawPiece (g, H_BOARD_GAP + x * SIZE + SPACE, V_BOARD_GAP + y * SIZE + SPACE, lookManager.getBoardTheme ().getImg (stateManager.getPieceAt (location)));
        //}
      }
      
      if (GRID_SIZE_Y % 2 == 0) {
        color = !color;
      }
    }
  }
  
  private void drawSquare (Graphics g, boolean isLight, int x, int y/*, int location*/) {
    //g.setColor (highlightedSquares.contains (location) ? lookManager.getBoardTheme ().getHighlight () : isLight ? lookManager.getBoardTheme ().getLight () : lookManager.getBoardTheme ().getDark ());
    g.setColor (isLight ? lookManager.getGUITheme ().getInactiveBorderlessButtonBackground () : lookManager.getGUITheme ().getActiveBorderlessButtonBackground ());
    g.fillRect (x, y, SIZE, SIZE);
    
    if (stateManager.getGraph ().isNode (getGridLocation (x, y))) {
      g.setColor (lookManager.getGUITheme ().getTextboxText ());
      
      for (int i : stateManager.getGraph ().getNeighbours (getGridLocation (x, y))) {
        System.out.println (i + " " + (i % GRID_SIZE_X) + " " + (i / GRID_SIZE_Y));
        
        if (i > getGridLocation (x, y)) {
          g.drawLine (x + SIZE / 4, y + SIZE / 4, i % GRID_SIZE_X, i / GRID_SIZE_Y);
        }
      }
      
      g.setColor (lookManager.getGUITheme ().getNodeColor ());
      g.fillOval (x + SIZE / 4, y + SIZE / 4, SIZE / 2, SIZE / 2);
      
      repaint ();
    }
  }
  
  public int getGridLocation (int x, int y) {
    int file = 0, rank = 0;
    
    for (int i = 0; i < GRID_SIZE_X; i++) {
      if (H_BOARD_GAP + SPACE + i * SIZE > x) {
        break;
      }
      
      file = i;
    }
    
    for (int i = 0; i < GRID_SIZE_Y; i++) {
      if (V_BOARD_GAP + SPACE + i * SIZE > y) {
        break;
      }
      
      rank = GRID_SIZE_Y - 1 - i;
    }
    
    //System.out.println (file + ", " + rank);
    return file + GRID_SIZE_X * rank;
  }
  
  public boolean isInsideBoard (int x, int y) {
    return x >= H_BOARD_GAP + SPACE && x <= BOARD_LENGTH_X + H_BOARD_GAP - SPACE && y >= V_BOARD_GAP + SPACE && y <= BOARD_LENGTH_Y + V_BOARD_GAP - SPACE;
  }
  
  public boolean getDrag () {
    return drag;
  }
  
  public void setDrag (boolean newDrag) {
    drag = newDrag;
  }
  
  public GridPanel () {
    lookManager = GUIState.getInstance ();
    stateManager = ProgramState.getInstance ();
    listener = new DragAndDropListener ();
    
    setPreferredSize (new Dimension (1100, 630));
    setMinimumSize (new Dimension (1025, 630));
    setBorder (lookManager.getGUITheme ().getDefaultBorder ());
    
    addMouseListener (listener);
    addMouseMotionListener (listener);
    
    setComponentPopupMenu (new NodeMenu (0));
    
    setDoubleBuffered (true);
    setVisible (true);
  }
}