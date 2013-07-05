package net.sf.anathema.character.library.trait.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.character.view.ColumnCount;

public interface GroupedFavorableTraitConfigurationView {

  void initGui(ColumnCount columnCount, ICharacterType characterType);

  void startNewTraitGroup(String groupLabel);

  ExtensibleTraitView addExtensibleTraitView(String string, int maximalValue);
}