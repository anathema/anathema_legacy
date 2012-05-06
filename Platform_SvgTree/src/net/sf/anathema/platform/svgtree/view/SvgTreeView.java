package net.sf.anathema.platform.svgtree.view;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants;
import net.sf.anathema.platform.svgtree.presenter.view.CascadeLoadException;
import net.sf.anathema.platform.svgtree.presenter.view.CascadeLoadedListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISpecialNodeViewManager;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;
import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.svgtree.view.batik.AnathemaCanvas;
import net.sf.anathema.platform.svgtree.view.batik.BoundsCalculator;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.DomUtilities;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGSpecialNodeViewManager;
import net.sf.anathema.platform.svgtree.view.listening.SvgTreeListening;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.gvt.GVTTreeRendererListener;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherAdapter;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherEvent;
import org.dom4j.DocumentException;
import org.dom4j.io.DOMWriter;
import org.w3c.dom.DOMException;
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

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.ATTRIB_POINTER_EVENTS;
import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.ATTRIB_VISIBILITY;
import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.VALUE_1500;
import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.VALUE_3000;
import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.VALUE_CASCADE_ID;
import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.VALUE_FILL;
import static org.apache.batik.util.SVGConstants.SVG_DX_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_DY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_FILL_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_HEIGHT_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_NONE_VALUE;
import static org.apache.batik.util.SVGConstants.SVG_OPACITY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_RECT_TAG;
import static org.apache.batik.util.SVGConstants.SVG_TEXT_TAG;
import static org.apache.batik.util.SVGConstants.SVG_TSPAN_TAG;
import static org.apache.batik.util.SVGConstants.SVG_WIDTH_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_X_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_Y_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_ZERO_VALUE;

public class SvgTreeView implements ISvgTreeView {

  private AnathemaCanvas canvas = new AnathemaCanvas();
  private final ISvgTreeViewProperties properties;
  private final SvgTreeListening listening;
  private final SVGSpecialNodeViewManager manager;

  public SvgTreeView(ISvgTreeViewProperties properties) {
    this.properties = properties;
    IBoundsCalculator calculator = new BoundsCalculator();
    this.manager = new SVGSpecialNodeViewManager(canvas, calculator);
    this.listening = new SvgTreeListening(canvas, properties);
    addCascadeLoadedListener(new CascadeLoadedListener() {
      @Override
      public void cascadeLoaded() {
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

  @Override
  public JComponent getComponent() {
    return canvas;
  }

  @Override
  public ISpecialNodeViewManager getSpecialViewManager() {
    return manager;
  }

  @Override
  public void loadCascade(org.dom4j.Document cascade, boolean resetView) throws CascadeLoadException {
    final AffineTransform transform = resetView ? null : canvas.getRenderingTransform();
    if (resetView) {
      canvas.resetRenderingTransform();
    }
    listening.destructDocumentListening(canvas.getSVGDocument());
    SVGDocument document = null;
    if (cascade != null) {
      document = createSvgDocument(cascade);
      injectGlassPane(document);
    }
    canvas.setDocument(document);
    if (transform != null) {
      canvas.addGVTTreeRendererListener(new GVTTreeRendererListener() {

        @Override
        public void gvtRenderingCancelled(GVTTreeRendererEvent e) {
          // TODO Auto-generated method stub

        }

        @Override
        public void gvtRenderingCompleted(GVTTreeRendererEvent e) {
          // TODO Auto-generated method stub
        }

        @Override
        public void gvtRenderingFailed(GVTTreeRendererEvent e) {
          // TODO Auto-generated method stub
        }

        @Override
        public void gvtRenderingPrepare(GVTTreeRendererEvent e) {
          canvas.setRenderingTransform(transform);
          canvas.removeGVTTreeRendererListener(this);
        }

        @Override
        public void gvtRenderingStarted(GVTTreeRendererEvent e) {
          // TODO Auto-generated method stub

        }
      });
    }
  }

  @Override
  public void clear() {
    loadCascade(null, true);
  }

  private SVGDocument createSvgDocument(org.dom4j.Document dom4jDocument) {
    SVGDocument document;
    DOMImplementation implementation = SVGDOMImplementation.getDOMImplementation();
    try {
      document = (SVGDocument) new DOMWriter().write(dom4jDocument, implementation);
    } catch (DocumentException e) {
      throw new CascadeLoadException(e);
    }
    return document;
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

  @Override
  public void addNodeInteractionListener(NodeInteractionListener listener) {
    listening.addNodeSelectionListener(listener);
  }

  @Override
  public void setNodeBackgroundColor(String nodeId, Color color) {
    Preconditions.checkNotNull(color);
    SVGElement nodeGroup = (SVGElement) canvas.getElementById(nodeId);
    if (nodeGroup == null) {
      return;
    }
    NodeList list = nodeGroup.getChildNodes();
    for (int i = 0; i < list.getLength(); i++) {
      if (list.item(i) instanceof SVGUseElement) {
        SVGElement element = (SVGElement) list.item(i);
        element.setAttribute(SVG_FILL_ATTRIBUTE, "rgb(" //$NON-NLS-1$
                + color.getRed() + "," //$NON-NLS-1$
                + color.getGreen() + "," //$NON-NLS-1$
                + color.getBlue() + ")"); //$NON-NLS-1$
        element.setAttribute(SVG_FILL_OPACITY_ATTRIBUTE, String.valueOf((float) color.getAlpha() / 255));
      }
    }
  }

  @Override
  public void setNodeAlpha(String nodeId, int alpha) {
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

  @Override
  public void addCascadeLoadedListener(final CascadeLoadedListener listener) {
    canvas.addSVGLoadEventDispatcherListener(new SVGLoadEventDispatcherAdapter() {
      @Override
      public void svgLoadEventDispatchCompleted(SVGLoadEventDispatcherEvent arg0) {
        listener.cascadeLoaded();
      }
    });
  }

  private void initNodeNames() {
    DOMException error = null;
    for (SVGGElement groupElement : canvas.getNodeElements()) {
      NodeList textNodes = groupElement.getElementsByTagName(SVG_TEXT_TAG);
      for (int i = 0; i < textNodes.getLength(); i++) {
        try {
          SVGTextElement currentNode = (SVGTextElement) textNodes.item(i);
          internationalize(currentNode);
          breakText(currentNode);
        } catch (DOMException e) {
          error = e;
        }
      }
    }
    if (error != null){
      throw error;
    }
  }

  @Override
  public void setCanvasBackground(Color color) {
    canvas.setBackground(color);
  }

  @Override
  public void dispose() {
    SVGDocument document = canvas.getSVGDocument();
    if (document != null) {
      listening.destructDocumentListening(document);
    }
    canvas.setDocument(null);
    // todo vom (22.02.2005) (sieroux): Notify pool of unused canvas
    canvas = null;
  }

  private void breakText(SVGTextElement text) {
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
    for (int breakpoint : StringUtilities.findBreakPoints(oldNode.getData(), lines)) {
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

  private Element createTSpanElement(Document document, Text textNode, String xPosition, float varY, int dy) {
    Element tSpanElement = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVG_TSPAN_TAG);
    tSpanElement.setAttribute(SVG_X_ATTRIBUTE, xPosition);
    tSpanElement.setAttribute(SVG_Y_ATTRIBUTE, String.valueOf(varY));
    tSpanElement.setAttribute(SVG_DX_ATTRIBUTE, SVG_ZERO_VALUE);
    tSpanElement.setAttribute(SVG_DY_ATTRIBUTE, String.valueOf(dy));
    tSpanElement.setAttribute(ISVGCascadeXMLConstants.ATTRIB_POINTER_EVENTS, SVG_NONE_VALUE);
    tSpanElement.appendChild(textNode);
    return tSpanElement;
  }

  private void internationalize(SVGTextElement text) {
    String id = ((Text) text.getFirstChild()).getData();
    String nodeName = properties.getNodeName(id);
    if (properties.isRootNode(id)) {
      text.getFirstChild().setNodeValue(nodeName.toUpperCase());
    } else {
      text.getFirstChild().setNodeValue(nodeName);
    }
  }
}
