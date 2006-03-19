package net.sf.anathema.character.generic.framework.magic.treelayout.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.lib.util.Identificate;

public class IdentifiedRegularNode extends Identificate implements IIdentifiedRegularNode {

  private final List<ISimpleNode> childList = new ArrayList<ISimpleNode>();
  private final List<ISimpleNode> parentList = new ArrayList<ISimpleNode>();
  private int layer = 0;
  private boolean lowerToChildren = false;

  public IdentifiedRegularNode(IRegularNode[] children, String id) {
    super(id);
    Collections.addAll(childList, children);
  }

  public void removeParent(ISimpleNode node) {
    parentList.remove(node);
  }

  public void addParent(ISimpleNode parentNode) {
    parentList.add(parentNode);
  }

  public ISimpleNode[] getParents() {
    return parentList.toArray(new ISimpleNode[parentList.size()]);
  }

  public boolean isRootNode() {
    return parentList.isEmpty();
  }

  public void setLayer(int newLayer) {
    layer = newLayer;
  }

  public ISimpleNode[] getChildren() {
    return childList.toArray(new ISimpleNode[childList.size()]);
  }

  public int getLayer() {
    return layer;
  }

  public void removeChild(ISimpleNode child) {
    childList.remove(child);
  }

  public void addChild(ISimpleNode child) {
    childList.add(child);
  }

  public void setLowerToChildren(boolean lower) {
    this.lowerToChildren = lower;
  }

  public boolean getLowerToChildren() {
    return lowerToChildren;
  }

  public boolean isLeafNode() {
    return childList.isEmpty();
  }

  @Override
  public String toString() {
    return getId() + "(Layer:" + getLayer() + ")"; //$NON-NLS-1$//$NON-NLS-2$
  }
}