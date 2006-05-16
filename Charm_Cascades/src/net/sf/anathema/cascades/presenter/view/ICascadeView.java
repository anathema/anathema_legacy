package net.sf.anathema.cascades.presenter.view;

import java.awt.Color;

import javax.swing.JComponent;

import net.sf.anathema.charmtree.presenter.view.ICascadeSelectionView;

public interface ICascadeView extends ICascadeSelectionView {

  public void setCharmVisuals(String id, Color color);

  public void setBackgroundColor(Color color);

  public void addRuleSetComponent(JComponent component, String borderTitle);

  public void unselect();
}