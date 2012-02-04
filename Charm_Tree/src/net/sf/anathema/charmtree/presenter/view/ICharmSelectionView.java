package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.platform.svgtree.presenter.view.INodeSelectionListener;

import javax.swing.JComponent;
import java.awt.Color;

public interface ICharmSelectionView extends ICascadeSelectionView, IView, ISpecialCharmViewContainer {

  void addCharmSelectionListener(INodeSelectionListener listener);

  void setCharmVisuals(String charmId, Color fillColor, int opacity);

  void initGui();

  JComponent getCharmComponent();
}