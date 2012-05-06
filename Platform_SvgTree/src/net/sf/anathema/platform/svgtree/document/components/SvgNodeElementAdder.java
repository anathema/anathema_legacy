package net.sf.anathema.platform.svgtree.document.components;

import net.sf.anathema.platform.svgtree.document.util.SVGCreationUtils;
import net.sf.anathema.platform.svgtree.document.visualizer.NodeAdder;
import org.apache.batik.util.SVGConstants;
import org.dom4j.Element;
import org.dom4j.QName;

import java.awt.Dimension;

import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.ATTRIB_IS_TREE_NODE;
import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.ATTRIB_POINTER_EVENTS;
import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.VALUE_FRAME_REFERENCE;
import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.VALUE_VISIBLE;
import static org.apache.batik.util.SVGConstants.SVG_FILL_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_FONT_SIZE_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_MIDDLE_VALUE;
import static org.apache.batik.util.SVGConstants.SVG_NONE_VALUE;
import static org.apache.batik.util.SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_USE_TAG;
import static org.apache.batik.util.SVGConstants.SVG_X_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_Y_ATTRIBUTE;

public class SvgNodeElementAdder implements NodeAdder<Element> {

  private final String id;
  private final Integer xPosition;
  private final Dimension nodeDimension;
  private final int yPosition;

  public SvgNodeElementAdder(String id, Dimension nodeDimension, Integer xPosition, int yPosition) {
    this.id = id;
    this.xPosition = xPosition;
    this.nodeDimension = nodeDimension;
    this.yPosition = yPosition;
  }

  @Override
  public void addTo(Element element) {
    QName group = SVGCreationUtils.createSVGQName(SVGConstants.SVG_G_TAG);
    Element g = element.addElement(group);
    g.addAttribute(SVGConstants.SVG_ID_ATTRIBUTE, id);
    g.addAttribute(ATTRIB_IS_TREE_NODE, SVGConstants.SVG_TRUE_VALUE);
    addUseElement(g);
    addTextElement(g);
  }

  private void addTextElement(Element g) {
    QName textName = SVGCreationUtils.createSVGQName(SVGConstants.SVG_TEXT_TAG);
    Element text = g.addElement(textName);
    text.addAttribute(SVG_X_ATTRIBUTE, String.valueOf(xPosition));
    text.addAttribute(SVG_Y_ATTRIBUTE, String.valueOf(yPosition + nodeDimension.getHeight() / 2));
    text.addAttribute(SVG_TEXT_ANCHOR_ATTRIBUTE, SVG_MIDDLE_VALUE);
    text.addAttribute(SVG_FONT_SIZE_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_15);
    text.addAttribute(ATTRIB_POINTER_EVENTS, SVG_NONE_VALUE);
    text.setText(id);

  }

  private void addUseElement(Element g) {
    QName useName = SVGCreationUtils.createSVGQName(SVG_USE_TAG);
    Element use = g.addElement(useName);
    use.addAttribute(SVG_X_ATTRIBUTE, String.valueOf(xPosition - nodeDimension.width / 2));
    use.addAttribute(SVG_Y_ATTRIBUTE, String.valueOf(yPosition));
    use.addAttribute(ATTRIB_POINTER_EVENTS, VALUE_VISIBLE);
    use.addAttribute(SVG_FILL_ATTRIBUTE, SVG_NONE_VALUE);
    use.addAttribute(SVGCreationUtils.createXLinkQName(), VALUE_FRAME_REFERENCE);
  }
}