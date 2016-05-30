import java.util.*;

public class Graph {
  public Map <Integer, Set <Integer>> map = new HashMap <Integer, Set <Integer>> ();
  public Set <Integer> infectionPoints = new HashSet <Integer> ();
  public Map <Integer, Integer> population = new HashMap <Integer, Integer> ();
  
  public void addNode (int a, int pop) {
    if (!isNode (a)) {
      map.put (a, new HashSet <Integer> ());
    }
    
    population.put (a, pop);
  }
  
  public void addNode (int a) {
    addNode (a, 0);
  }
  
  public void addEdge (int a, int b) {
    if (!isNode (a)) {
      map.put (a, new HashSet <Integer> ());
      population.put (a, 0);
    }
    
    if (!isNode (b)) {
      map.put (b, new HashSet <Integer> ());
      population.put (b, 0);
    }
    
    map.get (a).add (b);
    map.get (b).add (a);
  }
  
  public void removeNode (int a) {
    if (isNode (a)) {
      for (int i : map.get (a)) {
        removeEdge (i, a);
      }
      
      map.remove (a);
      population.remove (a);
      infectionPoints.remove (a);
    }
  }
  
  public void addInfectionPoint (int a) {
    infectionPoints.add (a);
  }
  
  public void removeInfectionPoint (int a) {
    infectionPoints.remove (a);
  }
  
  public boolean isInfectionPoint (int a) {
    return infectionPoints.contains (a);
  }
  
  public void setPopulation (int a, int pop) {
    if (isNode (a)) {
      population.put (a, pop);
    }
  }
  
  public boolean isNode (int a) {
    return map.containsKey (a);
  }
  
  public Set <Integer> getNeighbours (int a) {
    if (isNode (a)) {
      return map.get (a);
    }
    return null;
  }
  
  public int getConnectivity (int a) {
    if (isNode (a)) {
      return map.get (a).size ();
    }
    return -1;
  }
  
  public void reset () {
    map.clear ();
    infectionPoints.clear ();
  }
  
  public void removeEdge (int a, int b) {
    if (isNode (a)) {
      map.get (a).remove (b);
    }
    
    if (isNode (b)) {
      map.get (b).remove (a);
    }
  }
}