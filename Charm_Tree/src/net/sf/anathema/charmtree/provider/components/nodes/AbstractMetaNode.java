package net.sf.anathema.charmtree.provider.components.nodes;

import java.awt.Dimension;
import java.util.Map;

import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.ISimpleNode;

import org.dom4j.Element;

public abstract class AbstractMetaNode extends AbstractVisualizableNode {

  public AbstractMetaNode(Map<ISimpleNode, IVisualizableNode> map, Dimension charmDimension) {
    super(map, charmDimension);
  }

  public final void toXML(Element element) {
    throw new UnsupportedOperationException("Metanodes should be unrolled before creating XML."); //$NON-NLS-1$
  }

  public boolean isOfSameLeafGroup(IVisualizableNode node) {
    for (IVisualizableNode visualizableNode : getInnerNodes()) {
      if (visualizableNode.isOfSameLeafGroup(node)) {
        return true;
      }
    }
    return false;
  }

  protected abstract IVisualizableNode[] getInnerNodes();

  public void resolveMetanode() {
    positionInnerNodes();
    refreshContentMap();
  }

  protected abstract void refreshContentMap();

  protected abstract void positionInnerNodes();
}