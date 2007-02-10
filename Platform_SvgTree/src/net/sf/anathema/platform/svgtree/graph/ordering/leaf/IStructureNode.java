package net.sf.anathema.platform.svgtree.graph.ordering.leaf;

public interface IStructureNode {

  public boolean isLeafNode();
  
  public boolean hasMultipleParents();
}