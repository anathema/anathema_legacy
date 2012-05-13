package net.sf.anathema.platform.svgtree.view.batik.intvalue;

import net.sf.anathema.platform.svgtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;
import net.sf.anathema.platform.svgtree.presenter.view.ISpecialNodeViewManager;
import net.sf.anathema.platform.svgtree.view.batik.AnathemaCanvas;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGGElement;
import org.w3c.dom.svg.SVGMatrix;
import org.w3c.dom.svg.SVGSVGElement;

import java.awt.Rectangle;
import static org.apache.batik.util.SVGConstants.*;
import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.*;

public class SVGSpecialNodeViewManager implements ISpecialNodeViewManager {

  private final IAnathemaCanvas canvas;
  private final IBoundsCalculator calculator;

  public SVGSpecialNodeViewManager(AnathemaCanvas canvas, IBoundsCalculator calculator) {
    this.canvas = canvas;
    this.calculator = calculator;
  }

  @Override
  public void setVisible(ISVGSpecialNodeView specialView, boolean visible) {
    if (!visible) {
      specialView.hide();
      return;
    }
    Rectangle bounds = getGroupBounds(specialView.getNodeId());
    if (bounds == null) {
      specialView.hide();
      return;
    }
    SVGOMDocument document = (SVGOMDocument) canvas.getSVGDocument();
    Element viewElement = specialView.initGui(document, calculator);
    DomUtilities.setAttribute(viewElement, ATTRIB_IS_CONTROL, SVG_TRUE_VALUE);
    SVGSVGElement rootElement = document.getRootElement();
    SVGMatrix screenCTM = rootElement.getScreenCTM();
    float xPosition = bounds.x / screenCTM.getA();
    float yPosition = (bounds.y + bounds.height + 5) / screenCTM.getD();
    DomUtilities.setAttribute(viewElement, SVG_TRANSFORM_ATTRIBUTE, "translate(" + xPosition + "," + yPosition + ")");
    rootElement.appendChild(viewElement);
  }

  private Rectangle getGroupBounds(String nodeId) {
    SVGGElement svgElement = (SVGGElement) canvas.getElementById(nodeId);
    if (svgElement == null) {
      return null;
    }
    return calculator.getBounds(svgElement);
  }
}