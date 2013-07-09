package net.sf.anathema.cascades.presenter.view;

import net.sf.anathema.character.main.magic.display.view.charmtree.CascadeSelectionView;
import net.sf.anathema.framework.ui.RGBColor;

public interface CascadeView extends CascadeSelectionView {

  void setCharmVisuals(String id, RGBColor color);

  void setBackgroundColor(RGBColor color);

  void unselect();
}