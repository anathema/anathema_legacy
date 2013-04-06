package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.platform.tree.util.Area;

import java.awt.Color;

public interface ITreePresentationProperties {

  Area getNodeDimension();

  Area getGapDimension();

  Area getVerticalLineDimension();

  Color getColor();
}