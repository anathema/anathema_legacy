package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.platform.tree.util.Area;
import net.sf.anathema.platform.tree.util.RGBColor;

public interface ITreePresentationProperties {

  Area getNodeDimension();

  Area getGapDimension();

  Area getVerticalLineDimension();

  RGBColor getColor();
}