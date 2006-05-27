package net.sf.anathema.charmtree.batik.intvalue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.charmtree.batik.IBoundsCalculator;
import net.sf.anathema.charmtree.presenter.view.ISVGSpecialCharmView;
import net.sf.anathema.framework.value.IBooleanValueView;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;

public class SVGSubeffectCharmView implements ISVGSpecialCharmView {

  private final String id;
  private final double width;
  private final Color color;
  private final List<SVGBooleanValueDisplay> effects = new ArrayList<SVGBooleanValueDisplay>();

  public SVGSubeffectCharmView(String id, double width, Color color) {
    this.id = id;
    this.width = width;
    this.color = color;
  }

  public Element initGui(SVGOMDocument document, IBoundsCalculator boundsCalculator) {
    Element groupElement = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_G_TAG);
    for (int index = 0; index < effects.size(); index++) {
      Element displayElement = effects.get(index).initGui(document);
      DomUtilities.setAttribute(
          displayElement,
          SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
          "translate(0," + index * SVGIntValueDisplay.getDiameter(width) * 1.1 + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      groupElement.appendChild(displayElement);
    }
    return groupElement;
  }

  public void reset() {
    // Nothing to do
  }

  public void setVisible(boolean visible) {
    for (SVGBooleanValueDisplay effect : effects) {
      effect.setVisible(visible);
    }
  }

  public String getCharmId() {
    return id;
  }

  public IBooleanValueView addSubeffect(String label) {
    SVGBooleanValueDisplay display = new SVGBooleanValueDisplay(label, width, color);
    effects.add(display);
    return display;
  }
}