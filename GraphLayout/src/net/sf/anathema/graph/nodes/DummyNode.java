package net.sf.anathema.graph.nodes;

import com.google.common.base.Preconditions;
import net.disy.commons.core.util.ArrayUtilities;

public class DummyNode implements ISimpleNode {

  private final int layer;
  private final ISimpleNode[] child = new ISimpleNode[1];
  private final ISimpleNode[] parent = new ISimpleNode[1];

  public DummyNode(ISimpleNode child, int layer) {
    this.child[0] = child;
    this.layer = layer;
  }

  @Override
  public ISimpleNode[] getChildren() {
    return child;
  }

  @Override
  public int getLayer() {
    return layer;
  }

  public boolean isParentOf(ISimpleNode potential) {
    return ArrayUtilities.containsValue(this.child, potential);
  }

  @Override
  public ISimpleNode[] getParents() {
    return parent;
  }

  @Override
  public void addParent(ISimpleNode node) {
    Preconditions.checkArgument(this.parent[0] == null);
    this.parent[0] = node;
  }

  @Override
  public boolean isRootNode() {
    return false;
  }

  @Override
  public boolean isLeafNode() {
    return false;
  }

  @Override
  public void removeParent(ISimpleNode node) {
    // Nothing to do
  }

  @Override
  public boolean hasMultipleParents() {
    return false;
  }

  @Override
  public ISimpleNode[] getChildren(ISimpleNode[] childrenLayer) {
    return getChildren();
  }

  @Override
  public void reorderChildren(ISimpleNode[] orderedNodes) {
    // Nothing to do
  }

  @Override
  public String toString() {
    return "Dummynode (Layer:" + getLayer() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}