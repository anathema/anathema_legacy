package net.sf.anathema.hero.experience.display;

import net.sf.anathema.character.main.advance.ExperiencePointEntry;
import net.sf.anathema.character.main.advance.ExperienceSelectionListener;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.platform.fx.BridgingPanel;

import javax.swing.JComponent;

public class BridgingExperienceView implements ExperienceView, IView {
  private final FxExperienceView fxView;
  private final BridgingPanel panel = new BridgingPanel();

  public BridgingExperienceView(FxExperienceView fxView) {
    this.fxView = fxView;
  }
  @Override
  public void initGui(ExperienceViewProperties properties) {
    fxView.initGui(properties);
    panel.init(fxView);
  }

  @Override
  public Tool addTool() {
    return fxView.addTool();
  }

  @Override
  public void addSelectionListener(ExperienceSelectionListener listener) {
    fxView.addSelectionListener(listener);
  }

  @Override
  public void setTotalValueLabel(int overallExperiencePoints) {
    fxView.setTotalValueLabel(overallExperiencePoints);
  }

  @Override
  public void addUpdateListener(ExperienceUpdateListener experienceUpdateListener) {
    fxView.addUpdateListener(experienceUpdateListener);
  }

  @Override
  public void setSelection(ExperiencePointEntry entry) {
    fxView.setSelection(entry);
  }

  @Override
  public void setEntries(ExperiencePointEntry... allEntries) {
    fxView.setEntries(allEntries);
  }

  @Override
  public JComponent getComponent() {
    return panel.getComponent();
  }
}
