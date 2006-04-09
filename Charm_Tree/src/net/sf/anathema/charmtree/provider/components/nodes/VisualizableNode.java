package net.sf.anathema.charmtree.provider.components.nodes;

import java.awt.Dimension;
import java.util.Map;

import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.IIdentifiedRegularNode;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.ISimpleNode;
import net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants;
import net.sf.anathema.charmtree.provider.svg.SVGCreationUtils;
import net.sf.anathema.lib.collection.MultiEntryMap;

import org.dom4j.Element;
import org.dom4j.QName;

public class VisualizableNode extends AbstractSingleVisualizableNode {

  public void accept(IVisualizableNodeVisitor visitor) {
    visitor.visitSingleNode(this);
  }

  public VisualizableNode(
      IIdentifiedRegularNode contentNode,
      Map<ISimpleNode, IVisualizableNode> map,
      Dimension charmDimension,
      MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors) {
    super(contentNode, map, charmDimension, leafNodesByAncestors);
  }

  public void toXML(Element element) {
    QName group = SVGCreationUtils.createSVGQName(ISVGCascadeXMLConstants.TAG_G);
    Element g = element.addElement(group);
    QName useName = SVGCreationUtils.createSVGQName(ISVGCascadeXMLConstants.TAG_USE);
    Element use = g.addElement(useName);
    {
      use.addAttribute(ISVGCascadeXMLConstants.ATTRIB_X, String.valueOf(getPosition() - getCharmDimension().width / 2));
      use.addAttribute(ISVGCascadeXMLConstants.ATTRIB_Y, String.valueOf(getLayer().getYPosition()));
      use.addAttribute(ISVGCascadeXMLConstants.ATTRIB_FILL, ISVGCascadeXMLConstants.VALUE_NONE);
      use.addAttribute(SVGCreationUtils.createXLinkQName(), ISVGCascadeXMLConstants.VALUE_FRAME_REFERENCE);
    }
    QName textName = SVGCreationUtils.createSVGQName(ISVGCascadeXMLConstants.TAG_TEXT);
    Element text = g.addElement(textName);
    {
      text.addAttribute(ISVGCascadeXMLConstants.ATTRIB_X, String.valueOf(getPosition()));
      text.addAttribute(ISVGCascadeXMLConstants.ATTRIB_Y, String.valueOf(getLayer().getYPosition()
          + getCharmDimension().getHeight()
          / 2));
      text.addAttribute(ISVGCascadeXMLConstants.ATTRIB_TEXT_ANCHOR, ISVGCascadeXMLConstants.VALUE_MIDDLE);
      text.addAttribute(ISVGCascadeXMLConstants.ATTRIB_FONT_SIZE, ISVGCascadeXMLConstants.VALUE_15);
      text.setText(getContentNode().getId());
    }
  }

  @Override
  protected IIdentifiedRegularNode getContentNode() {
    return (IIdentifiedRegularNode) super.getContentNode();
  }
}