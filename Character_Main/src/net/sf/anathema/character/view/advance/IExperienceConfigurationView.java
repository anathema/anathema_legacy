package net.sf.anathema.character.view.advance;

import net.sf.anathema.framework.presenter.view.IContentView;

public interface IExperienceConfigurationView extends IContentView<IExperienceConfigurationViewProperties> {

  public void addExperienceConfigurationViewListener(IExperienceConfigurationViewListener listener);

  public void setRemoveButtonEnabled(boolean enabled);

  public void setTotalValueLabel(int overallExperiencePoints);
}