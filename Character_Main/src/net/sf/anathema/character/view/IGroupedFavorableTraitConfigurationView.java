package net.sf.anathema.character.view;

import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.framework.swing.IView;

public interface IGroupedFavorableTraitConfigurationView extends IView {

  IToggleButtonTraitView<?> addTraitView(String labelText, int value, int maxValue, IModifiableCapTrait trait, boolean selected,
                                         IIconToggleButtonProperties properties);

  IToggleButtonTraitView<?> addMarkerLessTraitView(String labelText, int value, int maxValue, IModifiableCapTrait trait, boolean selected,
                                                   IIconToggleButtonProperties properties);

  void startNewTraitGroup(String groupLabel);
}