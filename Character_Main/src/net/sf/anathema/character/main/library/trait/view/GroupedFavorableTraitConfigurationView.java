package net.sf.anathema.character.main.library.trait.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.presenter.ExtensibleTraitView;
import net.sf.anathema.character.main.view.ColumnCount;

public interface GroupedFavorableTraitConfigurationView {

  void initGui(ColumnCount columnCount, ICharacterType characterType);

  void startNewTraitGroup(String groupLabel);

  ExtensibleTraitView addExtensibleTraitView(String string, int maximalValue);
}