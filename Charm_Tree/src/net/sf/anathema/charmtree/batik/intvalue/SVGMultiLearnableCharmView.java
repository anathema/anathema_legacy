package net.sf.anathema.charmtree.batik.intvalue;

import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_FILL;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_FILL_OPACITY;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_HEIGHT;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_STROKE;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_TRANSFORM;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_WIDTH;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_X;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_Y;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_COLOR_BLACK;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.charmtree.batik.IBoundsCalculator;
import net.sf.anathema.charmtree.presenter.view.ISVGMultiLearnableCharmView;
import net.sf.anathema.framework.value.IIntValueView;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

public class SVGMultiLearnableCharmView implements ISVGMultiLearnableCharmView {

  private final String charmId;
  private final double charmWidth;
  private final Color charmColor;
  private final List<SVGDefaultTraitView> categories = new ArrayList<SVGDefaultTraitView>();

  public SVGMultiLearnableCharmView(String charmId, double charmWidth, Color charmColor) {
    this.charmId = charmId;
    this.charmWidth = charmWidth;
    this.charmColor = charmColor;
  }

  public String getCharmId() {
    return charmId;
  }

  public void setVisible(boolean visible) {
    for (SVGDefaultTraitView view : categories) {
      view.setVisible(visible);
    }
  }

  public Element initGui(SVGOMDocument document, IBoundsCalculator boundsCalculator) {
    Element groupElement = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_G_TAG);
    groupElement.appendChild(createBorder(document));
    for (int index = 0; index < categories.size(); index++) {
      Element displayElement = categories.get(index).initGui(document, boundsCalculator);
      setAttribute(
          displayElement,
          ATTRIB_TRANSFORM,
          "translate(0," + index * SVGIntValueDisplay.getDiameter(charmWidth) * 1.1 + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      groupElement.appendChild(displayElement);
    }
    return groupElement;
  }

  private void setAttribute(org.w3c.dom.Element element, String attributeName, String attributeValue) {
    element.setAttributeNS(null, attributeName, attributeValue);
  }

  private Element createBorder(SVGDocument document) {
    Element rectangle = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_RECT_TAG);
    setAttribute(rectangle, ATTRIB_X, SVGConstants.SVG_ZERO_VALUE);
    setAttribute(rectangle, ATTRIB_Y, SVGConstants.SVG_ZERO_VALUE);
    setAttribute(rectangle, ATTRIB_WIDTH, String.valueOf(charmWidth));
    setAttribute(rectangle, ATTRIB_HEIGHT, String.valueOf(categories.size()
        * SVGIntValueDisplay.getDiameter(charmWidth)
        * 1.15));
    setAttribute(rectangle, ATTRIB_STROKE, VALUE_COLOR_BLACK);
    setAttribute(rectangle, ATTRIB_FILL, VALUE_COLOR_BLACK);
    setAttribute(rectangle, ATTRIB_FILL_OPACITY, SVGConstants.SVG_ZERO_VALUE);
    return rectangle;
  }

  public IIntValueView addCategory(String labelText, int maxValue, int value) {
    SVGDefaultTraitView view = new SVGDefaultTraitView(maxValue, charmWidth, charmColor, labelText, value);
    categories.add(view);
    return view;
  }

  public void reset() {
    // Nothing to do
  }
}