package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.svgtree.document.CascadeBuilder;
import net.sf.anathema.platform.svgtree.document.CascadeCreationStrategy;
import net.sf.anathema.platform.svgtree.document.VisualizerFactory;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.ContainerCascade;
import net.sf.anathema.platform.tree.view.visualizer.SwingVisualizerFactory;

public class SwingCascadeStrategy implements CascadeCreationStrategy<Cascade> {

  @Override
  public CascadeBuilder<ContainerCascade, Cascade> createCascadeBuilder(ITreePresentationProperties properties) {
    return new SwingCascadeBuilder();
  }

  @Override
  public VisualizerFactory getVisualizer(ITreePresentationProperties properties) {
    return new SwingVisualizerFactory(properties);
  }
}
