package net.sf.anathema.character.main.library.trait.view;

import net.sf.anathema.hero.display.ExtensibleTraitView;
import net.sf.anathema.character.main.view.ColumnCount;

public interface GroupedFavorableTraitConfigurationView {

  void initGui(ColumnCount columnCount);

  void startNewTraitGroup(String groupLabel);

  ExtensibleTraitView addExtensibleTraitView(String string, int maximalValue);
}