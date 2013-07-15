package net.sf.anathema.graph.ordering;

public interface IStructureNode {

  boolean isLeafNode();
  
  boolean hasMultipleParents();
}