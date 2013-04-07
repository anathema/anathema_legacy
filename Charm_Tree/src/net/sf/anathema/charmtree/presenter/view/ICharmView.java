package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.platform.tree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.tree.util.RGBColor;

public interface ICharmView extends ICascadeSelectionView, IView, ISpecialCharmViewContainer {

  void addCharmInteractionListener(NodeInteractionListener listener);

  void setCharmVisuals(String charmId, RGBColor fillColor, int opacity);
}
