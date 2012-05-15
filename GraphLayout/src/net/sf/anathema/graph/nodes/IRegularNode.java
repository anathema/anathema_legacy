package net.sf.anathema.graph.nodes;

public interface IRegularNode extends ISimpleNode {
  void setLayer(int i);

  void removeChild(ISimpleNode child);

  void addChild(ISimpleNode currentChild);

  void setLowerToChildren(boolean lower);

  boolean getLowerToChildren();
}