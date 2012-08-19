package net.sf.anathema.platform.tree.document;

import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.tree.document.visualizer.VisualizedGraphFactory;

public interface CascadeCreationStrategy<CASCADE> {

  CascadeBuilder<?, CASCADE> createCascadeBuilder(ITreePresentationProperties properties);

  VisualizedGraphFactory getFactoryForVisualizedGraphs(ITreePresentationProperties properties);
}