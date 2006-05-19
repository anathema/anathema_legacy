package net.sf.anathema.charmtree.batik.intvalue;

import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_TRANSFORM;

import java.awt.Rectangle;

import net.sf.anathema.charmtree.batik.IBoundsCalculator;
import net.sf.anathema.charmtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeView;
import net.sf.anathema.charmtree.presenter.view.ISVGMultiLearnableCharmView;
import net.sf.anathema.charmtree.presenter.view.ISpecialCharmViewManager;

import org.apache.batik.dom.svg.SVGOMDocument;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGSVGElement;

public class SVGSpecialCharmViewManager implements ISpecialCharmViewManager<ISVGMultiLearnableCharmView> {

  private final IAnathemaCanvas canvas;
  private final IBoundsCalculator boundsCalculator;

  public SVGSpecialCharmViewManager(ICharmTreeView view) {
    this.canvas = view.getCanvas();
    this.boundsCalculator = view.getBoundsCalculator();
  }

  public void setSpecialCharmViewVisible(ICharmTreeView view, ISVGMultiLearnableCharmView charmView, boolean visible) {
    SVGOMDocument document = (SVGOMDocument) canvas.getSVGDocument();
    if (!visible) {
      charmView.setVisible(false);
    }
    else {
      SVGSVGElement rootElement = document.getRootElement();
      Element viewElement = charmView.initGui(document, boundsCalculator);
      Rectangle bounds = view.getGroupBounds(charmView.getCharmId());
      float xPosition = bounds.x / rootElement.getScreenCTM().getA();
      float yPosition = (bounds.y + bounds.height + 5) / rootElement.getScreenCTM().getD();
      setAttribute(viewElement, ATTRIB_TRANSFORM, "translate(" + xPosition + "," + yPosition + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      rootElement.appendChild(viewElement);
    }
  }

  private void setAttribute(Element element, String attributeName, String attributeValue) {
    element.setAttributeNS(null, attributeName, attributeValue);
  }
}