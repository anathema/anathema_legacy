package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.transform.AgnosticTransform;

import javax.swing.JComponent;

public interface SpecialControl extends ShapeWithPosition {
  void transformThrough(AgnosticTransform transform);

  void addTo(JComponent parent);

  void remove(JComponent parent);

  void transformOriginalCoordinates(AgnosticTransform transform);
}
