package net.sf.anathema.character.view.advance;

public interface ExperienceView {

  void initGui(IExperienceViewProperties properties);

  void addExperienceConfigurationViewListener(IExperienceConfigurationViewListener listener);

  void setRemoveButtonEnabled(boolean enabled);

  void setTotalValueLabel(int overallExperiencePoints);
}