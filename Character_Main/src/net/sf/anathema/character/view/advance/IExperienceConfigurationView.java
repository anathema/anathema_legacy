package net.sf.anathema.character.view.advance;

import net.sf.anathema.framework.presenter.view.ITabView;

public interface IExperienceConfigurationView extends ITabView<IExperienceConfigurationViewProperties> {

  public void addExperienceConfigurationViewListener(IExperienceConfigurationViewListener listener);

  public void setRemoveButtonEnabled(boolean enabled);

  public void setTotalValueLabel(int overallExperiencePoints);
}