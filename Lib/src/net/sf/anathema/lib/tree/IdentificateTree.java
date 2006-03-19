package net.sf.anathema.lib.tree;

import net.sf.anathema.lib.util.ILinkedIdentificate;

public class IdentificateTree<I extends ILinkedIdentificate> {

  private final IdentificateNode<I> virtualRoot;
  private final ITreeListFactory<I> factory;

  public IdentificateTree(ITreeListFactory<I> factory) {
    this.factory = factory;
    this.virtualRoot = factory.createNode(null);
  }

  public void add(I nodeContent) {
    IdentificateNode<I> node = factory.createNode(nodeContent);
    String[] parentIDs = nodeContent.getParentIDs();
    if (parentIDs.length == 0) {
      virtualRoot.addChild(node);
      return;
    }
  }

  public IdentificateNode<I> getNodeById(String id) {
    return virtualRoot.getNodeById(id);
  }
}