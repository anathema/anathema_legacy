package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

public interface CascadeCreationStrategy<CASCADE> {

  CascadeBuilder<?, CASCADE> createCascadeBuilder(ITreePresentationProperties properties);

  VisualizerFactory getVisualizer(ITreePresentationProperties properties);
}
