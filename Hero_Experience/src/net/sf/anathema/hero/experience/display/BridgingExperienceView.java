package net.sf.anathema.hero.experience.display;

import net.sf.anathema.character.main.advance.IExperiencePointEntry;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.platform.fx.BridgingPanel;

import javax.swing.JComponent;

public class BridgingExperienceView implements ExperienceView, IView {
  private final FxExperienceView fxView;
  private final BridgingPanel panel = new BridgingPanel();

  public BridgingExperienceView(FxExperienceView fxView) {
    this.fxView = fxView;
  }
  @Override
  public void initGui(IExperienceViewProperties properties) {
    fxView.initGui(properties);
    panel.init(fxView);
  }

  @Override
  public void addExperienceConfigurationViewListener(ExperienceConfigurationViewListener listener) {
    fxView.addExperienceConfigurationViewListener(listener);
  }

  @Override
  public void setRemoveButtonEnabled(boolean enabled) {
    fxView.setRemoveButtonEnabled(enabled);
  }

  @Override
  public void setTotalValueLabel(int overallExperiencePoints) {
    fxView.setTotalValueLabel(overallExperiencePoints);
  }

  @Override
  public void addEntry(IExperiencePointEntry entry) {
    fxView.addEntry(entry);
  }

  @Override
  public void clearEntries() {
    fxView.clearEntries();
  }

  @Override
  public void addUpdateListener(ExperienceUpdateListener experienceUpdateListener) {
    fxView.addUpdateListener(experienceUpdateListener);
  }

  @Override
  public int getNumberOfEntriesOnDisplay() {
    return fxView.getNumberOfEntriesOnDisplay();
  }

  @Override
  public void setSelection(IExperiencePointEntry entry) {
    fxView.setSelection(entry);
  }

  @Override
  public void addAllEntries(IExperiencePointEntry... allEntries) {
    fxView.addAllEntries(allEntries);
  }

  @Override
  public JComponent getComponent() {
    return panel.getComponent();
  }
}
