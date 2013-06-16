package net.sf.anathema.character.view.advance;

public interface IExperienceConfigurationView {

  void initGui(IExperienceConfigurationViewProperties properties);

  void addExperienceConfigurationViewListener(IExperienceConfigurationViewListener listener);

  void setRemoveButtonEnabled(boolean enabled);

  void setTotalValueLabel(int overallExperiencePoints);
}