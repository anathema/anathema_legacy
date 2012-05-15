package net.sf.anathema.platform.svgtree.document.visualizer;

import org.dom4j.Element;

import java.awt.Dimension;

public interface IVisualizedGraph {

  Element getCascadeElement();

  Dimension getDimension();

  boolean isSingleNode();
}