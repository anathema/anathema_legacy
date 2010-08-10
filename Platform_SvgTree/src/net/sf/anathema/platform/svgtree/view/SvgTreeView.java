package net.sf.anathema.platform.svgtree.view;

import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.*;
import static org.apache.batik.util.SVGConstants.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants;
import net.sf.anathema.platform.svgtree.presenter.view.IDocumentLoadedListener;
import net.sf.anathema.platform.svgtree.presenter.view.INodeSelectionListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISpecialNodeViewManager;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;
import net.sf.anathema.platform.svgtree.view.batik.AnathemaCanvas;
import net.sf.anathema.platform.svgtree.view.batik.BoundsCalculator;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.DomUtilities;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGSpecialNodeViewManager;
import net.sf.anathema.platform.svgtree.view.listening.SvgTreeListening;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherAdapter;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherEvent;
import org.dom4j.DocumentException;
import org.dom4j.io.DOMWriter;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGElement;
import org.w3c.dom.svg.SVGGElement;
import org.w3c.dom.svg.SVGTextElement;
import org.w3c.dom.svg.SVGUseElement;

public class SvgTreeView implements ISvgTreeView {

  private AnathemaCanvas canvas = new AnathemaCanvas();
  private final ISvgTreeViewProperties properties;
  private final SvgTreeListening listening;
  private final SVGSpecialNodeViewManager manager;
  private final IBoundsCalculator calculator;

  public SvgTreeView(final ISvgTreeViewProperties properties) {
    this.properties = properties;
    this.calculator = new BoundsCalculator();
    this.manager = new SVGSpecialNodeViewManager(canvas, calculator);
    this.listening = new SvgTreeListening(canvas, calculator, properties);
    addDocumentLoadedListener(new IDocumentLoadedListener() {
      public void documentLoaded() {
        initNodeNames();
      }
    });
    canvas.addGVTTreeRendererListener(new GVTTreeRendererAdapter() {
      @Override
      public void gvtRenderingCompleted(GVTTreeRendererEvent e) {
        listening.initDocumentListening(canvas.getSVGDocument());
      }
    });
  }

  public JComponent getComponent() {
    return canvas;
  }

  @Override
  public ISpecialNodeViewManager getSpecialViewManager() {
    return manager;
  }

  public void loadCascade(final org.dom4j.Document dom4jDocument) throws DocumentException {
    calculator.reset();
    listening.destructDocumentListening(canvas.getSVGDocument());
    SVGDocument document = null;
    if (dom4jDocument != null) {
      DOMImplementation implementation = SVGDOMImplementation.getDOMImplementation();
      document = (SVGDocument) new DOMWriter().write(dom4jDocument, implementation);
      injectGlassPane(document);
    }
    canvas.setDocument(document);
  }

  private void injectGlassPane(SVGDocument document) {
    Element rectangle = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVG_RECT_TAG);
    DomUtilities.setAttribute(rectangle, SVG_X_ATTRIBUTE, SVG_ZERO_VALUE);
    DomUtilities.setAttribute(rectangle, SVG_Y_ATTRIBUTE, SVG_ZERO_VALUE);
    DomUtilities.setAttribute(rectangle, SVG_WIDTH_ATTRIBUTE, VALUE_3000);
    DomUtilities.setAttribute(rectangle, SVG_HEIGHT_ATTRIBUTE, VALUE_1500);
    DomUtilities.setAttribute(rectangle, ATTRIB_VISIBILITY, ISVGCascadeXMLConstants.VALUE_HIDDEN);
    DomUtilities.setAttribute(rectangle, ATTRIB_POINTER_EVENTS, VALUE_FILL);
    document.getDocumentElement().insertBefore(rectangle, document.getElementById(VALUE_CASCADE_ID));
  }

  public void addNodeSelectionListener(final INodeSelectionListener listener) {
    listening.addNodeSelectionListener(listener);
  }

  public void setNodeBackgroundColor(final String nodeId, final Color color) {
    Ensure.ensureNotNull("Color must not be null.", color); //$NON-NLS-1$
    SVGElement nodeGroup = (SVGElement) canvas.getElementById(nodeId);
    if (nodeGroup == null) {
      return;
    }
    NodeList list = nodeGroup.getChildNodes();
    for (int i = 0; i < list.getLength(); i++) {
      if (list.item(i) instanceof SVGUseElement) {
        SVGElement element = (SVGElement) list.item(i);
        element.setAttribute(SVG_FILL_ATTRIBUTE, "rgb(" //$NON-NLS-1$
            + color.getRed()
            + "," //$NON-NLS-1$
            + color.getGreen()
            + "," //$NON-NLS-1$
            + color.getBlue()
            + ")"); //$NON-NLS-1$
        element.setAttribute(SVG_FILL_OPACITY_ATTRIBUTE, String.valueOf((float) color.getAlpha() / 255));
      }
    }
  }

  public void setNodeAlpha(final String nodeId, int alpha) {
    if (alpha > 255) {
      alpha = 255;
    }
    if (alpha < 0) {
      alpha = 0;
    }
    SVGElement nodeGroup = (SVGElement) canvas.getElementById(nodeId);
    if (nodeGroup == null) {
      return;
    }
    nodeGroup.setAttribute(SVG_OPACITY_ATTRIBUTE, String.valueOf((float) alpha / 255));
  }

  public void addDocumentLoadedListener(final IDocumentLoadedListener listener) {
    canvas.addSVGLoadEventDispatcherListener(new SVGLoadEventDispatcherAdapter() {
      @Override
      public void svgLoadEventDispatchCompleted(final SVGLoadEventDispatcherEvent arg0) {
        listener.documentLoaded();
      }
    });
  }

  private void initNodeNames() {
    for (SVGGElement groupElement : canvas.getNodeElements()) {
      NodeList textNodes = groupElement.getElementsByTagName(SVG_TEXT_TAG);
      for (int i = 0; i < textNodes.getLength(); i++) {
        SVGTextElement currentNode = (SVGTextElement) textNodes.item(i);
        internationalize(currentNode);
        breakText(currentNode);
      }
    }
  }

  public void setCanvasBackground(final Color color) {
    canvas.setBackground(color);
  }

  public void dispose() {
    SVGDocument document = canvas.getSVGDocument();
    if (document != null) {
      listening.destructDocumentListening(document);
    }
    canvas.setDocument(null);
    // todo vom (22.02.2005) (sieroux): Notify pool of unused canvas
    canvas = null;
  }

  private void breakText(final SVGTextElement text) {
    float textLength = text.getComputedTextLength();
    int lines = Math.min(3, (int) Math.ceil(textLength / 95));
    Document document = text.getOwnerDocument();
    Text oldNode = (Text) text.getFirstChild();
    Text[] textNodes = new Text[lines];
    int lineHeight = 16;
    float varY = Float.valueOf(text.getAttribute(SVG_Y_ATTRIBUTE));
    varY += lineHeight - lines * lineHeight / 2 - (lines == 1 ? 2 : 0);
    List<Integer> wrap = new ArrayList<Integer>();
    wrap.add(0);
    for (int breakpoint : AnathemaStringUtilities.findBreakPoints(oldNode.getData(), lines)) {
      wrap.add(breakpoint);
    }
    wrap.add(oldNode.getData().length());
    for (int index = 0; index < lines; index++) {
      int startIndex = wrap.get(index);
      int runLength = wrap.get(index + 1) - startIndex;
      textNodes[index] = document.createTextNode(oldNode.substringData(startIndex, runLength).trim());
    }
    text.removeChild(oldNode);
    Element[] lineBreak = new Element[lines];
    String xPosition = text.getAttribute(SVG_X_ATTRIBUTE);
    for (int index = 0; index < lineBreak.length; index++) {
      lineBreak[index] = createTSpanElement(document, textNodes[index], xPosition, varY, lineHeight * index);
      text.appendChild(lineBreak[index]);
    }
  }

  private Element createTSpanElement(
      final Document document,
      final Text textNode,
      final String xPosition,
      final float varY,
      final int dy) {
    Element tSpanElement = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVG_TSPAN_TAG);
    tSpanElement.setAttribute(SVG_X_ATTRIBUTE, xPosition);
    tSpanElement.setAttribute(SVG_Y_ATTRIBUTE, String.valueOf(varY));
    tSpanElement.setAttribute(SVG_DX_ATTRIBUTE, SVG_ZERO_VALUE);
    tSpanElement.setAttribute(SVG_DY_ATTRIBUTE, String.valueOf(dy));
    tSpanElement.setAttribute(ISVGCascadeXMLConstants.ATTRIB_POINTER_EVENTS, SVG_NONE_VALUE);
    tSpanElement.appendChild(textNode);
    return tSpanElement;
  }

  private void internationalize(final SVGTextElement text) {
    String id = ((Text) text.getFirstChild()).getData();
    String nodeName = properties.getNodeName(id);
    if (properties.isRootNode(id)) {
      text.getFirstChild().setNodeValue(nodeName.toUpperCase());
    }
    else {
      text.getFirstChild().setNodeValue(nodeName);
    }
  }
}