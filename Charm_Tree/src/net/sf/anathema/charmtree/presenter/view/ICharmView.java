package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.platform.svgtree.presenter.view.CharmInteractionListener;

import java.awt.Color;

public interface ICharmView extends ICascadeSelectionView, IView, ISpecialCharmViewContainer {

  void addCharmInteractionListener(CharmInteractionListener listener);

  void setCharmVisuals(String charmId, Color fillColor, int opacity);
}
