package net.sf.anathema.charmtree.provider.components.nodes;

import java.awt.Dimension;
import java.util.Map;

import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.IIdentifiedRegularNode;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.ISimpleNode;
import net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants;
import net.sf.anathema.charmtree.provider.svg.SVGCreationUtils;
import net.sf.anathema.lib.collection.MultiEntryMap;

import org.apache.batik.util.SVG12Constants;
import org.apache.batik.util.SVGConstants;
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
    QName group = SVGCreationUtils.createSVGQName(SVGConstants.SVG_G_TAG);
    Element g = element.addElement(group);
    g.addAttribute(SVGConstants.SVG_ID_ATTRIBUTE, getContentNode().getId());
    g.addAttribute("isCharm", "true");
    addUseElement(g);
    addTextElement(g);
    // addFlowTextElement(g);
  }

  private void addTextElement(Element g) {
    QName textName = SVGCreationUtils.createSVGQName(ISVGCascadeXMLConstants.TAG_TEXT);
    Element text = g.addElement(textName);
    {
      text.addAttribute(ISVGCascadeXMLConstants.ATTRIB_X, String.valueOf(getPosition()));
      text.addAttribute(ISVGCascadeXMLConstants.ATTRIB_Y, String.valueOf(getLayer().getYPosition()
          + getCharmDimension().getHeight()
          / 2));
      text.addAttribute(ISVGCascadeXMLConstants.ATTRIB_TEXT_ANCHOR, SVGConstants.SVG_MIDDLE_VALUE);
      text.addAttribute(ISVGCascadeXMLConstants.ATTRIB_FONT_SIZE, ISVGCascadeXMLConstants.VALUE_15);
      text.setText(getContentNode().getId());
    }
  }

  private void addFlowTextElement(Element g) {
    QName textName = SVGCreationUtils.createSVGQName(SVG12Constants.SVG_FLOW_ROOT_TAG);
    Element flowText = g.addElement(textName);
    Element region = flowText.addElement(SVGCreationUtils.createSVGQName(SVG12Constants.SVG_FLOW_REGION_TAG));
    Element regionRect = region.addElement(SVGCreationUtils.createSVGQName(SVGConstants.SVG_RECT_TAG));
    regionRect.addAttribute(SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(getPosition() - getCharmDimension().width / 2));
    regionRect.addAttribute(SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(getLayer().getYPosition()));
    regionRect.addAttribute(SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(getCharmDimension().getWidth()));
    regionRect.addAttribute(SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(getCharmDimension().getHeight()));
    regionRect.addAttribute("visibility", "hidden");
    Element flowDiv = flowText.addElement(SVGCreationUtils.createSVGQName(SVG12Constants.SVG_FLOW_DIV_TAG));
    Element paragraph = flowDiv.addElement(SVGCreationUtils.createSVGQName(SVG12Constants.SVG_FLOW_PARA_TAG));
    paragraph.addAttribute(SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_15);
    paragraph.addAttribute("text-align", SVGConstants.SVG_MIDDLE_VALUE);
    paragraph.addAttribute("margin-top", "10");
    paragraph.addAttribute("margin-bottom", "10");
    paragraph.addAttribute("margin-left", "10");
    paragraph.addAttribute("margin-right", "10");
    paragraph.setText(getContentNode().getId());
  }

  private void addUseElement(Element g) {
    QName useName = SVGCreationUtils.createSVGQName(SVGConstants.SVG_USE_TAG);
    Element use = g.addElement(useName);
    use.addAttribute(SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(getPosition() - getCharmDimension().width / 2));
    use.addAttribute(SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(getLayer().getYPosition()));
    use.addAttribute(SVGConstants.SVG_FILL_ATTRIBUTE, SVGConstants.SVG_NONE_VALUE);
    use.addAttribute(SVGCreationUtils.createXLinkQName(), ISVGCascadeXMLConstants.VALUE_FRAME_REFERENCE);
  }

  @Override
  protected IIdentifiedRegularNode getContentNode() {
    return (IIdentifiedRegularNode) super.getContentNode();
  }
}