package net.sf.anathema.framework.view;

import javafx.scene.Node;

public interface ViewFactory {

  Node getNode();

  void init();
}