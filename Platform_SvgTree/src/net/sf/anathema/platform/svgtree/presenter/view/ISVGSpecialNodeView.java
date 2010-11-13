package net.sf.anathema.platform.svgtree.presenter.view;

import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;

import org.apache.batik.dom.svg.SVGOMDocument;
import org.w3c.dom.svg.SVGGElement;

public interface ISVGSpecialNodeView {

  public String getNodeId();

  public SVGGElement initGui(SVGOMDocument document, IBoundsCalculator boundsCalculator);

  public void setVisible(boolean visible);

  public void reset();
}