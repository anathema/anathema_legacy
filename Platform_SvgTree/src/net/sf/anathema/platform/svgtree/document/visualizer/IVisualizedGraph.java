package net.sf.anathema.platform.svgtree.document.visualizer;

import java.awt.Dimension;

import org.dom4j.Element;

public interface IVisualizedGraph {

  Element getCascadeElement();

  Dimension getDimension();

  boolean isSingleNode();
}