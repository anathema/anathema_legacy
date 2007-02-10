package net.sf.anathema.platform.svgtree.view;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.platform.svgtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.platform.svgtree.presenter.view.ICharmSelectionListener;
import net.sf.anathema.platform.svgtree.presenter.view.ICharmTreeView;
import net.sf.anathema.platform.svgtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.platform.svgtree.presenter.view.IDocumentLoadedListener;
import net.sf.anathema.platform.svgtree.view.batik.AnathemaCanvas;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;
import net.sf.anathema.platform.svgtree.view.listening.CharmTreeListening;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherAdapter;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherEvent;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGElement;
import org.w3c.dom.svg.SVGTextElement;
import org.w3c.dom.svg.SVGUseElement;

public class CharmTreeView implements ICharmTreeView {

  private AnathemaCanvas canvas = new AnathemaCanvas();
  private ICharmTreeViewProperties properties;
  private final CharmTreeListening listening = new CharmTreeListening(canvas);

  public CharmTreeView(ICharmTreeViewProperties properties) {
    setProperties(properties);
    addDocumentLoadedListener(new IDocumentLoadedListener() {
      public void documentLoaded() {
        initCharmNames(canvas.getSVGDocument());
      }
    });
    canvas.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent evt) {
        canvas.scrollRectToVisible(new Rectangle(evt.getX(), evt.getY(), 1, 1));
      }
    });
  }

  public JComponent getComponent() {
    return canvas;
  }

  public IAnathemaCanvas getCanvas() {
    return canvas;
  }

  public void loadCascade(SVGDocument document) {
    listening.destructDocumentListening(canvas.getSVGDocument());
    if (document != null) {
      listening.initDocumentListening(document);
    }
    canvas.setDocument(document);
  }

  public synchronized void addCharmSelectionListener(ICharmSelectionListener listener) {
    listening.addCharmSelectionListener(listener);
  }

  public void setCharmBackgroundColor(String charmId, Color color) {
    Ensure.ensureNotNull("Color must not be null.", color); //$NON-NLS-1$
    SVGElement charmGroup = (SVGElement) canvas.getSVGDocument().getElementById(charmId);
    if (charmGroup == null) {
      return;
    }
    NodeList list = charmGroup.getChildNodes();
    for (int i = 0; i < list.getLength(); i++) {
      if (list.item(i) instanceof SVGUseElement) {
        SVGElement element = (SVGElement) list.item(i);
        element.setAttribute(SVGConstants.SVG_FILL_ATTRIBUTE, "rgb(" //$NON-NLS-1$
            + color.getRed()
            + "," //$NON-NLS-1$
            + color.getGreen()
            + "," //$NON-NLS-1$
            + color.getBlue()
            + ")"); //$NON-NLS-1$
        element.setAttribute(SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE, String.valueOf((float) color.getAlpha() / 255));
      }
    }
  }

  public void setCharmAlpha(String charmId, int alpha) {
    if (alpha > 255) {
      alpha = 255;
    }
    if (alpha < 0) {
      alpha = 0;
    }
    SVGElement charmGroup = (SVGElement) canvas.getSVGDocument().getElementById(charmId);
    if (charmGroup == null) {
      return;
    }
    charmGroup.setAttribute(SVGConstants.SVG_OPACITY_ATTRIBUTE, String.valueOf((float) alpha / 255));
  }

  public void addDocumentLoadedListener(final IDocumentLoadedListener listener) {
    canvas.addSVGLoadEventDispatcherListener(new SVGLoadEventDispatcherAdapter() {
      @Override
      public void svgLoadEventDispatchCompleted(SVGLoadEventDispatcherEvent arg0) {
        listener.documentLoaded();
      }
    });
  }

  // private void initCharmNames(SVGDocument svgDoc) {
  // NodeList textNodes = svgDoc.getElementsByTagName(SVG12Constants.SVG_FLOW_PARA_TAG);
  // for (int i = 0; i < textNodes.getLength(); i++) {
  // internationalize((SVGTextContentElement) textNodes.item(i));
  // }
  // }

  private void initCharmNames(SVGDocument svgDoc) {
    NodeList textNodes = svgDoc.getElementsByTagName(SVGConstants.SVG_TEXT_TAG);
    for (int i = 0; i < textNodes.getLength(); i++) {
      SVGTextElement currentNode = (SVGTextElement) textNodes.item(i);
      internationalize(currentNode);
      breakText(currentNode);
    }
  }

  // private void internationalize(SVGTextContentElement currentNode) {
  // String id = ((Text) currentNode.getFirstChild()).getData();
  // String charmName = properties.getNodeName(id);
  // if (properties.isRootCharm(id)) {
  // currentNode.getFirstChild().setNodeValue(charmName.toUpperCase());
  // }
  // else {
  // currentNode.getFirstChild().setNodeValue(charmName);
  // }
  // }

  public void setCanvasBackground(Color color) {
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

  public void setProperties(ICharmTreeViewProperties viewProperties) {
    this.properties = viewProperties;
    this.listening.setProperties(viewProperties);
  }

  public IBoundsCalculator getBoundsCalculator() {
    return listening.getBoundsCalculator();
  }

  private void breakText(SVGTextElement text) {
    float textLength = text.getComputedTextLength();
    int lines = Math.min(3, (int) Math.ceil(textLength / 95));
    Document document = text.getOwnerDocument();
    Text oldNode = (Text) text.getFirstChild();
    Text[] textNodes = new Text[lines];
    int lineHeight = 16;
    float varY = Float.valueOf(text.getAttribute(SVGConstants.SVG_Y_ATTRIBUTE));
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
      textNodes[index] = document.createTextNode(oldNode.substringData(startIndex, runLength));
    }
    text.removeChild(oldNode);
    Element[] lineBreak = new Element[lines];
    String xPosition = text.getAttribute(SVGConstants.SVG_X_ATTRIBUTE);
    for (int index = 0; index < lineBreak.length; index++) {
      lineBreak[index] = createTSpanElement(document, textNodes[index], xPosition, varY, lineHeight * index);
      text.appendChild(lineBreak[index]);
    }
  }

  private Element createTSpanElement(Document document, Text textNode, String xPosition, float varY, int dy) {
    Element tSpanElement = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_TSPAN_TAG);
    tSpanElement.setAttribute(SVGConstants.SVG_X_ATTRIBUTE, xPosition);
    tSpanElement.setAttribute(SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(varY));
    tSpanElement.setAttribute(SVGConstants.SVG_DX_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    tSpanElement.setAttribute(SVGConstants.SVG_DY_ATTRIBUTE, String.valueOf(dy));
    tSpanElement.appendChild(textNode);
    return tSpanElement;
  }

  private void internationalize(SVGTextElement text) {
    String id = ((Text) text.getFirstChild()).getData();
    String charmName = properties.getNodeName(id);
    if (properties.isRootCharm(id)) {
      text.getFirstChild().setNodeValue(charmName.toUpperCase());
    }
    else {
      text.getFirstChild().setNodeValue(charmName);
    }
  }
}