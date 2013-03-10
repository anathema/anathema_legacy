package net.sf.anathema.scribe.perspective;

import javafx.scene.Node;

public interface NodeHolder<N extends Node> {

  N getNode();
}
