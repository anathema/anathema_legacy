package net.sf.anathema.character.view;

import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.presenter.specialty.ISpecialtyConfigurationView;
import net.sf.anathema.framework.presenter.view.ITabView;

public interface IGroupedFavorableTraitConfigurationView extends ISpecialtyConfigurationView, ITabView<Object> {

  public IToggleButtonTraitView addTraitView(
      String labelText,
      int value,
      int maxValue,
      boolean selected,
      IIconToggleButtonProperties properties);

  public void startNewTraitGroup(String groupLabel);
}