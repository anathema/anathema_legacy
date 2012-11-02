package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.platform.tree.document.components.HorizontalMetaNode;
import net.sf.anathema.platform.tree.document.components.ILayer;
import net.sf.anathema.platform.tree.document.components.IVisualizableNodeVisitor;
import net.sf.anathema.platform.tree.document.components.VisualizableDummyNode;
import net.sf.anathema.platform.tree.document.components.VisualizableNode;

public class CreateElementForNode<PARENT> implements IVisualizableNodeVisitor {
  public static <PARENT> CreateElementForNode<PARENT> create(ILayer layer, ITreePresentationProperties properties,
                                                             PARENT cascadeElement, NodeAdderFactory<PARENT> factory) {
    return new CreateElementForNode<>(layer, properties, cascadeElement, factory);
  }

  private final ILayer layer;
  private final PARENT parent;
  private final ITreePresentationProperties properties;

  private final NodeAdderFactory<PARENT> adderFactory;

  private CreateElementForNode(ILayer layer, ITreePresentationProperties properties, PARENT parent,
                               NodeAdderFactory<PARENT> factory) {
    this.layer = layer;
    this.parent = parent;
    this.properties = properties;
    this.adderFactory = factory;
  }

  @Override
  public void visitHorizontalMetaNode(HorizontalMetaNode visitedNode) {
    throw new UnsupportedOperationException("Unroll meta nodes before positioning.");
  }

  @Override
  public void visitSingleNode(VisualizableNode visitedNode) {
    NodeAdder<PARENT> adder = adderFactory.create(visitedNode.getId(), properties.getNodeDimension(),
            visitedNode.getPosition(), layer.getYPosition());
    adder.addTo(parent);
  }

  @Override
  public void visitDummyNode(VisualizableDummyNode visitedNode) {
    //nothing to do
  }
}