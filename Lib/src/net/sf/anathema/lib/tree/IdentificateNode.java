package net.sf.anathema.lib.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.lib.util.IIdentificate;

public class IdentificateNode<I extends IIdentificate> {

  private final I content;
  private final List<IdentificateNode<I>> children = new ArrayList<IdentificateNode<I>>();
  private final ITreeListFactory<I> listFactory;

  public IdentificateNode(I content, ITreeListFactory<I> listFactory) {
    this.content = content;
    this.listFactory = listFactory;
  }

  public void addChild(IdentificateNode<I> child) {
    children.add(child);
  }

  public I getContent() {
    return content;
  }

  public boolean hasChildren() {
    return children.size() > 0;
  }

  public I[] getAllChildren() {
    List<I> allChildren = new ArrayList<I>();
    for (IdentificateNode<I> childNode : children) {
      allChildren.add(childNode.getContent());
      if (childNode.hasChildren()) {
        allChildren.addAll(Arrays.asList(childNode.getAllChildren()));
      }
    }
    return listFactory.concertToArray(allChildren);
  }

  public IdentificateNode<I> getNodeById(String id) {
    if (content != null && content.getId().equals(id)) {
      return this;
    }
    if (!hasChildren()) {
      return null;
    }
    for (IdentificateNode<I> childNode : children) {
      IdentificateNode<I> node = childNode.getNodeById(id);
      if (node != null) {
        return node;
      }
    }
    return null;
  }
}