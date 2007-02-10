package net.sf.anathema.character.generic.framework.magic.treelayout.hierarchy;

import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.IRegularNode;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.ISimpleNode;

public interface IHierachyBuilder {

  public ISimpleNode[] removeLongEdges(IRegularNode[] acyclicGraph);

}