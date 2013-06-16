package net.sf.anathema.character.view;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.presenter.ExtensibleTraitView;

public interface IGroupedFavorableTraitConfigurationView {

  void initGui(ColumnCount columnCount);

  void startNewTraitGroup(String groupLabel);

  ExtensibleTraitView addExtensibleTraitView(String string, int currentValue, int maximalValue, Trait favorableTrait);
}