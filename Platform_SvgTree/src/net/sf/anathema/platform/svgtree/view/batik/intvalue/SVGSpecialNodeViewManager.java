package net.sf.anathema.platform.svgtree.view.batik.intvalue;

import java.awt.Rectangle;

import net.sf.anathema.platform.svgtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;
import net.sf.anathema.platform.svgtree.presenter.view.ISpecialNodeViewManager;
import net.sf.anathema.platform.svgtree.view.batik.AnathemaCanvas;
import net.sf.anathema.platform.svgtree.view.batik.BoundsCalculator;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;

import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGGElement;
import org.w3c.dom.svg.SVGSVGElement;

public class SVGSpecialNodeViewManager implements ISpecialNodeViewManager {

  private final IAnathemaCanvas canvas;
  private final IBoundsCalculator boundsCalculator;

  public SVGSpecialNodeViewManager(AnathemaCanvas canvas, BoundsCalculator calculator) {
    this.canvas = canvas;
    this.boundsCalculator = calculator;
  }

  public void setVisible(final ISVGSpecialNodeView specialView, boolean visible) {
    if (!visible) {
      specialView.setVisible(false);
      return;
    }
    SVGOMDocument document = (SVGOMDocument) canvas.getSVGDocument();
    SVGSVGElement rootElement = document.getRootElement();
    Element viewElement = specialView.initGui(document, boundsCalculator);
    Rectangle bounds = getGroupBounds(specialView.getNodeId());
    if (bounds == null) {
      specialView.setVisible(false);
      return;
    }
    float xPosition = bounds.x / rootElement.getScreenCTM().getA();
    float yPosition = (bounds.y + bounds.height + 5) / rootElement.getScreenCTM().getD();
    DomUtilities.setAttribute(
        viewElement,
        SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
        "translate(" + xPosition + "," + yPosition + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    rootElement.appendChild(viewElement);
  }

  private Rectangle getGroupBounds(final String nodeId) {
    SVGGElement svgElement = (SVGGElement) canvas.getElementById(nodeId);
    if (svgElement == null) {
      return null;
    }
    return boundsCalculator.getBounds(svgElement);
  }
}