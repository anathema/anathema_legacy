package net.sf.anathema.platform.fx;

import javafx.scene.Parent;

public class ViewHolder implements ParentHolder {

  private NodeHolder nodeHolder;

  public ViewHolder(NodeHolder nodeHolder) {
    this.nodeHolder = nodeHolder;
  }

  @Override
  public Parent getParent() {
    return (Parent) nodeHolder.getNode();
  }
}