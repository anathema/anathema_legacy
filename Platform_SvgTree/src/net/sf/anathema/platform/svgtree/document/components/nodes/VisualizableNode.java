package net.sf.anathema.platform.svgtree.document.components.nodes;

import java.awt.Dimension;
import java.util.Map;

import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.platform.svgtree.document.svg.ISVGCascadeXMLConstants;
import net.sf.anathema.platform.svgtree.document.svg.SVGCreationUtils;
import net.sf.anathema.platform.svgtree.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.platform.svgtree.graph.nodes.ISimpleNode;

import org.apache.batik.util.SVG12Constants;
import org.apache.batik.util.SVGConstants;
import org.dom4j.Element;
import org.dom4j.QName;

public class VisualizableNode extends AbstractSingleVisualizableNode {

  public void accept(final IVisualizableNodeVisitor visitor) {
    visitor.visitSingleNode(this);
  }

  public VisualizableNode(
      final IIdentifiedRegularNode contentNode,
      final Map<ISimpleNode, IVisualizableNode> map,
      final Dimension nodeDimension,
      final MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors) {
    super(contentNode, map, nodeDimension, leafNodesByAncestors);
  }

  public void toXML(final Element element) {
    QName group = SVGCreationUtils.createSVGQName(SVGConstants.SVG_G_TAG);
    Element g = element.addElement(group);
    g.addAttribute(SVGConstants.SVG_ID_ATTRIBUTE, getContentNode().getId());
    g.addAttribute(ISVGCascadeXMLConstants.ATTRIB_IS_LISTENER_REQUIRED, SVGConstants.SVG_TRUE_VALUE);
    addUseElement(g);
    addTextElement(g);
    // addFlowTextElement(g);
  }

  private void addTextElement(final Element g) {
    QName textName = SVGCreationUtils.createSVGQName(SVGConstants.SVG_TEXT_TAG);
    Element text = g.addElement(textName);
    {
      text.addAttribute(SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(getPosition()));
      text.addAttribute(SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(getLayer().getYPosition()
          + getNodeDimension().getHeight()
          / 2));
      text.addAttribute(SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE, SVGConstants.SVG_MIDDLE_VALUE);
      text.addAttribute(SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_15);
      text.setText(getContentNode().getId());
    }
  }

  @SuppressWarnings("unused")
  private void addFlowTextElement(final Element g) {
    QName textName = SVGCreationUtils.createSVGQName(SVG12Constants.SVG_FLOW_ROOT_TAG);
    Element flowText = g.addElement(textName);
    Element region = flowText.addElement(SVGCreationUtils.createSVGQName(SVG12Constants.SVG_FLOW_REGION_TAG));
    Element regionRect = region.addElement(SVGCreationUtils.createSVGQName(SVGConstants.SVG_RECT_TAG));
    regionRect.addAttribute(SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(getPosition() - getNodeDimension().width / 2));
    regionRect.addAttribute(SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(getLayer().getYPosition()));
    regionRect.addAttribute(SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(getNodeDimension().getWidth()));
    regionRect.addAttribute(SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(getNodeDimension().getHeight()));
    regionRect.addAttribute("visibility", "hidden"); //$NON-NLS-1$ //$NON-NLS-2$
    Element flowDiv = flowText.addElement(SVGCreationUtils.createSVGQName(SVG12Constants.SVG_FLOW_DIV_TAG));
    Element paragraph = flowDiv.addElement(SVGCreationUtils.createSVGQName(SVG12Constants.SVG_FLOW_PARA_TAG));
    paragraph.addAttribute(SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_15);
    paragraph.addAttribute("text-align", SVGConstants.SVG_MIDDLE_VALUE); //$NON-NLS-1$
    paragraph.addAttribute("margin-top", "10"); //$NON-NLS-1$ //$NON-NLS-2$
    paragraph.addAttribute("margin-bottom", "10"); //$NON-NLS-1$ //$NON-NLS-2$
    paragraph.addAttribute("margin-left", "10"); //$NON-NLS-1$ //$NON-NLS-2$
    paragraph.addAttribute("margin-right", "10"); //$NON-NLS-1$ //$NON-NLS-2$
    paragraph.setText(getContentNode().getId());
  }

  private void addUseElement(final Element g) {
    QName useName = SVGCreationUtils.createSVGQName(SVGConstants.SVG_USE_TAG);
    Element use = g.addElement(useName);
    use.addAttribute(SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(getPosition() - getNodeDimension().width / 2));
    use.addAttribute(SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(getLayer().getYPosition()));
    use.addAttribute(SVGConstants.SVG_FILL_ATTRIBUTE, SVGConstants.SVG_NONE_VALUE);
    use.addAttribute(SVGCreationUtils.createXLinkQName(), ISVGCascadeXMLConstants.VALUE_FRAME_REFERENCE);
  }

  @Override
  protected IIdentifiedRegularNode getContentNode() {
    return (IIdentifiedRegularNode) super.getContentNode();
  }
}