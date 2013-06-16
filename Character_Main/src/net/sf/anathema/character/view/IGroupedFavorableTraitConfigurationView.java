package net.sf.anathema.character.view;

import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.trait.Trait;

public interface IGroupedFavorableTraitConfigurationView {

  IToggleButtonTraitView<?> addTraitView(String labelText, int value, int maxValue, Trait trait, boolean selected,
                                         IIconToggleButtonProperties properties);

  void startNewTraitGroup(String groupLabel);

  void initGui(int columnCount);
}