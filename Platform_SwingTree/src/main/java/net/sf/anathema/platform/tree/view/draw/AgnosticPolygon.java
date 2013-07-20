package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.view.interaction.SpecialControl;

public interface AgnosticPolygon {
  void whenToggledDo(Runnable runnable);

  void fill(RGBColor fillColor);

  void setAlpha(int alpha);

  void setText(String id);

  void moveBy(int x, int y);

  void position(SpecialControl control);

  boolean contains(Coordinate point);
}
