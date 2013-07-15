package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.platform.tree.document.components.ILayer;

public interface VisualizedGraphFactory {

  IVisualizedGraph create(ILayer... layers);
}