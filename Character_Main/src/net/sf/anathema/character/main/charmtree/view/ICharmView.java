package net.sf.anathema.character.main.charmtree.view;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.presenter.view.NodeInteractionListener;

public interface ICharmView extends ICascadeSelectionView, ISpecialCharmViewContainer {

  void addCharmInteractionListener(NodeInteractionListener listener);

  void setCharmVisuals(String charmId, RGBColor fillColor, int opacity);
}
