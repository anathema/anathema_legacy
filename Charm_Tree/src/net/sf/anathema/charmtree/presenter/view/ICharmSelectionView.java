package net.sf.anathema.charmtree.presenter.view;

import java.awt.Color;

import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICharmSelectionView extends ICascadeSelectionView, ISimpleTabView {

  public void addCharmSelectionListener(ICharmSelectionListener listener);

  public void setCharmVisuals(String charmId, Color fillColor, int opacity);

  public void fillCharmTypeBox(IIdentificate[] cascadeTypes);

  public void initGui();

  public void setSpecialCharmViewVisible(ISVGMultiLearnableCharmView charmView, boolean visible);
}