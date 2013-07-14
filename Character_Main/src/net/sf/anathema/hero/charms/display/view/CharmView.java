package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.NodeInteractionListener;

public interface CharmView extends CascadeSelectionView, SpecialCharmViewContainer {

  void addCharmInteractionListener(NodeInteractionListener listener);

  void setCharmVisuals(String charmId, RGBColor fillColor, int opacity);
}
