package net.sf.anathema.charms.graph.ordering;

public interface IStructureNode {

  public boolean isLeafNode();

  public boolean hasMultipleParents();
}