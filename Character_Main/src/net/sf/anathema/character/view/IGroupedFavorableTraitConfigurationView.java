package net.sf.anathema.character.view;

import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.trait.IDefaultTrait;
import net.sf.anathema.framework.swing.IView;

public interface IGroupedFavorableTraitConfigurationView extends IView {

  IToggleButtonTraitView<?> addTraitView(String labelText, int value, int maxValue, IDefaultTrait trait, boolean selected,
                                         IIconToggleButtonProperties properties);

  IToggleButtonTraitView<?> addMarkerLessTraitView(String labelText, int value, int maxValue, IDefaultTrait trait, boolean selected,
                                                   IIconToggleButtonProperties properties);

  void startNewTraitGroup(String groupLabel);

  void initGui(int columnCount);
}