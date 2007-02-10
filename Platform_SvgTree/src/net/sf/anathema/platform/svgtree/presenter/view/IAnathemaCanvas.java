package net.sf.anathema.platform.svgtree.presenter.view;

import java.awt.Cursor;
import java.util.List;

import org.apache.batik.swing.gvt.Interactor;
import org.w3c.dom.Element;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.svg.SVGDocument;

public interface IAnathemaCanvas {

  public void setCursorInternal(Cursor currentCursor);

  public void setToolTipText(String toolTip);

  public void addEventListener(String eventType, EventListener listener, boolean useCapture);

  public Element getElementById(String id);

  public SVGDocument getSVGDocument();

  public List<Interactor> getInteractors();
}