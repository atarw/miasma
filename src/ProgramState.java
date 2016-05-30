import java.util.*;

public class ProgramState {
  private static final ProgramState state = new ProgramState ();
  private Graph graph;
  
  public static ProgramState getInstance () {
    return state;
  }
  
  public Graph getGraph () {
    return graph;
  }
  
  public void resetState () {
    graph = new Graph ();
  }
  
  private ProgramState () {
    resetState ();
  }
}