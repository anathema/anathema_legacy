package net.sf.anathema.charmtree.provider;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.generic.framework.magic.CharmGraphNodeBuilder;
import net.sf.anathema.character.generic.framework.magic.treelayout.SugiyamaLayout;
import net.sf.anathema.character.generic.framework.magic.treelayout.graph.IProperHierarchicalGraph;
import net.sf.anathema.character.generic.framework.magic.treelayout.graph.type.IGraphType;
import net.sf.anathema.character.generic.framework.magic.treelayout.graph.type.IGraphTypeVisitor;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.IRegularNode;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;
import net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants;
import net.sf.anathema.charmtree.provider.svg.SVGCreationUtils;
import net.sf.anathema.charmtree.provider.svg.SVGDocumentFrameFactory;
import net.sf.anathema.charmtree.provider.visualizer.BottomUpGraphVisualizer;
import net.sf.anathema.charmtree.provider.visualizer.InvertedTreeVisualizer;
import net.sf.anathema.charmtree.provider.visualizer.SingleNodeVisualizer;
import net.sf.anathema.charmtree.provider.visualizer.TreeVisualizer;

import org.apache.batik.util.SVGConstants;
import org.dom4j.Document;
import org.dom4j.Element;

public class CharmTreeProvider {
  private final static Dimension MAXIMUM_DIMENSION = new Dimension(1400, 625);
  private final SugiyamaLayout layout = new SugiyamaLayout();
  private final SVGDocumentFrameFactory factory = new SVGDocumentFrameFactory();

  public Document createCascadeDocument(ICharm[] charms, final ICharmPresentationProperties properties) {
    final List<IVisualizedGraph> visualizedGraphs = visualizeGraphs(charms, properties);
    return placeOnCanvas(properties, visualizedGraphs);
  }

  private Document placeOnCanvas(
      final ICharmPresentationProperties properties,
      final List<IVisualizedGraph> visualizedGraphs) {
    Document cascadeDocument = factory.createFrame(properties);
    Element root = cascadeDocument.getRootElement();
    Element cascadeElement = createCascadeElement(root);
    boolean singlesPresent = false;
    for (IVisualizedGraph graph : visualizedGraphs) {
      if (graph.isSingleNode()) {
        singlesPresent = true;
        break;
      }
    }
    double firstRowWidth = properties.getGapDimension().width;
    double firstRowHeight = singlesPresent ? properties.getCharmDimension().height
        + properties.getGapDimension().height : 0;
    double currentWidth = properties.getGapDimension().width;
    double maximumHeight = 0;
    for (IVisualizedGraph graph : visualizedGraphs) {
      cascadeElement.add(graph.getCascadeElement());
      if (graph.isSingleNode()) {
        graph.getCascadeElement().addAttribute(
            SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
            "translate(" + firstRowWidth + " 0)"); //$NON-NLS-1$ //$NON-NLS-2$
        firstRowWidth += properties.getGapDimension().width + graph.getDimension().width;
      }
      else {
        graph.getCascadeElement().addAttribute(
            SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
            "translate(" + currentWidth + " " + firstRowHeight + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        currentWidth += properties.getGapDimension().width + graph.getDimension().width;
        maximumHeight = Math.max(maximumHeight, graph.getDimension().height);
      }
    }
    maximumHeight += singlesPresent ? firstRowHeight : 0;
    currentWidth = Math.max(currentWidth, firstRowWidth);
    setViewBox(currentWidth, maximumHeight, root);
    return cascadeDocument;
  }

  private Element createCascadeElement(Element root) {
    Element cascadeElement = root.addElement(SVGCreationUtils.createSVGQName(SVGConstants.SVG_G_TAG));
    cascadeElement.addAttribute(SVGConstants.SVG_ID_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_CASCADE_ID);
    return cascadeElement;
  }

  private void setViewBox(double width, double height, Element root) {
    if (height > MAXIMUM_DIMENSION.height || width > MAXIMUM_DIMENSION.width) {
      double viewBoxHeight = Math.max(height, width / 2.24);
      double viewBoxWidth = Math.max(width, height * 2.24) + 10;
      root.addAttribute(SVGConstants.SVG_VIEW_BOX_ATTRIBUTE, "0 0 " + viewBoxWidth + " " + viewBoxHeight); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  private List<IVisualizedGraph> visualizeGraphs(ICharm[] charms, final ICharmPresentationProperties properties) {
    IRegularNode[] nodes = CharmGraphNodeBuilder.createNodesFromCharms(Arrays.asList(charms));
    final IProperHierarchicalGraph[] graphs = layout.createProperHierarchicalGraphs(nodes);
    final List<IVisualizedGraph> visualizedGraphs = new ArrayList<IVisualizedGraph>(graphs.length);
    for (final IProperHierarchicalGraph graph : graphs) {
      graph.getType().accept(new IGraphTypeVisitor() {
        public void visitDirectedGraph(IGraphType visitedType) {
          visualizedGraphs.add(new BottomUpGraphVisualizer(graph, properties).buildCharmTree());
        }

        public void visitInvertedTree(IGraphType visitedType) {
          visualizedGraphs.add(new InvertedTreeVisualizer(graph, properties).buildCharmTree());
        }

        public void visitTree(IGraphType visitedType) {
          visualizedGraphs.add(new TreeVisualizer(graph, properties).buildCharmTree());
        }

        public void visitSingle(IGraphType visitedType) {
          visualizedGraphs.add(new SingleNodeVisualizer(properties, graph).buildCharmTree());
        }
      });
    }
    return visualizedGraphs;
  }
}