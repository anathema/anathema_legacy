package net.sf.anathema.platform.svgtree.graph.ordering;

public interface IStructureNode {

  public boolean isLeafNode();
  
  public boolean hasMultipleParents();
}