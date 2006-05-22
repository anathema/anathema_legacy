package net.sf.anathema.charmtree.batik.intvalue;

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
          SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
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
    setAttribute(rectangle, SVGConstants.SVG_X_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    setAttribute(rectangle, SVGConstants.SVG_Y_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    setAttribute(rectangle, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(charmWidth));
    setAttribute(rectangle, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(categories.size()
        * SVGIntValueDisplay.getDiameter(charmWidth)
        * 1.15));
    setAttribute(rectangle, SVGConstants.SVG_STROKE_ATTRIBUTE, VALUE_COLOR_BLACK);
    setAttribute(rectangle, SVGConstants.SVG_FILL_ATTRIBUTE, VALUE_COLOR_BLACK);
    setAttribute(rectangle, SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
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