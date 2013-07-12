package net.sf.anathema.platform.tree.document;

import net.sf.anathema.graph.SugiyamaLayout;
import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.tree.document.visualizer.IVisualizedGraph;
import net.sf.anathema.platform.tree.document.visualizer.VisualizedGraphFactory;

import java.util.List;

public class GenericCascadeFactory<CASCADE> implements CascadeFactory<CASCADE> {

  private final SugiyamaLayout layout = new SugiyamaLayout();
  private final CascadeCreationStrategy<CASCADE> creationStrategy;

  public GenericCascadeFactory(CascadeCreationStrategy<CASCADE> cascadeStrategy) {
    this.creationStrategy = cascadeStrategy;
  }

  @Override
  public CASCADE createCascade(IRegularNode[] nodes, ITreePresentationProperties properties) {
    IProperHierarchicalGraph[] graphs = createHierarchicalGraphs(nodes);
    List<IVisualizedGraph> visualizedGraphs = visualizeGraphs(properties, graphs);
    return buildCascadeObject(properties, visualizedGraphs);
  }

  private CASCADE buildCascadeObject(ITreePresentationProperties properties, List<IVisualizedGraph> visualizedGraphs) {
    CascadeBuilder<?, CASCADE> cascadeBuilder = creationStrategy.createCascadeBuilder(properties);
    double firstRowWidth = 0;
    double firstRowHeight = 0;
    for (IVisualizedGraph graph : visualizedGraphs) {
      if (graph.isSingleNode()) {
        firstRowWidth = properties.getGapDimension().width;
        firstRowHeight = properties.getNodeDimension().height + properties.getGapDimension().height;
        break;
      }
    }
    double currentWidth = properties.getGapDimension().width;
    double maximumHeight = 0;
    for (IVisualizedGraph graph : visualizedGraphs) {
      graph.addTo(cascadeBuilder);
      if (graph.isSingleNode()) {
        graph.translateBy(firstRowWidth, 0);
        firstRowWidth += properties.getGapDimension().width + graph.getDimension().width;
      } else {
        graph.translateBy(currentWidth, firstRowHeight);
        currentWidth += properties.getGapDimension().width + graph.getDimension().width;
        maximumHeight = Math.max(maximumHeight, graph.getDimension().height);
      }
    }
    maximumHeight += firstRowHeight;
    return cascadeBuilder.create();
  }

  private List<IVisualizedGraph> visualizeGraphs(ITreePresentationProperties properties,
                                                 IProperHierarchicalGraph[] graphs) {
    HierarchicalGraphVisualizer hierarchicalGraphVisualizer = createNodeToGraphConverter(properties);
    return hierarchicalGraphVisualizer.visualizeGraphs(graphs);
  }

  private IProperHierarchicalGraph[] createHierarchicalGraphs(IRegularNode[] nodes) {
    return layout.createProperHierarchicalGraphs(nodes);
  }

  private HierarchicalGraphVisualizer createNodeToGraphConverter(ITreePresentationProperties properties) {
    PositionerFactory positionerFactory = new PositionerFactory(properties);
    VisualizedGraphFactory factoryForVisualizedGraphs = creationStrategy.getFactoryForVisualizedGraphs(properties);
    return new HierarchicalGraphVisualizer(positionerFactory, factoryForVisualizedGraphs);
  }
}