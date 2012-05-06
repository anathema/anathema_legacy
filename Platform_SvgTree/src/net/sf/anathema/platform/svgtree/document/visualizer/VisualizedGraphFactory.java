package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.platform.svgtree.document.components.ILayer;

public interface VisualizedGraphFactory {

  IVisualizedGraph create(ILayer... layers);
}