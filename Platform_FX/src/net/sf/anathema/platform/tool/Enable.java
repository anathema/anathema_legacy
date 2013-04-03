package net.sf.anathema.platform.tool;

import javafx.scene.Node;

public class Enable implements Runnable {

  private Node node;

  public Enable(Node node) {
    this.node = node;
  }

  @Override
  public void run() {
    node.setDisable(false);
  }
}