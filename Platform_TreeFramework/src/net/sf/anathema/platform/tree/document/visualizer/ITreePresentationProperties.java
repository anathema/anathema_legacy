package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.framework.ui.RGBColor;

public interface ITreePresentationProperties {

  Area getNodeDimension();

  Area getGapDimension();

  Area getVerticalLineDimension();

  RGBColor getColor();
}