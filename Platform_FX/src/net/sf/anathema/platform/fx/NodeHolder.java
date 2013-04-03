package net.sf.anathema.platform.fx;

import javafx.scene.Node;

public interface NodeHolder<N extends Node> {

  N getNode();
}
