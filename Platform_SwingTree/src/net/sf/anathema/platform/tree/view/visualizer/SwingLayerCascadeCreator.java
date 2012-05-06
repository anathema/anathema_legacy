package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.svgtree.document.components.HorizontalMetaNode;
import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNode;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNodeVisitor;
import net.sf.anathema.platform.svgtree.document.components.VisualizableDummyNode;
import net.sf.anathema.platform.svgtree.document.components.VisualizableNode;
import net.sf.anathema.platform.svgtree.document.visualizer.CreateElementForNode;
import net.sf.anathema.platform.svgtree.document.visualizer.Fletcher;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;
import net.sf.anathema.platform.tree.view.draw.FlexibleArrow;

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
    for (ILayer layer : layers) {
      addArrows(container, layer);
    }
  }

  public void addArrows(final DefaultContainerCascade container, ILayer layer) {
    if (layer.isBottomMostLayer()) {
      return;
    }
    for (IVisualizableNode node : layer.getNodes()) {
      node.accept(new IVisualizableNodeVisitor() {
        @Override
        public void visitHorizontalMetaNode(HorizontalMetaNode visitedNode) {
          throw new IllegalStateException("Unroll Metanodes before positioning."); //$NON-NLS-1$
        }

        @Override
        public void visitSingleNode(VisualizableNode currentNode) {
          for (IVisualizableNode childNode : currentNode.getChildren()) {
            FlexibleArrow arrow = new FlexibleArrow();
            new Fletcher().createArrow(currentNode, childNode, arrow);
            container.add(arrow);
          }
        }

        @Override
        public void visitDummyNode(VisualizableDummyNode visitedNode) {
          // Nothing to do
        }
      });
    }
  }
}
