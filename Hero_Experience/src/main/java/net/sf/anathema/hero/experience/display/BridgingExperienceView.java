package net.sf.anathema.hero.experience.display;

import net.sf.anathema.fx.hero.perspective.AbstractBridgingView;
import net.sf.anathema.hero.advance.experience.ExperiencePointEntry;
import net.sf.anathema.hero.advance.experience.ExperienceSelectionListener;
import net.sf.anathema.interaction.Tool;

public class BridgingExperienceView extends AbstractBridgingView implements ExperienceView {
  private final FxExperienceView fxView;

  public BridgingExperienceView(FxExperienceView fxView) {
    this.fxView = fxView;
  }
  @Override
  public void initGui(ExperienceViewProperties properties) {
    fxView.initGui(properties);
    init(fxView, "skin/experience/experience.css");
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
}