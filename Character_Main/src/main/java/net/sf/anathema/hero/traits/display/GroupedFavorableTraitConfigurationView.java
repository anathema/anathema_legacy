package net.sf.anathema.hero.traits.display;

import net.sf.anathema.character.framework.display.ColumnCount;
import net.sf.anathema.hero.display.ExtensibleTraitView;

public interface GroupedFavorableTraitConfigurationView {

  void initGui(ColumnCount columnCount);

  void startNewTraitGroup(String groupLabel);

  ExtensibleTraitView addExtensibleTraitView(String string, int maximalValue);
}