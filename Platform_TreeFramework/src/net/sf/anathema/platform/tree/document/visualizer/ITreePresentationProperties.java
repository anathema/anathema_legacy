package net.sf.anathema.platform.tree.document.visualizer;

import java.awt.Color;
import java.awt.Dimension;

public interface ITreePresentationProperties {

  Dimension getNodeDimension();

  Dimension getGapDimension();

  Dimension getVerticalLineDimension();

  Color getColor();
}