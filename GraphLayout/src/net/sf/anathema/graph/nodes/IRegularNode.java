package net.sf.anathema.graph.nodes;

public interface IRegularNode extends ISimpleNode {
  public void setLayer(int i);

  public void removeChild(ISimpleNode child);

  public void addChild(ISimpleNode currentChild);

  public void setLowerToChildren(boolean lower);

  public boolean getLowerToChildren();
}