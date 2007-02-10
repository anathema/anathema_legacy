package net.sf.anathema.demo.charm.tree.batik.intvalue;

import java.awt.Color;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.platform.svgtree.view.batik.AnathemaCanvas;
import net.sf.anathema.platform.svgtree.view.batik.BoundsCalculator;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGIntValueView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGIntValueDisplay;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGCategorizedSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGViewControlButton;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.jdemo.extensions.SwingDemoCase;

public class SVGIntValueDisplayDemo extends SwingDemoCase {
  private static final int MAX_DOTS = 7;

  public void demoSVGIntValueDisplay() throws Exception {
    DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
    Document document = impl.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null); //$NON-NLS-1$
    AnathemaCanvas canvas = new AnathemaCanvas();
    canvas.setDocument(document);
    SVGIntValueDisplay display = new SVGIntValueDisplay(7, 7, Color.GREEN, 3, 10);
    display.addIntValueChangedListener(new IIntValueChangedListener() {
      public void valueChanged(final int newValue) {
        System.out.println(newValue);
      }
    });
    Element element = display.initGui((SVGOMDocument) canvas.getSVGDocument(), new BoundsCalculator());
    canvas.getSVGDocument().getRootElement().appendChild(element);
    show(canvas);
  }

  public void demoSVGTraitView() throws Exception {
    DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
    Document document = impl.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null); //$NON-NLS-1$
    AnathemaCanvas canvas = new AnathemaCanvas();
    canvas.setDocument(document);
    SVGIntValueView display = new SVGIntValueView(7, 7, 130, Color.GREEN, "Testtext", 4); //$NON-NLS-1$
    display.addIntValueChangedListener(new IIntValueChangedListener() {
      public void valueChanged(final int newValue) {
        System.out.println(newValue);
      }
    });
    Element element = display.initGui((SVGOMDocument) canvas.getSVGDocument(), new BoundsCalculator());
    canvas.getSVGDocument().getRootElement().appendChild(element);
    show(canvas);
  }

  public void demoSVGMultiLearnableCharmView() throws Exception {
    DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
    Document document = impl.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null); //$NON-NLS-1$
    AnathemaCanvas canvas = new AnathemaCanvas();
    canvas.setDocument(document);
    SVGCategorizedSpecialNodeView display = new SVGCategorizedSpecialNodeView("MyCharm", 190, Color.GREEN, MAX_DOTS); //$NON-NLS-1$
    display.addCategory("First", 7, 2); //$NON-NLS-1$
    display.addCategory("Second", 4, 4); //$NON-NLS-1$
    display.addCategory("Third", 6, 3); //$NON-NLS-1$
    Element element = display.initGui((SVGOMDocument) canvas.getSVGDocument(), new BoundsCalculator());
    canvas.getSVGDocument().getRootElement().appendChild(element);
    show(canvas);
  }

  public void demoButtonExpandedSVGMultiLearnableCharmView() {
    DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
    Document document = impl.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null); //$NON-NLS-1$
    AnathemaCanvas canvas = new AnathemaCanvas();
    canvas.setDocument(document);
    SVGCategorizedSpecialNodeView display = new SVGCategorizedSpecialNodeView("MyCharm", 190, Color.GREEN, MAX_DOTS); //$NON-NLS-1$
    display.addCategory("First", 7, 2); //$NON-NLS-1$
    display.addCategory("Second", 4, 4); //$NON-NLS-1$
    display.addCategory("Third", 6, 3); //$NON-NLS-1$
    final BoundsCalculator boundsCalculator = new BoundsCalculator();
    final SVGViewControlButton buttonView = new SVGViewControlButton(display, 190, "Categories"); //$NON-NLS-1$
    Element buttonElement = buttonView.initGui((SVGOMDocument) canvas.getSVGDocument(), boundsCalculator);
    canvas.getSVGDocument().getRootElement().appendChild(buttonElement);
    show(canvas);
  }

  public void demoButtonFadeout() {
    DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
    Document document = impl.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null); //$NON-NLS-1$
    AnathemaCanvas canvas = new AnathemaCanvas();
    canvas.setDocument(document);
    SVGCategorizedSpecialNodeView display = new SVGCategorizedSpecialNodeView("MyCharm", 190, Color.GREEN, MAX_DOTS); //$NON-NLS-1$
    display.addCategory("First", 7, 2); //$NON-NLS-1$
    final BoundsCalculator boundsCalculator = new BoundsCalculator();
    final SVGViewControlButton buttonView = new SVGViewControlButton(display, 190, "Categories"); //$NON-NLS-1$
    Element circle = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_CIRCLE_TAG);
    circle.setAttributeNS(null, SVGConstants.SVG_R_ATTRIBUTE, "50"); //$NON-NLS-1$
    canvas.getSVGDocument().getRootElement().appendChild(circle);
    Element buttonElement = buttonView.initGui((SVGOMDocument) canvas.getSVGDocument(), boundsCalculator);
    canvas.getSVGDocument().getRootElement().appendChild(buttonElement);
    show(canvas);
  }
}