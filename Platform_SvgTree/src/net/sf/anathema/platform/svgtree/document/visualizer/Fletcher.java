package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.platform.svgtree.document.components.ExtensibleArrow;
import net.sf.anathema.platform.svgtree.document.components.HorizontalMetaNode;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNode;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNodeVisitor;
import net.sf.anathema.platform.svgtree.document.components.VisualizableDummyNode;
import net.sf.anathema.platform.svgtree.document.components.VisualizableNode;

public class Fletcher {
  public void createArrow(IVisualizableNode visitedNode, IVisualizableNode child, ExtensibleArrow arrow) {
    arrow.addPoint(visitedNode.getPosition(), visitedNode.getLayer().getYPosition() + visitedNode.getHeight());
    arrow.addPoint(child.getPosition(), child.getLayer().getYPosition());
    child.accept(new ArrowExtender(arrow));
  }

  private static class ArrowExtender implements IVisualizableNodeVisitor {
    private final ExtensibleArrow arrow;

    public ArrowExtender(ExtensibleArrow arrow) {
      this.arrow = arrow;
    }

    @Override
    public void visitHorizontalMetaNode(HorizontalMetaNode visitedNode) {
      throw new IllegalStateException("Unroll Metanodes before positioning."); //$NON-NLS-1$
    }

    @Override
    public void visitSingleNode(VisualizableNode visitedNode) {
      // Nothing to do
    }

    @Override
    public void visitDummyNode(VisualizableDummyNode visitedNode) {
      IVisualizableNode child = visitedNode.getChildren()[0];
      arrow.addPoint(visitedNode.getPosition(), visitedNode.getLayer().getYPosition() + visitedNode.getHeight());
      arrow.addPoint(child.getPosition(), child.getLayer().getYPosition());
      child.accept(new ArrowExtender(arrow));
    }
  }
}