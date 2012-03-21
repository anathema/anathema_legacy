package net.sf.anathema.cascades.presenter.view;

import net.sf.anathema.charmtree.presenter.view.ICascadeSelectionView;

import java.awt.Color;

public interface ICascadeView extends ICascadeSelectionView {

  void setCharmVisuals(String id, Color color);

  void setBackgroundColor(Color color);

  void unselect();
}