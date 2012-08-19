package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.document.CascadeBuilder;
import net.sf.anathema.platform.tree.document.CascadeCreationStrategy;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.tree.document.visualizer.VisualizedGraphFactory;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.ContainerCascade;
import net.sf.anathema.platform.tree.view.visualizer.SwingGraphFactory;

public class SwingCascadeStrategy implements CascadeCreationStrategy<Cascade> {

  @Override
  public CascadeBuilder<ContainerCascade, Cascade> createCascadeBuilder(ITreePresentationProperties properties) {
    return new SwingCascadeBuilder();
  }

  @Override
  public VisualizedGraphFactory getFactoryForVisualizedGraphs(ITreePresentationProperties properties) {
    return new SwingGraphFactory(properties);
  }
}
