package net.sf.anathema.hero.experience.display;

import net.sf.anathema.character.main.advance.ExperiencePointConfigurationListener;
import net.sf.anathema.character.main.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.main.advance.IExperiencePointEntry;
import net.sf.anathema.lib.resources.Resources;

public class ExperienceConfigurationPresenter {

  private final IExperiencePointConfiguration experiencePoints;
  private final ExperienceView experienceView;
  private final Resources resources;

  public ExperienceConfigurationPresenter(Resources resources, IExperiencePointConfiguration experiencePoints,
                                          ExperienceView experienceView) {
    this.resources = resources;
    this.experiencePoints = experiencePoints;
    this.experienceView = experienceView;
  }

  public void initPresentation() {
    experienceView.addExperienceConfigurationViewListener(new ExperienceConfigurationViewListener() {
      @Override
      public void removeRequested(IExperiencePointEntry entry) {
        experiencePoints.removeEntry(entry);
      }

      @Override
      public void addRequested() {
        experiencePoints.addEntry();
      }

      @Override
      public void selectionChanged(IExperiencePointEntry entry) {
        experienceView.setRemoveButtonEnabled(entry != null);
      }

    });
    experiencePoints.addExperiencePointConfigurationListener(new ExperiencePointConfigurationListener() {
      @Override
      public void entryRemoved(IExperiencePointEntry entry) {
        refreshEntriesInView();
      }

      @Override
      public void entryAdded(IExperiencePointEntry entry) {
        refreshEntriesInView();
      }

      @Override
      public void entryChanged(IExperiencePointEntry entry) {
        refreshEntriesInView();
      }
    });
    experienceView.initGui(new ExperienceViewProperties(resources));
    refreshEntriesInView();
    experienceView.addUpdateListener(new ExperienceUpdateListener() {
      public void update(IExperiencePointEntry entry, int points, String description) {
        entry.setExperiencePoints(points);
        entry.getTextualDescription().setText(description);
      }
    });
    updateTotal();
  }

  private void refreshEntriesInView() {
    experienceView.clearEntries();
    for (IExperiencePointEntry entry : experiencePoints.getAllEntries()) {
      addToView(entry);
    }
    updateTotal();
  }

  private void addToView(IExperiencePointEntry entry) {
    experienceView.addEntry(entry);
  }

  private void updateTotal() {
    experienceView.setTotalValueLabel(experiencePoints.getTotalExperiencePoints());
  }
}