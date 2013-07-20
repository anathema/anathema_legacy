package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.platform.tree.document.visualizer.NodeAdder;
import net.sf.anathema.platform.tree.document.visualizer.NodeAdderFactory;

public class CascadeNodeAdderFactory implements NodeAdderFactory {
  @Override
  public NodeAdder create(String id, Area dimension, int xPosition, int yPosition) {
    return new SwingNodeAdder(id, xPosition, yPosition);
  }
}