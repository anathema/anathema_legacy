package net.sf.anathema.charmtree.presenter.view;

import java.awt.Color;

import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.platform.svgtree.presenter.view.INodeSelectionListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;

public interface ICharmSelectionView extends ICascadeSelectionView, IView {

  public void addCharmSelectionListener(INodeSelectionListener listener);

  public void setCharmVisuals(String charmId, Color fillColor, int opacity);

  public void initGui();

  public void setSpecialCharmViewVisible(ISVGSpecialNodeView charmView, boolean visible);
}