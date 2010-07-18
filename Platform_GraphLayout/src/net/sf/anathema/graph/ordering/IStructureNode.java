package net.sf.anathema.graph.ordering;

public interface IStructureNode {

  public boolean isLeafNode();
  
  public boolean hasMultipleParents();
}