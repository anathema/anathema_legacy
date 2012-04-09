package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.graph.graph.LayeredGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.MultiEntryMap;

import java.util.Collections;
import java.util.List;

class TestLayeredGraph implements LayeredGraph {

  public final MultiEntryMap<Integer, ISimpleNode> nodesByLayer = new MultiEntryMap<Integer, ISimpleNode>();

  @Override
  public ISimpleNode[] getNodesByLayer(int layer) {
    List<ISimpleNode> nodes = nodesByLayer.get(layer);
    return nodes.toArray(new ISimpleNode[nodes.size()]);
  }

  @Override
  public int getDeepestLayer() {
    return Collections.max(nodesByLayer.keySet());
  }
}
