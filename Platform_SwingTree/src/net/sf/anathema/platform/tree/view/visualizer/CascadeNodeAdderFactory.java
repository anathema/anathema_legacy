package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.tree.document.visualizer.NodeAdder;
import net.sf.anathema.platform.tree.document.visualizer.NodeAdderFactory;
import net.sf.anathema.platform.tree.util.Area;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;

public class CascadeNodeAdderFactory implements NodeAdderFactory<DefaultContainerCascade> {
  @Override
  public NodeAdder<DefaultContainerCascade> create(String id, Area dimension, int xPosition, int yPosition) {
    return new SwingNodeAdder(id, xPosition, yPosition);
  }
}