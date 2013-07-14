package net.sf.anathema.character.main.library.trait.view;

import net.sf.anathema.character.main.view.ColumnCount;
import net.sf.anathema.hero.display.ExtensibleTraitView;

public interface GroupedFavorableTraitConfigurationView {

  void initGui(ColumnCount columnCount);

  void startNewTraitGroup(String groupLabel);

  ExtensibleTraitView addExtensibleTraitView(String string, int maximalValue);
}