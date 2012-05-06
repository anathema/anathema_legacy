package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNode;
import net.sf.anathema.platform.svgtree.document.visualizer.CreateElementForNode;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;

public class SwingLayerCascadeCreator {
  private final ITreePresentationProperties properties;

  public SwingLayerCascadeCreator(ITreePresentationProperties properties) {
    this.properties = properties;
  }

  public DefaultContainerCascade create(ILayer[] layers) {
    DefaultContainerCascade container = new DefaultContainerCascade();
    addNodes(container, layers);
    addArrows(container, layers);
    return container;
  }

  private void addNodes(DefaultContainerCascade container, ILayer[] layers) {
    for (ILayer layer : layers) {
      for (IVisualizableNode node : layer.getNodes()) {
        node.accept(CreateElementForNode.create(layer, properties, container, new CascadeNodeAdderFactory()));
      }
    }
  }

  private void addArrows(DefaultContainerCascade container, ILayer[] layers) {
    //To change body of created methods use File | Settings | File Templates.
  }
}
