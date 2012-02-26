package net.sf.anathema.platform.svgtree.document.visualizer;

import java.awt.Dimension;

public abstract class AbstractCharmPresentationProperties implements ITreePresentationProperties {

  public final Dimension getVerticalLineDimension() {
    return new Dimension(getGapDimension().width, getNodeDimension().height);
  }
}