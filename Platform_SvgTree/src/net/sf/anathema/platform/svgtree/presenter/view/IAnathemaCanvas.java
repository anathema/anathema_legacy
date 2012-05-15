package net.sf.anathema.platform.svgtree.presenter.view;

import org.apache.batik.swing.gvt.Interactor;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGGElement;

import java.awt.Cursor;
import java.util.List;

public interface IAnathemaCanvas {

  void setCursorInternal(Cursor currentCursor);

  void setToolTipText(String toolTip);

  Element getElementById(String id);

  SVGDocument getSVGDocument();

  List<Interactor> getInteractors();

  List<SVGGElement> getNodeElements();

  List<SVGGElement> getControlElements();
}