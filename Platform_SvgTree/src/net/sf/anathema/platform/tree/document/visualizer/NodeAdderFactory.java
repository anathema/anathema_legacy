package net.sf.anathema.platform.tree.document.visualizer;

import java.awt.Dimension;

public interface NodeAdderFactory<PARENT> {

  NodeAdder<PARENT> create(String id, Dimension dimension, int xPosition, int yPosition);
}