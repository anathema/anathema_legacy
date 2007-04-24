package net.sf.anathema.platform.svgtree.presenter.view;

import java.awt.Cursor;
import java.util.List;

import org.apache.batik.swing.gvt.Interactor;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGGElement;

public interface IAnathemaCanvas {

  public void setCursorInternal(Cursor currentCursor);

  public void setToolTipText(String toolTip);

  public Element getElementById(String id);

  public SVGDocument getSVGDocument();

  public List<Interactor> getInteractors();

  public List<SVGGElement> getNodeElements();

  public List<SVGGElement> getControlElements();
}