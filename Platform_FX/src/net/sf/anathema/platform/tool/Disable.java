package net.sf.anathema.platform.tool;

import javafx.scene.Node;

public class Disable implements Runnable {

  private Node node;

  public Disable(Node node) {
    this.node = node;
  }

  @Override
  public void run() {
    node.setDisable(true);
  }
}