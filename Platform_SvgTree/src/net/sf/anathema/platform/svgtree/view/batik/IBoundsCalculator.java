package net.sf.anathema.platform.svgtree.view.batik;

import org.w3c.dom.svg.SVGLocatable;

import java.awt.Rectangle;

public interface IBoundsCalculator {

  Rectangle getBounds(SVGLocatable svgElement);
}