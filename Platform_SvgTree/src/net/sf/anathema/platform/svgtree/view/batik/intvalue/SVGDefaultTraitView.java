package net.sf.anathema.platform.svgtree.view.batik.intvalue;

import java.awt.Color;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class SVGDefaultTraitView implements IIntValueView {

  private final SVGIntValueDisplay valueDisplay;
  private final String labelString;
  private final double maxWidth;
  private final int widthInDots;

  public SVGDefaultTraitView(
      final int maxValue,
      final int widthInDots,
      final double maxWidth,
      final Color fillColor,
      final String labelString,
      final int initialValue) {
    this.widthInDots = widthInDots;
    this.maxWidth = maxWidth;
    this.labelString = labelString;
    this.valueDisplay = new SVGIntValueDisplay(
        maxValue,
        widthInDots,
        fillColor,
        initialValue,
        SVGIntValueDisplay.getDiameter(maxWidth));
  }

  public Element initGui(final SVGOMDocument svgDocument, final IBoundsCalculator boundsCalculator) {
    Element groupElement = svgDocument.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_G_TAG);
    Element textElement = svgDocument.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_TEXT_TAG);
    setAttribute(
        textElement,
        SVGConstants.SVG_Y_ATTRIBUTE,
        String.valueOf(SVGIntValueDisplay.getDiameter(maxWidth) * 0.9));
    Text text = svgDocument.createTextNode(labelString);
    textElement.appendChild(text);
    groupElement.appendChild(textElement);
    Element valueGroupElement = valueDisplay.initGui(svgDocument, boundsCalculator);
    setAttribute(valueGroupElement, SVGConstants.SVG_TRANSFORM_ATTRIBUTE, "translate(" //$NON-NLS-1$
        + String.valueOf(maxWidth - widthInDots * SVGIntValueDisplay.getDiameter(maxWidth) * 1.11)
        + ",0)"); //$NON-NLS-1$
    groupElement.appendChild(valueGroupElement);
    return groupElement;
  }

  private void setAttribute(final org.w3c.dom.Element element, final String attributeName, final String attributeValue) {
    element.setAttributeNS(null, attributeName, attributeValue);
  }

  public void setValue(final int newValue) {
    valueDisplay.setValue(newValue);
  }

  public void addIntValueChangedListener(final IIntValueChangedListener listener) {
    valueDisplay.addIntValueChangedListener(listener);

  }

  public void removeIntValueChangedListener(final IIntValueChangedListener listener) {
    valueDisplay.removeIntValueChangedListener(listener);
  }

  public void setMaximum(final int maximalValue) {
    valueDisplay.setMaximum(maximalValue);
  }

  public void setVisible(final boolean visible) {
    valueDisplay.setVisible(visible);
  }
}