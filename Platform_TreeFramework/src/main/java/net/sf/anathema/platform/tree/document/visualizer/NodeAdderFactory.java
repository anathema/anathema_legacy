package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.framework.ui.Area;

public interface NodeAdderFactory {

  NodeAdder create(String id, Area dimension, int xPosition, int yPosition);
}