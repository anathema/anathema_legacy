package net.sf.anathema.charmtree.provider;

import java.awt.Dimension;

import org.dom4j.Element;

public interface IVisualizedGraph {

  public Element getCascadeElement();

  public Dimension getDimension();

  public boolean isSingleNode();
}