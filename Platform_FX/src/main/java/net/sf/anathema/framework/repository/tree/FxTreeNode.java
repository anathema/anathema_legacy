package net.sf.anathema.framework.repository.tree;

import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;

public class FxTreeNode implements AgnosticTreeNode {
  private final TreeItem<Object> item;

  public FxTreeNode(TreeItem<Object> item) {
    this.item = item;
  }

  @Override
  public AgnosticTreeNode addChildNode(Object value) {
    TreeItem<Object> child = new TreeItem<>(value);
    item.getChildren().add(child);
    return new FxTreeNode(child);
  }

  @Override
  public Object getObject() {
    return item.getValue();
  }

  @Override
  public void remove() {
    item.getParent().getChildren().remove(item);
  }

  @Override
  public AgnosticTreeNode[] getChildren() {
    List<AgnosticTreeNode> childNodes = new ArrayList<>();
    for (TreeItem<Object> childItem : item.getChildren()) {
      childNodes.add(new FxTreeNode(childItem));
    }
    return childNodes.toArray(new AgnosticTreeNode[childNodes.size()]);
  }
}
