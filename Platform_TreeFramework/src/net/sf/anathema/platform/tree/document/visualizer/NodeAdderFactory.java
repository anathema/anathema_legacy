package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.platform.tree.util.Area;

public interface NodeAdderFactory<PARENT> {

  NodeAdder<PARENT> create(String id, Area dimension, int xPosition, int yPosition);
}