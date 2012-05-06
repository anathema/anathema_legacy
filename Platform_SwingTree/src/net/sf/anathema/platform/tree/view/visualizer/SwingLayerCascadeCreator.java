package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;

public class SwingLayerCascadeCreator {
  private final ITreePresentationProperties properties;

  public SwingLayerCascadeCreator(ITreePresentationProperties properties) {
    this.properties = properties;
  }

  public DefaultContainerCascade create(ILayer[] layers) {
    return new DefaultContainerCascade();
  }
}
