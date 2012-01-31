package net.sf.anathema.cascades.presenter.view;

import java.awt.Color;

import javax.swing.JComponent;

import net.sf.anathema.charmtree.presenter.view.ICascadeSelectionView;

public interface ICascadeView extends ICascadeSelectionView {

  void setCharmVisuals(String id, Color color);

  void setBackgroundColor(Color color);

  void addRuleSetComponent(JComponent component, String borderTitle);

  void unselect();
}