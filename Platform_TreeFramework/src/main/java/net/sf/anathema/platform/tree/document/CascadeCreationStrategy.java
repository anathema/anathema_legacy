package net.sf.anathema.platform.tree.document;

import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;
import net.sf.anathema.platform.tree.document.visualizer.VisualizedGraphFactory;

public interface CascadeCreationStrategy<CASCADE> {

  CascadeBuilder<?, CASCADE> createCascadeBuilder(TreePresentationProperties properties);

  VisualizedGraphFactory getFactoryForVisualizedGraphs(TreePresentationProperties properties);
}