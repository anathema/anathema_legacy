package net.sf.anathema.platform.svgtree.view.batik;

import java.awt.Rectangle;

import org.w3c.dom.svg.SVGLocatable;

public interface IBoundsCalculator {

  public Rectangle getBounds(SVGLocatable svgElement);

}