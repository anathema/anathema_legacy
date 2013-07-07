package net.sf.anathema.cascades.presenter.view;

import net.sf.anathema.character.main.charmtree.view.ICascadeSelectionView;
import net.sf.anathema.framework.ui.RGBColor;

public interface ICascadeView extends ICascadeSelectionView {

  void setCharmVisuals(String id, RGBColor color);

  void setBackgroundColor(RGBColor color);

  void unselect();
}