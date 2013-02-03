package net.sf.anathema.character.view.advance;

import net.sf.anathema.framework.presenter.view.IInitializableContentView;

public interface IExperienceConfigurationView extends IInitializableContentView<IExperienceConfigurationViewProperties> {

  void addExperienceConfigurationViewListener(IExperienceConfigurationViewListener listener);

  void setRemoveButtonEnabled(boolean enabled);

  void setTotalValueLabel(int overallExperiencePoints);
}