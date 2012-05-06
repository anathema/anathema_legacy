package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.platform.svgtree.document.components.HorizontalMetaNode;
import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNode;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNodeVisitor;
import net.sf.anathema.platform.svgtree.document.components.PolylineSVGArrow;
import net.sf.anathema.platform.svgtree.document.components.VisualizableDummyNode;
import net.sf.anathema.platform.svgtree.document.components.VisualizableNode;
import net.sf.anathema.platform.svgtree.document.util.SVGCreationUtils;
import org.apache.batik.util.SVGConstants;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultElement;

public class SvgLayerElementCreator {
  private final ITreePresentationProperties properties;

  public SvgLayerElementCreator(ITreePresentationProperties properties) {
    this.properties = properties;
  }

  public Element createXml(ILayer... layers) {
    QName group = SVGCreationUtils.createSVGQName(SVGConstants.SVG_G_TAG);
    Element cascadeElement = new DefaultElement(group);
    addNodes(cascadeElement, layers);
    addArrows(cascadeElement, layers);
    return cascadeElement;
  }

  private void addNodes(Element cascadeElement, ILayer[] layers) {
    for (ILayer layer : layers) {
      for (IVisualizableNode node : layer.getNodes()) {
        node.accept(CreateElementForNode.create(layer, properties, cascadeElement, new SvgNodeAdderFactory()));
      }
    }
  }

  private void addArrows(Element cascadeElement, ILayer[] layers) {
    for (ILayer layer : layers) {
      addArrows(cascadeElement, layer);
    }
  }

  public void addArrows(final Element cascade, final ILayer layer) {
    if (layer.isBottomMostLayer()) {
      return;
    }
    for (IVisualizableNode node : layer.getNodes()) {
      node.accept(new IVisualizableNodeVisitor() {
        @Override
        public void visitHorizontalMetaNode(HorizontalMetaNode visitedNode) {
          throw new IllegalStateException("Unroll Metanodes before creating XML."); //$NON-NLS-1$
        }

        @Override
        public void visitSingleNode(VisualizableNode visitedNode) {
          for (IVisualizableNode child : visitedNode.getChildren()) {
            PolylineSVGArrow arrow = new PolylineSVGArrow();
            arrow.addPoint(visitedNode.getPosition(), layer.getYPosition() + visitedNode.getHeight());
            arrow.addPoint(child.getPosition(), child.getLayer().getYPosition());
            extendArrow(arrow, child);
            cascade.add(arrow.toXML());
          }
        }

        @Override
        public void visitDummyNode(VisualizableDummyNode visitedNode) {
          // Nothing to do
        }
      });
    }
  }

  private void extendArrow(final PolylineSVGArrow arrow, IVisualizableNode node) {
    node.accept(new IVisualizableNodeVisitor() {
      @Override
      public void visitHorizontalMetaNode(HorizontalMetaNode visitedNode) {
        throw new IllegalStateException("Unroll Metanodes before creating XML."); //$NON-NLS-1$
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
        extendArrow(arrow, child);
      }
    });
  }
}