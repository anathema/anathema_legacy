package net.sf.anathema.character.view;

import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.presenter.ExtensibleTraitView;

public interface IGroupedFavorableTraitConfigurationView {

  IToggleButtonTraitView<?> addTraitView(String labelText, int value, int maxValue, Trait trait, boolean selected,
                                         IIconToggleButtonProperties properties);

  void startNewTraitGroup(String groupLabel);

  void initGui(ColumnCount columnCount);

  ExtensibleTraitView addExtensibleTraitView(String string, int currentValue, int maximalValue, Trait favorableTrait, boolean favored, IIconToggleButtonProperties favorableTraitViewProperties);
}