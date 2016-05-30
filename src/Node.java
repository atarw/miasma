public class Node {
  int ID, population;
  
  public boolean equals (Object o) {
    Node n = (Node) o;
    return n.ID == ID && n.population == population;
  }
  
  public int hashCode () {
    return ID;
  }
  
  public Node (int ID) {
    this (ID, 0);
  }
  
  public Node (int ID, int population) {
    this.ID = ID;
    this.population = population;
  }
}