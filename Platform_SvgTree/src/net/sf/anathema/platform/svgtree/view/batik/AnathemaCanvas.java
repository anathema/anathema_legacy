package net.sf.anathema.platform.svgtree.view.batik;

import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants;
import net.sf.anathema.platform.svgtree.presenter.view.IAnathemaCanvas;

import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.AbstractJSVGComponent;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.svg.SVGGElement;

public class AnathemaCanvas extends JSVGCanvas implements IAnathemaCanvas {

  public AnathemaCanvas() {
    super(null, true, false);
    setEnableImageZoomInteractor(false);
    setEnablePanInteractor(false);
    setEnableRotateInteractor(false);
    setEnableZoomInteractor(false);
    setDocumentState(AbstractJSVGComponent.ALWAYS_DYNAMIC);
    setBackground(new Color(255, 255, 255, 120));
    setProgressivePaint(true);
  }

  @Override
  public void setCursor(Cursor cursor) {
    // Nothing to do
  }

  public void setCursorInternal(Cursor cursor) {
    super.setCursor(cursor);
  }

  public void addEventListener(String eventType, EventListener eventListener, boolean useCapture) {
    Document document = getSVGDocument();
    if (document == null || !(document instanceof SVGOMDocument)) {
      return;
    }
    final SVGOMDocument omDocument = (SVGOMDocument) document;
    omDocument.addEventListener(eventType, eventListener, false);
  }

  public List<SVGGElement> getNodeElements() {
    NodeList groupElementsList = getElementById(ISVGCascadeXMLConstants.VALUE_CASCADE_ID).getElementsByTagName(
        SVGConstants.SVG_G_TAG);
    List<SVGGElement> list = new ArrayList<SVGGElement>();
    for (int index = 0; index < groupElementsList.getLength(); index++) {
      SVGGElement groupElement = (SVGGElement) groupElementsList.item(index);
      if (groupElement.hasAttribute(ISVGCascadeXMLConstants.ATTRIB_IS_TREE_NODE)) {
        list.add(groupElement);
      }
    }
    return list;
  }

  public Element getElementById(String id) {
    return getSVGDocument().getElementById(id);
  }
}