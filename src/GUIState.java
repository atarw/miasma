import javax.swing.*;

public class GUIState {
  
  private static final GUIState manager = new GUIState ();
  private GridPanel gridPanel;
  private EditPanel editPanel;
  private QuestionPanel questionPanel;
  
  private JPanel topPanel;
  
  private GUITheme frameTheme = GUITheme.LIGHT_THEME;
  
  public static GUIState getInstance () {
    return manager;
  }
  
  public GUITheme getGUITheme () {
    return frameTheme;
  }
  
  public void setGUITheme (GUITheme frameTheme) {
    this.frameTheme = frameTheme;
    configureUI ();
  }
  
  public GridPanel getGridPanel () {
    return gridPanel;
  }
  
  public EditPanel getEditPanel () {
    return editPanel;
  }
  
  public QuestionPanel getQuestionPanel () {
    return questionPanel;
  }
  
  public JPanel getTopPanel () {
    return topPanel;
  }
  
  public void toggleTopPanel () {
    if (topPanel instanceof EditPanel) {
      topPanel = questionPanel;
    }
    else {
      topPanel = editPanel;
    }
  }
  
  public void repaint () {
    topPanel.repaint ();
    gridPanel.repaint ();
  }
  
  public void configureUI () {
    UIManager.put ("Button.font", this.getGUITheme ().getBold ());
    UIManager.put("RadioButton.font", this.getGUITheme ().getLight ());
    UIManager.put("CheckBox.font", this.getGUITheme ().getLight ());
    UIManager.put("ComboBox.font", this.getGUITheme ().getLight ());
    UIManager.put("Label.font", this.getGUITheme ().getLight ());
    UIManager.put("MenuBar.font", this.getGUITheme ().getBold ());
    UIManager.put("MenuItem.font", this.getGUITheme ().getRegular ());
    UIManager.put("Menu.font", this.getGUITheme ().getBold ());
    UIManager.put("OptionPane.font", this.getGUITheme ().getLight ());
    UIManager.put("Panel.font", this.getGUITheme ().getLight ());
    UIManager.put("ScrollPane.font", this.getGUITheme ().getLight ());
    UIManager.put("Table.font", this.getGUITheme ().getLight ());
    UIManager.put("TableHeader.font", this.getGUITheme ().getLight ());
    UIManager.put("TextField.font", this.getGUITheme ().getLight ());
    UIManager.put("TextArea.font", this.getGUITheme ().getRegular ());
    
    UIManager.put ("Panel.background", this.getGUITheme ().getCard ());
    UIManager.put ("Panel.border", BorderFactory.createEmptyBorder ());
    
    UIManager.put ("MenuItem.background", this.getGUITheme ().getCard ());
    UIManager.put ("MenuItem.border", BorderFactory.createEmptyBorder ());
    UIManager.put ("MenuItem.disabledForeground", this.getGUITheme ().getMenuDisabledText ());
    UIManager.put ("MenuItem.selectionBackground", this.getGUITheme ().getMenuSelectionBackground ());
    UIManager.put("MenuItem.selectionForeground", this.getGUITheme ().getMenuSelectionText ());
    UIManager.put ("MenuItem.foreground", this.getGUITheme ().getMenuSelectionText ());
    
    UIManager.put("PopupMenu.border", BorderFactory.createLineBorder (this.getGUITheme ().getMenuSelectionBackground (), 1));
    UIManager.put("PopupMenu.background", this.getGUITheme ().getCard ());
    UIManager.put("Menu.border", BorderFactory.createEmptyBorder ());
    UIManager.put("Menu.selectionBackground", this.getGUITheme ().getMenuSelectionBackground ());
    UIManager.put("Menu.selectionForeground", this.getGUITheme ().getMenuSelectionText ());
    UIManager.put("Menu.disabledForeground", this.getGUITheme ().getMenuDisabledText ());
    UIManager.put ("Menu.background", this.getGUITheme ().getCard ());
    UIManager.put("Menu.foreground", this.getGUITheme ().getMenuSelectionText ());
    UIManager.put("Menu.opaque", true);
    UIManager.put("Menu.menuPopupOffsetY", 10);
    
    UIManager.put("MenuBar.background", this.getGUITheme ().getCard ());
    UIManager.put ("MenuBar.border", this.getGUITheme ().getMenuBorder ());
    
    UIManager.put ("SplitPane.border", BorderFactory.createEmptyBorder ());
    UIManager.put ("SplitPane.background", this.getGUITheme ().getCard ());
    UIManager.put ("SplitPane.dividerSize", 5);
    UIManager.put ("SplitPaneDivider.border", BorderFactory.createEmptyBorder ());
    
    UIManager.put ("ScrollPane.background", this.getGUITheme ().getCard ());
    UIManager.put ("ScrollPane.border", BorderFactory.createEmptyBorder ());
    
    UIManager.put ("TextArea.background", this.getGUITheme ().getInactiveTextbox ());
    UIManager.put ("TextArea.border", BorderFactory.createEmptyBorder ());
    UIManager.put ("TextArea.foreground", this.getGUITheme ().getTextboxText ());
    
    UIManager.put ("OptionPane.background", this.getGUITheme ().getCard ());
    UIManager.put ("OptionPane.border", this.getGUITheme ().getDefaultBorder ());
    
    UIManager.put ("Button.background", this.getGUITheme ().getInactiveBorderlessButtonBackground ());
    UIManager.put ("Button.foreground", this.getGUITheme ().getBorderlessButtonText ());
    UIManager.put ("Button.highlight", this.getGUITheme ().getActiveBorderlessButtonBackground ());
    UIManager.put ("Button.border", this.getGUITheme ().getDefaultBorder ());
    
    UIManager.put ("Separator.foreground", this.getGUITheme ().getSeparatorForeground ());
    
    try {
      UIManager.setLookAndFeel (UIManager.getCrossPlatformLookAndFeelClassName ());
    } 
    catch (UnsupportedLookAndFeelException e) {}
    catch (ClassNotFoundException e) {}
    catch (InstantiationException e) {}
    catch (IllegalAccessException e) {}
    
    gridPanel = new GridPanel ();
    editPanel = new EditPanel ();
    questionPanel = new QuestionPanel ();
    topPanel = editPanel;
  }
  
  private GUIState () {}
}