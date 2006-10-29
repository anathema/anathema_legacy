package net.sf.anathema.charmtree.presenter.view;

import java.awt.Color;

import net.sf.anathema.lib.gui.IView;

public interface ICharmSelectionView extends ICascadeSelectionView, IView {

  public void addCharmSelectionListener(ICharmSelectionListener listener);

  public void setCharmVisuals(String charmId, Color fillColor, int opacity);

  public void initGui();

  public void setSpecialCharmViewVisible(ISVGSpecialCharmView charmView, boolean visible);
}