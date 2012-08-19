package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.tree.document.visualizer.NodeAdder;
import net.sf.anathema.platform.tree.document.visualizer.NodeAdderFactory;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;

import java.awt.Dimension;

public class CascadeNodeAdderFactory implements NodeAdderFactory<DefaultContainerCascade> {
  @Override
  public NodeAdder<DefaultContainerCascade> create(String id, Dimension dimension, int xPosition, int yPosition) {
    return new SwingNodeAdder(id, xPosition, yPosition);
  }
}