package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.VisualizedGraphFactory;

public interface CascadeCreationStrategy<CASCADE> {

  CascadeBuilder<?, CASCADE> createCascadeBuilder(ITreePresentationProperties properties);

  VisualizedGraphFactory getFactoryForVisualizedGraphs(ITreePresentationProperties properties);
}