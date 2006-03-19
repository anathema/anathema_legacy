package net.sf.anathema.character.view;

import net.sf.anathema.character.library.intvalue.IFavorableIntValueView;
import net.sf.anathema.character.library.intvalue.IFavorableIntViewProperties;
import net.sf.anathema.character.presenter.specialty.ISpecialtyConfigurationView;
import net.sf.anathema.framework.presenter.view.ITabView;

public interface IGroupedFavorableTraitConfigurationView extends ISpecialtyConfigurationView, ITabView<Object> {

  public IFavorableIntValueView addTraitView(
      String labelText,
      int value,
      int maxValue,
      boolean selected,
      IFavorableIntViewProperties properties);

  public void startNewTraitGroup(String groupLabel);
}