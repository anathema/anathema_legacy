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
      public void removeRequested() {
        experiencePoints.removeEntry();
      }

      @Override
      public void addRequested() {
        experiencePoints.addEntry();
      }

      @Override
      public void selectionChanged(IExperiencePointEntry entry) {
        experiencePoints.selectForChange(entry);
      }

    });
    experiencePoints.addExperiencePointConfigurationListener(new ExperiencePointConfigurationListener() {
      @Override
      public void entryRemoved() {
        refreshEntriesInView();
      }

      @Override
      public void entryAdded() {
        refreshEntriesInView();
      }

      @Override
      public void entryChanged() {
        refreshEntriesInView();
      }

      @Override
      public void selectionChanged(IExperiencePointEntry entry) {
        updateSelectionInView(entry);
      }
    });
    experienceView.initGui(new ExperienceViewProperties(resources));
    refreshEntriesInView();
    experienceView.addUpdateListener(new ExperienceUpdateListener() {
      public void update(int points, String description) {
        experiencePoints.updateCurrentSelection(description, points);
      }
    });
    updateTotal();
  }

  private void updateSelectionInView(IExperiencePointEntry entry) {
    experienceView.setSelection(entry);
    experienceView.setRemoveButtonEnabled(entry != null);
  }

  private void refreshEntriesInView() {
    experienceView.clearEntries();
    for (IExperiencePointEntry entry : experiencePoints.getAllEntries()) {
      addToView(entry);
    }
    updateSelectionInView(experiencePoints.getCurrentSelection());
    updateTotal();
  }

  private void addToView(IExperiencePointEntry entry) {
    experienceView.addEntry(entry);
  }

  private void updateTotal() {
    experienceView.setTotalValueLabel(experiencePoints.getTotalExperiencePoints());
  }
}