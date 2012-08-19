package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.platform.tree.presenter.view.NodeInteractionListener;

import java.awt.Color;

public interface ICharmView extends ICascadeSelectionView, IView, ISpecialCharmViewContainer {

  void addCharmInteractionListener(NodeInteractionListener listener);

  void setCharmVisuals(String charmId, Color fillColor, int opacity);
}
