package net.sf.anathema.character.generic.framework.magic.treelayout.layering;

import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.IRegularNode;

public interface ILayerer {

  public int layerGraph(IRegularNode[] acyclicGraph);
}