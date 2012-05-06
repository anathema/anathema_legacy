package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.platform.svgtree.document.components.SvgNodeElementAdder;
import org.dom4j.Element;

import java.awt.Dimension;

public class SvgNodeAdderFactory implements NodeAdderFactory<Element> {
  @Override
  public NodeAdder<Element> create(String id, Dimension dimension, int xPosition, int yPosition) {
    return new SvgNodeElementAdder(id, dimension, xPosition, yPosition);
  }
}