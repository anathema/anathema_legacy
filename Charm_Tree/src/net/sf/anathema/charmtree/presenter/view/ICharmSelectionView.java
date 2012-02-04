package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.platform.svgtree.presenter.view.INodeSelectionListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;

import javax.swing.JComponent;
import java.awt.Color;

public interface ICharmSelectionView extends ICascadeSelectionView, IView, ISpecialCharmViewFactory {

  void addCharmSelectionListener(INodeSelectionListener listener);

  void setCharmVisuals(String charmId, Color fillColor, int opacity);

  void initGui();

  void setSpecialCharmViewVisible(ISVGSpecialNodeView charmView, boolean visible);

  JComponent getCharmComponent();

}