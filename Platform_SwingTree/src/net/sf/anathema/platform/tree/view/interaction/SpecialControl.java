package net.sf.anathema.platform.tree.view.interaction;

import javax.swing.JComponent;
import java.awt.geom.AffineTransform;

public interface SpecialControl {
  void transformThrough(AffineTransform transform);

  void addTo(JComponent parent);

  void remove(JComponent parent);

  void transformOriginalCoordinates(AffineTransform transform);

  void setPosition(int x, int y);

  void setWidth(int width);
}
