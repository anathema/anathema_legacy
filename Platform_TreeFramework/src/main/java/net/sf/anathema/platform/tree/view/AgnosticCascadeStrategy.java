package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.document.CascadeCreationStrategy;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;
import net.sf.anathema.platform.tree.document.visualizer.VisualizedGraphFactory;
import net.sf.anathema.platform.tree.view.visualizer.AgnosticGraphFactory;

public class AgnosticCascadeStrategy implements CascadeCreationStrategy {

  @Override
  public VisualizedGraphFactory getFactoryForVisualizedGraphs(TreePresentationProperties properties) {
    return new AgnosticGraphFactory(properties);
  }
}
