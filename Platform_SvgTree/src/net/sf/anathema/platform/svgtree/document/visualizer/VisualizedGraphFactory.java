package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.platform.svgtree.document.components.ILayer;

import java.awt.Dimension;

public interface VisualizedGraphFactory {
  IVisualizedGraph createForSingleNode(ILayer layer);

  IVisualizedGraph create(ILayer[] layers);

  IVisualizedGraph createWithDimension(ILayer[] layers, Dimension dimension);
}
