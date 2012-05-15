package net.sf.anathema.platform.svgtree.view.batik.intvalue;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.awt.Color;

public class SVGIntValueView implements IIntValueView {

  private final SVGIntValueDisplay valueDisplay;
  private final String labelString;
  private final double maxWidth;
  private final double dotDiameter;
  private final int widthInDots;

  public SVGIntValueView(
      int maxValue,
      int widthInDots,
      double maxWidth,
      Color fillColor,
      String labelString,
      int initialValue) {
    this.widthInDots = widthInDots;
    this.maxWidth = maxWidth;
    this.labelString = labelString;
    this.dotDiameter = SVGIntValueDisplay.getDisplayDiameter(maxWidth, widthInDots);
    this.valueDisplay = new SVGIntValueDisplay(
        maxValue,
        widthInDots,
        fillColor,
        initialValue,
        dotDiameter);
  }

  public Element initGui(SVGOMDocument svgDocument, IBoundsCalculator boundsCalculator) {
    Element groupElement = svgDocument.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_G_TAG);
    Element textElement = svgDocument.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_TEXT_TAG);
    setAttribute(
        textElement,
        SVGConstants.SVG_Y_ATTRIBUTE,
        String.valueOf(dotDiameter * 0.9));
    setAttribute(
            textElement,
            SVGConstants.SVG_HEIGHT_ATTRIBUTE,
            String.valueOf(dotDiameter));
    Text text = svgDocument.createTextNode(labelString);
    textElement.appendChild(text);
    groupElement.appendChild(textElement);
    Element valueGroupElement = valueDisplay.initGui(svgDocument, boundsCalculator);
    setAttribute(valueGroupElement, SVGConstants.SVG_TRANSFORM_ATTRIBUTE, "translate(" //$NON-NLS-1$
        + String.valueOf(maxWidth - widthInDots * dotDiameter * 1.11)
        + ",0)"); //$NON-NLS-1$
    groupElement.appendChild(valueGroupElement);
    return groupElement;
  }

  private void setAttribute(org.w3c.dom.Element element, String attributeName, String attributeValue) {
    element.setAttributeNS(null, attributeName, attributeValue);
  }

  @Override
  public void setValue(int newValue) {
    valueDisplay.setValue(newValue);
  }

  @Override
  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    valueDisplay.addIntValueChangedListener(listener);

  }

  @Override
  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    valueDisplay.removeIntValueChangedListener(listener);
  }

  @Override
  public void setMaximum(int maximalValue) {
    valueDisplay.setMaximum(maximalValue);
  }

  public void setVisible(boolean visible) {
    valueDisplay.setVisible(visible);
  }
}