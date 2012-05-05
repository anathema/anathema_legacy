package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.IVisualizedGraph;

import java.util.List;

public class GenericCascadeFactory<CASCADE> implements CascadeFactory<CASCADE> {

  private final CascadeCreationStrategy<CASCADE> creationStrategy;

  public GenericCascadeFactory(CascadeCreationStrategy<CASCADE> cascadeStrategy) {
    this.creationStrategy = cascadeStrategy;
  }

  @Override
  public CASCADE createCascade(IRegularNode[] nodes, ITreePresentationProperties properties) {
    VisualizerFactory visualizerFactory = creationStrategy.getVisualizer(properties);
    List<IVisualizedGraph> visualizedGraphs = new NodeToGraphConverter(nodes, visualizerFactory).visualizeGraphs();
    CascadeBuilder<?, CASCADE> cascadeBuilder = creationStrategy.createCascadeBuilder(properties);
    cascadeBuilder.initialize();
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
    currentWidth = Math.max(currentWidth, firstRowWidth);
    cascadeBuilder.applyFinalTouch(currentWidth, maximumHeight);
    return cascadeBuilder.create();
  }
}