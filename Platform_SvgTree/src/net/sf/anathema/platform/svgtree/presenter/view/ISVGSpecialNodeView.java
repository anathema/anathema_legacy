package net.sf.anathema.platform.svgtree.presenter.view;

import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;

import org.apache.batik.dom.svg.SVGOMDocument;
import org.w3c.dom.svg.SVGGElement;

public interface ISVGSpecialNodeView {

  String getNodeId();

  SVGGElement initGui(SVGOMDocument document, IBoundsCalculator boundsCalculator);

  void setVisible(boolean visible);

  void reset();
}