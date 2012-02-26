package net.sf.anathema.platform.svgtree.document.visualizer;

import java.awt.Color;
import java.awt.Dimension;

public interface ITreePresentationProperties {

  String getNodeFramePolygonString();

  Dimension getNodeDimension();

  Dimension getGapDimension();

  Dimension getVerticalLineDimension();

  Color getColor();
}