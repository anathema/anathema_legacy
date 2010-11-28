package net.sf.anathema.platform.svgtree.document.visualizer;

import java.awt.Dimension;

import org.dom4j.Element;

public interface IVisualizedGraph {

  public Element getCascadeElement();

  public Dimension getDimension();

  public boolean isSingleNode();
}