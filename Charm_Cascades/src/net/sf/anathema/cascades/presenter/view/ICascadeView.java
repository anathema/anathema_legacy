package net.sf.anathema.cascades.presenter.view;

import net.sf.anathema.charmtree.view.ICascadeSelectionView;
import net.sf.anathema.platform.tree.util.RGBColor;

public interface ICascadeView extends ICascadeSelectionView {

  void setCharmVisuals(String id, RGBColor color);

  void setBackgroundColor(RGBColor color);

  void unselect();
}